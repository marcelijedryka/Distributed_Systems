package devs;
import SmartHome.*;
import com.zeroc.Ice.*;

public class DeviceI implements Device{

    private DeviceInfo deviceInfo;
    private boolean isOn;

    public DeviceI(String name, String location, String info){
        this.isOn = false;
        this. deviceInfo = new DeviceInfo(name, location, info);
    }

    @Override
    public void turnOn(Current current){
        if(!isOn){
            isOn = true;
            System.out.println(deviceInfo.name + " has been turned on");
        }
    }

    @Override
    public void turnOff(Current current){
        if(isOn){
            isOn = false;
            System.out.println(deviceInfo.name + " has been turned off");
        }
    }

    @Override
    public String getState(Current current) {
        if(isOn){
            return "Device is running";
        }
        return "Device is not running";
    }

    @Override
    public DeviceInfo getInfo(Current current) {
        return deviceInfo;
    }
    public DeviceInfo getInfo() {
        return deviceInfo;
    }
}
