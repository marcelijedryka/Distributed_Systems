package client;

import SmartHome.*;
import com.zeroc.Ice.*;

import java.lang.Exception;

public class HomeClient {

    public static void main(String[] args){
        int status = 0;
        Communicator communicator = null;

        try{
            communicator = Util.initialize(args);

            ObjectPrx fridgeProxy_1 = communicator.stringToProxy("fridge/fridge_1" + ":tcp -h 127.0.0.2 -p 10000 -z : udp -h 127.0.0.2 -p 10000 -z");
            ObjectPrx lightProxy1 = communicator.stringToProxy("light/light_1" + ":tcp -h 127.0.0.2 -p 10000 -z : udp -h 127.0.0.2 -p 10000 -z");
            ObjectPrx ledProxy1 = communicator.stringToProxy("light/led_1" + ":tcp -h 127.0.0.2 -p 10000 -z : udp -h 127.0.0.2 -p 10000 -z");

            FridgePrx fridge_1 = FridgePrx.checkedCast(fridgeProxy_1);
            LightsPrx light_1 = LightsPrx.checkedCast(lightProxy1);
            LEDLightsPrx led_1 = LEDLightsPrx.checkedCast(ledProxy1);

            if (fridge_1 == null || light_1 == null || led_1 == null) {
                System.err.println("Unable to get one of proxies.");
                return;
            }

            fridge_1.turnOn();
            fridge_1.setTemperature(8);
            System.out.println("Temperature in " + fridge_1.getInfo().name+ " is: " + fridge_1.getTemperature());

            light_1.turnOn();
            System.out.println("Brightness of " + light_1.getInfo().name + " is: " + light_1.getBrightness());
            light_1.setBrightness(80);
            System.out.println("Brightness of " + light_1.getInfo().name + " is: " + light_1.getBrightness());
            light_1.turnOff();

            led_1.turnOn();
            System.out.println("Colour of " + led_1.getInfo().name + " is: " + led_1.getColor());
            led_1.setColor(ColorPalette.YELLOW);
            System.out.println("Colour of " + led_1.getInfo().name + " is: " + led_1.getColor());
            led_1.turnOff();

         }catch (LocalException e) {
            e.printStackTrace();
            status = 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            status = 1;
        }
        if (communicator != null) { //clean
            try {
                communicator.destroy();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                status = 1;
            }
        }
        System.exit(status);
        }
}

