package devs;

import SmartHome.ColorPalette;
import SmartHome.InvalidCommand;
import SmartHome.LEDLights;
import com.zeroc.Ice.Current;

public class LEDLightsI extends DeviceI implements LEDLights {

    private ColorPalette colour;
    private int brightness;
    public LEDLightsI(String name, String location, String info) {
        super(name, location, info);
        this.colour = ColorPalette.WHITE;
        this.brightness = 0;
    }

    @Override
    public String setColor(ColorPalette colour, Current current){
        this.colour = colour;
        System.out.println(getInfo().name + " has changed colour");
        return "Colour has been changed";
    }

    @Override
    public ColorPalette getColor(Current current) {
        return colour;
    }

    @Override
    public String setBrightness(int brightness, Current current) throws InvalidCommand {
        if(brightness < 0 || brightness > 100){
            throw new InvalidCommand();
        }
        this.brightness = brightness;
        System.out.println(getInfo().name + " brightness has been changed to " + Integer.toString(brightness));
        return "Device's brightness has been set to " + Integer.toString(this.brightness);
    }

    @Override
    public String getBrightness(Current current) {
        return "Device's brightness is " + Integer.toString(brightness);
    }
}
