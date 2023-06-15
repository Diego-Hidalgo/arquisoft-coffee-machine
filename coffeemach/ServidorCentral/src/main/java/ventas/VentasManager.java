package ventas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;

import modelo.ConexionBD;
import modelo.ManejadorDatos;
import modelo.VentasMaquina;
import modelo.VentasReceta;
import servicios.VentaService;
import tracker.PetitionTrackerImp;

public class VentasManager implements VentaService {

    private Communicator communicator;
    private PetitionTrackerImp petitionTrackerImp;

    public VentasManager(PetitionTrackerImp petitionTrackerImp) {
        this.petitionTrackerImp = petitionTrackerImp;
    }

    /**
     * @param communicator the communicator to set
     */
    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public void registrarVenta(int codMaq, String[] ventas, Current current) {
        petitionTrackerImp.increaseCount();
        reporteVentas(codMaq, ventas);
        petitionTrackerImp.decreaseCount();
    }

    @Override
    public int petitionCount(Current current) {
        return petitionTrackerImp.getCount();
    }

    public void reporteVentas(int idMaquina, String[] detalle) {
        ConexionBD cbd = new ConexionBD(communicator);
        cbd.conectarBaseDatos();
        ManejadorDatos md = new ManejadorDatos();
        md.setConexion(cbd.getConnection());

        VentasMaquina vM = new VentasMaquina();
        vM.setFechaInicial(new Date());
        vM.setFechaFinal(new Date());
        vM.setIdMaquina(idMaquina);

        List<VentasReceta> vR = new ArrayList<VentasReceta>();

        for (String dato : detalle) {

            String[] div = dato.split("#");
            VentasReceta vRTemp = new VentasReceta();
            vRTemp.setIdReceta(Integer.parseInt(div[0]));

            vRTemp.setValorreceta(Integer.parseInt(div[1]));

            vR.add(vRTemp);
        }

        vM.setDetalle(vR);
        vM.setValor(0);

        md.registrarReporteVentas(vM);
        cbd.cerrarConexion();
    }

}
