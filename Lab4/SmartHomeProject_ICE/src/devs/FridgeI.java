package devs;

import SmartHome.*;
import com.zeroc.Ice.*;

public class FridgeI extends DeviceI implements Fridge{

    int temperature = 4; //default temperature
    String name;

    public FridgeI(String name, String location, String info) {
        super(name, location, info);
        this.name = name;
    }

    @Override
    public String setTemperature(int temperature, Current current) throws InvalidCommand {
        if(temperature < 0 || temperature > 20){
            throw new SmartHome.InvalidCommand();
        }
        this.temperature = temperature;
        System.out.println( name + " temperature has been set on device");
        return "New temperature has been set";
    }

    @Override
    public String getTemperature(Current current) {
        return "Temperature in this device is set to " + Integer.toString(temperature);
    }
}
