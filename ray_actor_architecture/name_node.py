import ray
from data_node import DataNode
from typing import List
import random

@ray.remote
class NameNode():

    def __init__(self, storage_nodes : int, splits : int, copies : int) -> None:
        self.data_nodes = [DataNode.remote() for i in range(storage_nodes)]
        self.splits = splits
        self.copies = copies
        self.artefacts = {}

    def slice_data(self, data : str) -> List:
        data_blocks = []
        n = len(data) // self.splits

        i = 0
        while i < self.splits - 1:
            block = data[n * i : n * (i+1)]
            data_blocks.append(block)
            i += 1
        
        data_blocks.append(data[n * i :])

        return data_blocks
    
    def put(self, name : str, data : str):
        if name not in self.artefacts.keys():
            return "Nothing is being stored under this name"
        
        self.artefacts[name] = {}
        data_blocks = self.slice_data(data)

        for i, block in enumerate(data_blocks):
            block_name = name + f"_{i}"
            storage_nodes = random.sample(range(len(self.data_nodes)), self.copies)
            self.artefacts[name][block_name] = storage_nodes
            for node in storage_nodes:
                self.data_nodes[node].put.remote(block_name, block)
        
        return "Data has been replaced"

    
    def delete(self, name : str) -> str:
        if name not in self.artefacts.keys():
            return "Nothing is being stored under this name"
        
        for block in self.artefacts[name]:
            for node in self.artefacts[name][block]:
                self.data_nodes[node].delete.remote(block)

        self.artefacts.pop(name)
        
        return "Object has been deleted"

    def post(self, name : str, data : str) -> None:
        self.artefacts[name] = {}
        data_blocks = self.slice_data(data)

        for i, block in enumerate(data_blocks):
            block_name = name + f"_{i}"
            storage_nodes = random.sample(range(len(self.data_nodes)), self.copies)
            self.artefacts[name][block_name] = storage_nodes
            for node in storage_nodes:
                self.data_nodes[node].post.remote(block_name, block)
    

    def show_status(self) -> List:
        data = []

        for node in self.data_nodes:
            data.append(ray.get(node.get_whole.remote()))

        return data

    def remove_all(self) -> str:
        for node in self.data_nodes:
            ray.get(node.remove_all.remote())

        self.artefacts = {}
        
        return "Data Nodes have been cleared"
    
    def get_artefacts(self) -> dict:
        return self.artefacts
    
    def get(self, name : str) -> str:
        if name not in self.artefacts.keys():
            return "Nothing is being stored under this name"
        
        output = ""
        
        for block in self.artefacts[name]:
            result_block = [self.data_nodes[node].get.remote(block) for node in self.artefacts.get(name).get(block)]
            ready_block, remaining_blocks = ray.wait(result_block, num_returns=1, timeout=None) 
            output += ray.get(ready_block[0])

        return output