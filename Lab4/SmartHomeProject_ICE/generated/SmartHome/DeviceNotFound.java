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

public class DeviceNotFound extends com.zeroc.Ice.UserException
{
    public DeviceNotFound()
    {
    }

    public DeviceNotFound(Throwable cause)
    {
        super(cause);
    }

    public String ice_id()
    {
        return "::SmartHome::DeviceNotFound";
    }

    /** @hidden */
    @Override
    protected void _writeImpl(com.zeroc.Ice.OutputStream ostr_)
    {
        ostr_.startSlice("::SmartHome::DeviceNotFound", -1, true);
        ostr_.endSlice();
    }

    /** @hidden */
    @Override
    protected void _readImpl(com.zeroc.Ice.InputStream istr_)
    {
        istr_.startSlice();
        istr_.endSlice();
    }

    /** @hidden */
    public static final long serialVersionUID = 1732783594L;
}
