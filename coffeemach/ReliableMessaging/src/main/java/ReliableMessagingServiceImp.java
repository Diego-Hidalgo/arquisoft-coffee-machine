import com.zeroc.Ice.Current;
import com.zeroc.Ice.*;

import java.net.ConnectException;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;

import servicios.AlarmaServicePrx;
import servicios.BrokerServicePrx;
import servicios.ReliableMessagingService;
import servicios.ReliableMessagingServicePrx;

public class ReliableMessagingServiceImp extends Thread implements ReliableMessagingService {

    private Communicator communicator;
    // private ReliableMessagingServicePrx rmDestiny;
    // private ReliableMessagingServicePrx rmOrigin;
    // private ReliableMessagingServicePrx rm;
    private Queue<String> alarmas;
    private BrokerServicePrx broker;
    private String address;
    private int port;   

    public ReliableMessagingServiceImp(String address, int port) {
        alarmas = new LinkedList<>();
        System.out.println(address + " - " + port);
        this.address = address;
        this.port = port;

    }

    public synchronized void setCommunicator(Communicator com) {
        communicator = com;
    }

    public void setBroker(BrokerServicePrx broker) {
        this.broker = broker;
    }

     @Override
    public void sendMessage(String message, Current current) {
        alarmas.add(message);
        System.out.println("I just added something!");
        System.out.println(message);

    }

    @Override
    public void receiveAlertMessage(String message, Current current) {
        alarmas.add(message);
        System.out.println("I just added something!");
        System.out.println(message);
       

    }

    private void sendAlertMessage(String message) {
        //Aquí se llama a la alarma dependiendo del tipo
        // Se deben hacer varios metodos de envio dependiendo del tipo de alarma porque cada una
        // recibe parametros diferentes
        // a parte del broker.getAlarma() se obtiene el metodo para informar
        System.out.println("Se recibe para enviar");
        System.out.println(broker.getAlarma());

    }

    @Override
    public void receiveEscasezIngrediente (String ing, int cod, Current current) {

    }

     private ReliableMessagingServicePrx createReliableMessagingProxy(String address, int port) {
        System.out.println("Test error!5");
        ReliableMessagingServicePrx rmProxy = null;

        String proxyString = "CM:tcp -h " + address + " -p " + port;
        ObjectPrx base = communicator.stringToProxy(proxyString);
        rmProxy = ReliableMessagingServicePrx.checkedCast(base);
        if (rmProxy == null) {
            throw new IllegalStateException("Error al crear el proxy de ReliableMessagingService");
        }

        return rmProxy;
    }

    @Override
    public void run() {
        /*
         * while (true) {
         * System.out.println("Sape");
         * try {
         * Thread.sleep(10000);
         * } catch (InterruptedException e) {
         * continue;
         * }
         * }
         */

        while (true) {
            String alarm = "";
            try {
                while (!alarmas.isEmpty()) {
                    alarm = alarmas.peek();
                    try {
                        System.out.println(alarm);
                        ReliableMessagingServicePrx rm = createReliableMessagingProxy(address, port);
                        rm.sendMessage(alarm);
                        alarmas.poll();
                        //sendAlertMessage(alarm);
                    } catch (ConnectionRefusedException e1) {
                        System.out.println("Error al enviar la alarma: " + e1.getMessage());
                        alarmas.offer(alarm);
                        alarmas.poll();
                        // Handle the exception, log it, or perform any other necessary actions
                    }
                    // Para demorarse medio segundo entre un envío de mensaje y otro, para que no
                    // envíe a millón
                    // Pues se consumen recursos.
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e15) {
                        System.out.println("Error al parar el tiempo: " + e15.getMessage());
                    }
                }
                Thread.yield();
            } catch (RuntimeException e2) {
                System.out.println("Error al procesar la cola de alarmas: " +
                        e2.getMessage());
                // Handle the exception, log it, or perform any other necessary actions
            }
        }

    }
}
