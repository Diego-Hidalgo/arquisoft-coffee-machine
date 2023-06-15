import com.zeroc.Ice.Current;
import com.zeroc.Ice.*;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;

import servicios.AlarmaServicePrx;
import servicios.ReliableMessagingService;
import servicios.ReliableMessagingServicePrx;

public class ReliableMessagingServiceImp extends Thread implements ReliableMessagingService {

    private Communicator communicator;
    //private ReliableMessagingServicePrx rmDestiny;
    //private ReliableMessagingServicePrx rmOrigin;
    //private ReliableMessagingServicePrx rm;
    private Queue<String> alarmas;
    
    public ReliableMessagingServiceImp(String address, String port/*ReliableMessagingServicePrx acknowledgmentRM, ReliableMessagingServicePrx serverRM*/) {
        alarmas = new LinkedList<>();
        System.out.println(address + " - " + port);
        //rmOrigin = acknowledgmentRM;
        //rmDestiny = serverRM;

    }

    public synchronized void setCommunicator(Communicator com) {
        communicator = com;
    }

    @Override
    public void sendMessage(String message, Current current) {
        alarmas.add(message);
        System.out.println("I just added something!");
        System.out.println(message);
        //rmOrigin.sendMessage("ack - " + message + " - Recibido");
    }

    @Override
    public String getAlarma(Current current) {
        return alarmas.peek();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Sape");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                continue;
            }
        }
        /*while (true) {
            String alarm = "";
            try {
                while (!alarmas.isEmpty()) {
                    alarm = alarmas.peek();
                    try {
                        rm.sendMessage(alarm);
                        alarmas.poll();
                    } catch (RuntimeException e1) {
                        System.out.println("Error al enviar la alarma: " + e1.getMessage());
                        alarmas.offer(alarm);
                        alarmas.poll();
                        // Handle the exception, log it, or perform any other necessary actions
                    }
                    // Para demorarse medio segundo entre un envío de mensaje y otro, para que no envíe a millón
                    // Pues se consumen recursos.
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e15) {
                        System.out.println("Error al parar el tiempo: " + e15.getMessage());
                    } 
                }
                Thread.yield();
            } catch (RuntimeException e2) {
                System.out.println("Error al procesar la cola de alarmas: " + e2.getMessage());
                // Handle the exception, log it, or perform any other necessary actions
            }
        }*/
    }
}
