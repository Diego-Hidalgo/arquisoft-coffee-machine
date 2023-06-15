package interfaz;

import servicios.*;

import com.zeroc.Ice.Current;

public class ControladorMensajes extends Thread implements ReliableMessagingService {
	
	@Override
	public void run() {
        while (true) {
            //System.out.println("Escuchando...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		

	}


    @Override
    public void receiveAlertMessage(String message, Current current) {

    }
}
