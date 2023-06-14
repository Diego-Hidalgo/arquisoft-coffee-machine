import com.zeroc.Ice.Current;
import servicios.*;

import java.util.ArrayList;
import java.util.List;

public class BrokerServiceImp implements BrokerService {

    List<AlarmaServicePrx> alarmas;
    List<VentaServicePrx> ventas;
    List<RecetaServicePrx> recetas;
    List<ServicioAbastecimientoPrx> abastecimientos;
    List<ServicioComLogisticaPrx> logisticas;

    public BrokerServiceImp() {
        alarmas = new ArrayList<>();
        ventas = new ArrayList<>();
        recetas = new ArrayList<>();
        abastecimientos = new ArrayList<>();
        logisticas = new ArrayList<>();
    }

    @Override
    public void subscribeAlarma(AlarmaServicePrx service, Current current) {
        System.out.println("Alarma Service Added!!!");
        alarmas.add(service);
    }

    @Override
    public void subscribeVenta(VentaServicePrx service, Current current) {
        ventas.add(service);
    }

    @Override
    public void subscribeReceta(RecetaServicePrx service, Current current) {
        recetas.add(service);
    }

    @Override
    public void subscribeAbastecimiento(ServicioAbastecimientoPrx service, Current current) {
        abastecimientos.add(service);
    }

    @Override
    public void subscribeLogistica(ServicioComLogisticaPrx service, Current current) {
        logisticas.add(service);
    }

    @Override
    public AlarmaServicePrx getAlarma(Current current) {
        return alarmas.get(0);
    }


}