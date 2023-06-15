import com.zeroc.Ice.Current;
import servicios.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        alarmas.add(service);
    }

    @Override
    public void subscribeVenta(VentaServicePrx service, Current current) {
        ventas.add(service);
    }

    @Override
    public void subscribeReceta(RecetaServicePrx service, Current current) {
        recetas.add(service);
        System.out.println(recetas.size());
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
        Optional<AlarmaServicePrx> optional = alarmas.stream()
                .min((a1, a2) -> Integer.compare(a1.petitionCount(), a2.petitionCount()));
        return optional.orElse(null);
    }

    @Override
    public VentaServicePrx getVenta(Current current) {
        Optional<VentaServicePrx> optional = ventas.stream()
                .min((a1, a2) -> Integer.compare(a1.petitionCount(), a2.petitionCount()));
        return optional.orElse(null);
    }

    @Override
    public RecetaServicePrx getReceta(Current current) {
        Optional<RecetaServicePrx> optional = recetas.stream()
                .min((a1, a2) -> Integer.compare(a1.petitionCount(), a2.petitionCount()));
        return optional.orElse(null);
    }

    @Override
    public ServicioAbastecimientoPrx getAbastecimiento(Current current) {
        Optional<ServicioAbastecimientoPrx> optional = abastecimientos.stream()
                .min((a1, a2) -> Integer.compare(a1.petitionCount(), a2.petitionCount()));
        return optional.orElse(null);
    }

    @Override
    public ServicioComLogisticaPrx getLogistica(Current current) {
        Optional<ServicioComLogisticaPrx> optional = logisticas.stream()
                .min((a1, a2) -> Integer.compare(a1.petitionCount(), a2.petitionCount()));
        return optional.orElse(null);
    }


}