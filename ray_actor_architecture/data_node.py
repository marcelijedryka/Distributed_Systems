import ray

@ray.remote
class DataNode():

    def __init__(self) -> None:
        self. data = {}
        self.status = "READY"

    def post(self, name : str, data : str) -> None:
        self.data[name] = data

    def delete(self, name : str) -> None:
        self.data.pop(name)

    def remove_all(self) -> None:
        self.data = {}

    def get(self, name : str) -> str:
        return self.data[name]
    
    def get_whole(self):
        return self.data
    
    def put(self, name : str, data: str) -> None:
        if name in self.data:
            self.data[name] = data
    
    def get_status(self):
        return self.status