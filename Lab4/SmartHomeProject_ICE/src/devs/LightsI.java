package devs;

import SmartHome.DeviceNotFound;
import SmartHome.InvalidCommand;
import SmartHome.Lights;
import com.zeroc.Ice.Current;

public class LightsI extends DeviceI implements Lights {

    private int brightness;
    private String name;

    public LightsI(String name, String location, String info) {
        super(name, location, info);
        this.brightness = 0;
        this.name = name;
    }

    @Override
    public void setBrightness(int brightness, Current current) throws InvalidCommand {
        if(brightness < 0 || brightness > 100){
            throw new InvalidCommand();
        }
        this.brightness = brightness;
        System.out.println(name + " brightness has been changed to " + Integer.toString(brightness));
    }

    @Override
    public String getBrightness(Current current) {
        return Integer.toString(brightness);
    }

    public int getBrightness() {
        return brightness;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public void turnOff(Current current) {
        super.turnOff(current);
        this.brightness = 0;
    }
}
