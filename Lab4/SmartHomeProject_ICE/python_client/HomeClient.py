import Ice
import sys
import os

sys.path.append(os.path.join(os.path.dirname(__file__), "..", "generated_py"))
import SmartHome


def create_device_proxies(ip,socket, communicator, devices, shift = 0):

    device_types = {
        "fridge": 2, 
        "lights": 2, 
        "led": 2
    }

    for device_type, count in device_types.items():
        devices[device_type] = []
        for i in range(1, count + 1):
            if device_type == 'led':
                proxy = communicator.stringToProxy(f"light/{device_type}_{i+shift}:tcp -h {ip} -p {socket}")
            else:  
                proxy = communicator.stringToProxy(f"{device_type}/{device_type}_{i+shift}:tcp -h {ip} -p {socket}")
            if device_type == "fridge":
                casted_proxy = SmartHome.FridgePrx.checkedCast(proxy)
            elif device_type == "light":
                casted_proxy = SmartHome.LightsPrx.checkedCast(proxy)
            elif device_type == "led":
                casted_proxy = SmartHome.LEDLightsPrx.checkedCast(proxy)

            devices[f"{device_type}{i+shift}"] = casted_proxy


def main():
    with  Ice.initialize(sys.argv) as communicator:

        devices = {}

        server1 = '127.0.0.2'
        socket1 = 10000
        server2 = '127.0.0.3'
        socket2 = 20000

        create_device_proxies(server1, socket1, communicator, devices)
        create_device_proxies(server2, socket2, communicator, devices, 2)


        # #setup for server#1
       
        # fridge_proxy1 = communicator.stringToProxy(f"fridge/fridge_1:tcp -h {server1} -p 10000")
        # lights_proxy1 = communicator.stringToProxy(f"light/light_1:tcp -h {server1} -p 10000")
        # lights_proxy2 = communicator.stringToProxy(f"light/light_2:tcp -h {server1} -p 10000")
        # led_lights_proxy1 = communicator.stringToProxy(f"light/led_1:tcp -h {server1} -p 10000")

        # fridge1 = SmartHome.FridgePrx.checkedCast(fridge_proxy1)
        # lights1 = SmartHome.LightsPrx.checkedCast(lights_proxy1)
        # lights2 = SmartHome.LightsPrx.checkedCast(lights_proxy2)
        # led_lights1 = SmartHome.LEDLightsPrx.checkedCast(led_lights_proxy1)

        # #setup for server#2

        # fridge_proxy2 = communicator.stringToProxy(f"fridge/fridge_2:tcp -h {server2} -p 10000")
        # fridge_proxy3 = communicator.stringToProxy(f"fridge/fridge_3:tcp -h {server2} -p 10000")
        # lights_proxy3 = communicator.stringToProxy(f"light/light_3:tcp -h {server2} -p 10000")
        # led_lights_proxy2 = communicator.stringToProxy(f"light/led_2:tcp -h {server2} -p 10000")
        # led_lights_proxy3 = communicator.stringToProxy(f"light/led_3:tcp -h {server2} -p 10000")

        # fridge2 = SmartHome.FridgePrx.checkedCast(fridge_proxy2)
        # fridge3 = SmartHome.FridgePrx.checkedCast(fridge_proxy3)
        # lights3 = SmartHome.LightsPrx.checkedCast(lights_proxy3)
        # led_lights2 = SmartHome.LEDLightsPrx.checkedCast(led_lights_proxy2)
        # led_lights3 = SmartHome.LEDLightsPrx.checkedCast(led_lights_proxy3)



        # if not fridge or not lights or not led_lights:
        #     print("Unable to connect to object")
        #     sys.exit(1)

        devices["fridge1"].turnOn()
        devices["fridge3"].turnOn()



if __name__ == "__main__":
    main()