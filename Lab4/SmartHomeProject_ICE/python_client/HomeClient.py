import Ice
import sys
import os

sys.path.append(os.path.join(os.path.dirname(__file__), "..", "generated_py"))
import SmartHome


def create_device_proxies(ip,socket, communicator, devices, shift = 0):

    device_types = {
        "fridge": 2, 
        "light": 2, 
        "led": 2
    }

    for device_type, count in device_types.items():
        for i in range(1 + shift, count + 1 + shift):
            key = f"{device_type}{i}"
        
            if key in devices:
                continue
            if device_type == 'led':
                proxy = communicator.stringToProxy(f"light/{device_type}_{i}:tcp -h {ip} -p {socket}")
            else:  
                proxy = communicator.stringToProxy(f"{device_type}/{device_type}_{i}:tcp -h {ip} -p {socket}")
            if device_type == "fridge":
                casted_proxy = SmartHome.FridgePrx.checkedCast(proxy)
            elif device_type == "light":
                casted_proxy = SmartHome.LightsPrx.checkedCast(proxy)
            elif device_type == "led":
                casted_proxy = SmartHome.LEDLightsPrx.checkedCast(proxy)

            devices[f"{key}"] = casted_proxy

def handle_command(device, parts):
    if parts[1] == "on":
        print(device.turnOn())
    elif parts[1] == 'off':
        print(device.turnOff())
    elif parts[1] == 'info':
         print("------------")
         print(f"Name:{device.getInfo().name} \nLocation:{device.getInfo().location}\nInformations:{device.getInfo().informations}")
         print("------------")
    elif parts[1] == 'state':
        print(device.getState())
    elif parts[0][:6] == 'fridge' and parts[1] == 'set' and len(parts) == 3:
        print(device.setTemperature(int(parts[2])))
    elif parts[0][:6] == 'fridge' and parts[1] == 'temperature':
        print(device.getTemperature())
    elif (parts[0][:5] == 'light' or parts[0][:3] == 'led') and parts[1] == 'set' and len(parts) == 3:
        print(device.setBrightness(int(parts[2])))
    elif (parts[0][:5] == 'light' or parts[0][:3] == 'led') and parts[1] == 'brightness':
        print(device.getBrightness())
    elif parts[0][:3] == 'led' and parts[1] == 'change' and len(parts) == 3:
        if parts[2] == 'red':
            print(device.setColor(SmartHome.ColorPalette.RED))
        elif parts[2] == 'blue':
            print(device.setColor(SmartHome.ColorPalette.BLUE))
        elif parts[2] == 'green':
            print(device.setColor(SmartHome.ColorPalette.GREEN))
        elif parts[2] == 'white':
            print(device.setColor(SmartHome.ColorPalette.WHITE))
        elif parts[2] == 'violet':
            print(device.setColor(SmartHome.ColorPalette.VIOLET))
        elif parts[2] == 'yellow':
            print(device.setColor(SmartHome.ColorPalette.YELLOW))
        elif parts[2] == 'orange':
            print(device.setColor(SmartHome.ColorPalette.ORANGE))
        else:
            print("Led light cannot change to value you have inserted")

    elif parts[0][:3] == 'led' and parts[1] == 'color':
        color = device.getColor()
        print(f"Current led's color is {color}")
    else:
        print("Command you have provided is invalid")

def main():
    with  Ice.initialize(sys.argv) as communicator:

        devices = {}

        server1 = '127.0.0.2'
        socket1 = 10000
        server2 = '127.0.0.3'
        socket2 = 20000

        create_device_proxies(server1, socket1, communicator, devices)
        create_device_proxies(server2, socket2, communicator, devices, 2)

        # print(devices)

        while True:

            try:
                line = input("Please insert command: ")
                parts = line.split(" ")
            

                if parts[0] == 'show':
                    for name, device in devices.items():
                        print("------------")
                        print(f"Name:{device.getInfo().name} \nLocation:{device.getInfo().location}\nInformations:{device.getInfo().informations}")
                        print("------------")
                elif parts[0] == 'exit':
                    break
                elif parts[0] in devices:
                    handle_command(devices[parts[0]], parts)

            except Exception as e:
                    print(f"Error: {e}")






if __name__ == "__main__":
    main()