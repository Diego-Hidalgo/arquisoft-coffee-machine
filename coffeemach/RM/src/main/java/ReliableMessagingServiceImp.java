import com.zeroc.Ice.Current;
import java.util.ArrayList;

import servicios.AlarmaServicePrx;
import servicios.ReliableMessagingService;

public class ReliableMessagingServiceImp implements ReliableMessagingService{
    
    ArrayList<AlarmaServicePrx> alarmas;

    public ReliableMessagingServiceImp() {
        alarmas = new ArrayList<>();
    }
    
    @Override
    public void suscribeClient(AlarmaServicePrx service, Current current) {

    }

    @Override
    public void suscribeReceiver(AlarmaServicePrx service, Current current){

    }

    @Override
    public void sendMessage(String message, Current current) {

    }

    @Override
    public void receiveMessage(String message, Current current) {
        
    }

    @Override
    public void persistMessage(String message, Current current) {

    }

    @Override
    public AlarmaServicePrx getAlarma(Current current) {
        return alarmas.get(0);
    }
}
