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
        void turnOn();
        void turnOff();
        string getState();
        DeviceInfo getInfo();
    };

    interface Fridge extends Device{
        void setTemperature(int temperature) throws InvalidCommand;
        int getTemperature();
    };

    interface Lights extends Device{
        void setBrightness(int brightness) throws InvalidCommand;
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
        void setColor(ColorPalette colour);
        ColorPalette getColor();
    };

};

#endif