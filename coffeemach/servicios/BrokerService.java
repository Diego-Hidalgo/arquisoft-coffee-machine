//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.8
//
// <auto-generated>
//
// Generated from file `CoffeMach.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package servicios;

public interface BrokerService extends com.zeroc.Ice.Object
{
    void subscribeAlarma(AlarmaServicePrx service, com.zeroc.Ice.Current current);

    void subscribeVenta(VentaServicePrx service, com.zeroc.Ice.Current current);

    void subscribeReceta(RecetaServicePrx service, com.zeroc.Ice.Current current);

    void subscribeAbastecimiento(ServicioAbastecimientoPrx service, com.zeroc.Ice.Current current);

    void subscribeLogistica(ServicioComLogisticaPrx service, com.zeroc.Ice.Current current);

    AlarmaServicePrx getAlarma(com.zeroc.Ice.Current current);

    /** @hidden */
    static final String[] _iceIds =
    {
        "::Ice::Object",
        "::servicios::BrokerService"
    };

    @Override
    default String[] ice_ids(com.zeroc.Ice.Current current)
    {
        return _iceIds;
    }

    @Override
    default String ice_id(com.zeroc.Ice.Current current)
    {
        return ice_staticId();
    }

    static String ice_staticId()
    {
        return "::servicios::BrokerService";
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_subscribeAlarma(BrokerService obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        AlarmaServicePrx iceP_service;
        iceP_service = AlarmaServicePrx.uncheckedCast(istr.readProxy());
        inS.endReadParams();
        obj.subscribeAlarma(iceP_service, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_subscribeVenta(BrokerService obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        VentaServicePrx iceP_service;
        iceP_service = VentaServicePrx.uncheckedCast(istr.readProxy());
        inS.endReadParams();
        obj.subscribeVenta(iceP_service, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_subscribeReceta(BrokerService obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        RecetaServicePrx iceP_service;
        iceP_service = RecetaServicePrx.uncheckedCast(istr.readProxy());
        inS.endReadParams();
        obj.subscribeReceta(iceP_service, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_subscribeAbastecimiento(BrokerService obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        ServicioAbastecimientoPrx iceP_service;
        iceP_service = ServicioAbastecimientoPrx.uncheckedCast(istr.readProxy());
        inS.endReadParams();
        obj.subscribeAbastecimiento(iceP_service, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_subscribeLogistica(BrokerService obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        ServicioComLogisticaPrx iceP_service;
        iceP_service = ServicioComLogisticaPrx.uncheckedCast(istr.readProxy());
        inS.endReadParams();
        obj.subscribeLogistica(iceP_service, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_getAlarma(BrokerService obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        inS.readEmptyParams();
        AlarmaServicePrx ret = obj.getAlarma(current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeProxy(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /** @hidden */
    final static String[] _iceOps =
    {
        "getAlarma",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "subscribeAbastecimiento",
        "subscribeAlarma",
        "subscribeLogistica",
        "subscribeReceta",
        "subscribeVenta"
    };

    /** @hidden */
    @Override
    default java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceDispatch(com.zeroc.IceInternal.Incoming in, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        int pos = java.util.Arrays.binarySearch(_iceOps, current.operation);
        if(pos < 0)
        {
            throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return _iceD_getAlarma(this, in, current);
            }
            case 1:
            {
                return com.zeroc.Ice.Object._iceD_ice_id(this, in, current);
            }
            case 2:
            {
                return com.zeroc.Ice.Object._iceD_ice_ids(this, in, current);
            }
            case 3:
            {
                return com.zeroc.Ice.Object._iceD_ice_isA(this, in, current);
            }
            case 4:
            {
                return com.zeroc.Ice.Object._iceD_ice_ping(this, in, current);
            }
            case 5:
            {
                return _iceD_subscribeAbastecimiento(this, in, current);
            }
            case 6:
            {
                return _iceD_subscribeAlarma(this, in, current);
            }
            case 7:
            {
                return _iceD_subscribeLogistica(this, in, current);
            }
            case 8:
            {
                return _iceD_subscribeReceta(this, in, current);
            }
            case 9:
            {
                return _iceD_subscribeVenta(this, in, current);
            }
        }

        assert(false);
        throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
    }
}
