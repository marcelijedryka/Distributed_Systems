#ifndef DEV_ICE
#define DEV_ICE

module SmartHome{
    exception InvalidCommand {};

    struct DeviceInfo{
        string name;
        string location;
        string informations;
    };

    interface Device{
        string turnOn();
        string turnOff();
        string getState();
        DeviceInfo getInfo();
    };

    interface Fridge extends Device{
        string setTemperature(int temperature) throws InvalidCommand;
        string getTemperature();
    };

    interface Lights extends Device{
        string setBrightness(int brightness) throws InvalidCommand;
        string getBrightness();
    };

    enum ColorPalette{
        RED,
        BLUE,
        GREEN,
        WHITE,
        VIOLET,
        YELLOW,
        ORANGE
    };

    interface LEDLights extends Lights{
        string setColor(ColorPalette colour);
        ColorPalette getColor();
    };

};

#endif