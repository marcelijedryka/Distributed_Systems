package server;
import com.zeroc.Ice.*;
import devs.*;

import java.lang.Exception;

public class HomeServerOne {

    public void t1(String[] args) {
        int status = 0;
        Communicator communicator = null;

        try {
            communicator = Util.initialize(args);
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("Adapter2", "tcp -h 127.0.0.2 -p 10000 -z : udp -h 127.0.0.2 -p 10000 -z");

            String fridgeInfo = "Cooling and food preserving device";
            String lightInfo = "Electric device with remote brightness change feature";
            String LEDInfo = "Electric device with remote brightness and colour change feature";

            FridgeI fridgeServant1 = new FridgeI("Fridge1" , "Kitchen", fridgeInfo);
            FridgeI fridgeServant2 = new FridgeI("Fridge2" , "Garage", fridgeInfo);

            LightsI lightServant1 = new LightsI("Light1" , "Living Room", lightInfo);
            LightsI lightServant2 = new LightsI("Light2" , "Bedroom", lightInfo);

            LEDLightsI LEDServant1 = new LEDLightsI("LED1" , "Office", LEDInfo);
            LEDLightsI LEDServant2 = new LEDLightsI("LED1" , "Office", LEDInfo);

            adapter.add(fridgeServant1, new Identity("fridge_1", "fridge"));
            adapter.add(fridgeServant2, new Identity("fridge_2", "fridge"));

            adapter.add(lightServant1, new Identity("light_1", "light"));
            adapter.add(lightServant2, new Identity("light_2", "light"));

            adapter.add(LEDServant1, new Identity("led_1", "light"));
            adapter.add(LEDServant2, new Identity("led_2", "light"));

            adapter.activate();

            System.out.println("Entering event processing loop...");

            communicator.waitForShutdown();

        } catch (Exception e) {
            e.printStackTrace(System.err);
            status = 1;
        }
        if (communicator != null) {
            try {
                communicator.destroy();
            } catch (Exception e) {
                e.printStackTrace(System.err);
                status = 1;
            }
        }
        System.exit(status);
    }

    public static void main(String[] args)
    {
        HomeServerOne server = new HomeServerOne();
        server.t1(args);
    }
}
