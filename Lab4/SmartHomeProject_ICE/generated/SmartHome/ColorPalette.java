//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.10
//
// <auto-generated>
//
// Generated from file `devices.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package SmartHome;

public enum ColorPalette implements java.io.Serializable
{
    RED(0),
    BLUE(1),
    GREEN(2),
    WHITE(3),
    VIOLET(4),
    YELLOW(5),
    ORANGE(6);

    public int value()
    {
        return _value;
    }

    public static ColorPalette valueOf(int v)
    {
        switch(v)
        {
        case 0:
            return RED;
        case 1:
            return BLUE;
        case 2:
            return GREEN;
        case 3:
            return WHITE;
        case 4:
            return VIOLET;
        case 5:
            return YELLOW;
        case 6:
            return ORANGE;
        }
        return null;
    }

    private ColorPalette(int v)
    {
        _value = v;
    }

    public void ice_write(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeEnum(_value, 6);
    }

    public static void ice_write(com.zeroc.Ice.OutputStream ostr, ColorPalette v)
    {
        if(v == null)
        {
            ostr.writeEnum(SmartHome.ColorPalette.RED.value(), 6);
        }
        else
        {
            ostr.writeEnum(v.value(), 6);
        }
    }

    public static ColorPalette ice_read(com.zeroc.Ice.InputStream istr)
    {
        int v = istr.readEnum(6);
        return validate(v);
    }

    public static void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<ColorPalette> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    public static void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, ColorPalette v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.Size))
        {
            ice_write(ostr, v);
        }
    }

    public static java.util.Optional<ColorPalette> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.Size))
        {
            return java.util.Optional.of(ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static ColorPalette validate(int v)
    {
        final ColorPalette e = valueOf(v);
        if(e == null)
        {
            throw new com.zeroc.Ice.MarshalException("enumerator value " + v + " is out of range");
        }
        return e;
    }

    private final int _value;
}
