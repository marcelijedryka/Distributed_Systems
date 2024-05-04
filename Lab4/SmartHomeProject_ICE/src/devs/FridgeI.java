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
    public void setTemperature(int temperature, Current current) throws InvalidCommand {
        if(temperature < 0 || temperature > 20){
            throw new SmartHome.InvalidCommand();
        }
        this.temperature = temperature;
        System.out.println( name + " temperature has been set on device");
    }

    @Override
    public int getTemperature(Current current) {
        return this.temperature;
    }
}
