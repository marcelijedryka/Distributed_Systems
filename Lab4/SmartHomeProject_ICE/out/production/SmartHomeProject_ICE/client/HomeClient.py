import Ice
import sys
from devices_ice import FridgePrx, LightsPrx, LEDLightsPrx, ColorPalette

def main():
    with Ice.CommunicatorHolder() as communicator:
        # Używając identyfikatorów do uzyskania proxy
        fridge_proxy = communicator.stringToProxy("fridge/fridge_1:tcp -h 127.0.0.2 -p 10000")
        # lights_proxy = communicator.stringToProxy("light/light_1:tcp -h 127.0.0.2 -p 10000")
        # led_lights_proxy = communicator.stringToProxy("light/led_1:tcp -h 127.0.0.2 -p 10000")

        # Castowanie na odpowiednie typy
        fridge = FridgePrx.checkedCast(fridge_proxy)
        # lights = LightsPrx.checkedCast(lights_proxy)
        # led_lights = LEDLightsPrx.checkedCast(led_lights_proxy)

        if not fridge:
            print("Nie udało się połączyć z jednym z obiektów.")
            sys.exit(1) 

        # if not fridge or not lights or not led_lights:
        #     print("Nie udało się połączyć z jednym z obiektów.")
        #     sys.exit(1)

        # Przykładowe operacje
        # Fridge
        fridge.turnOn()
        print("Fridge is on:", fridge.getState().isOn)

        # # Lights
        # lights.setBrightness(50)
        # print("Lights brightness set to 50")

        # # LED Lights
        # led_lights.setColor(ColorPalette.RED)
        # print("LED Lights set to red")

if __name__ == "__main__":
    main()