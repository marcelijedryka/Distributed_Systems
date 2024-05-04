package server;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import devs.FridgeI;
import devs.LEDLightsI;
import devs.LightsI;

public class HomeServerTwo {

    public void t1(String[] args) {
        int status = 0;
        Communicator communicator = null;

        try {
            communicator = Util.initialize(args);
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("Adapter2", "tcp -h 127.0.0.3 -p 20000 -z : udp -h 127.0.0.3 -p 20000 -z");

            String fridgeInfo = "Cooling and food preserving device";
            String lightInfo = "Electric device with remote brightness change feature";
            String LEDInfo = "Electric device with remote brightness and colour change feature";

            FridgeI fridgeServant3 = new FridgeI("Fridge3" , "Kitchen", fridgeInfo);
            FridgeI fridgeServant4 = new FridgeI("Fridge4" , "Garage", fridgeInfo);

            LightsI lightServant3 = new LightsI("Light3" , "Living Room", lightInfo);
            LightsI lightServant4 = new LightsI("Light4" , "Bedroom", lightInfo);

            LEDLightsI LEDServant3 = new LEDLightsI("LED3" , "Office", LEDInfo);
            LEDLightsI LEDServant4 = new LEDLightsI("LED4" , "Office", LEDInfo);

            adapter.add(fridgeServant3, new Identity("fridge_3", "fridge"));
            adapter.add(fridgeServant4, new Identity("fridge_4", "fridge"));

            adapter.add(lightServant3, new Identity("light_3", "light"));
            adapter.add(lightServant4, new Identity("light_4", "light"));

            adapter.add(LEDServant3, new Identity("led_3", "light"));
            adapter.add(LEDServant4, new Identity("led_4", "light"));

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
        HomeServerTwo server = new HomeServerTwo();
        server.t1(args);
    }

}
