import com.zeroc.Ice.Current;
import com.zeroc.Ice.*;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;

import servicios.AlarmaServicePrx;
import servicios.ReliableMessagingService;
import servicios.ReliableMessagingServicePrx;
import com.zeroc.Ice.ObjectPrx;


public class ReliableMessagingServiceImp extends Thread implements ReliableMessagingService {

    private Communicator communicator;
    // private ReliableMessagingServicePrx rmDestiny;
    // private ReliableMessagingServicePrx rmOrigin;
    // private ReliableMessagingServicePrx rm;
    private Queue<String> alarmas;
    private String address;
    private int port;

    public ReliableMessagingServiceImp(String address, int port) {
        alarmas = new LinkedList<>();
        System.out.println(address + " - " + port);
        this.address = address;
        this.port = port;
        // rmOrigin = acknowledgmentRM;
        // rmDestiny = serverRM;

    }

    public synchronized void setCommunicator(Communicator com) {
        communicator = com;
    }

    @Override
    public void sendMessage(String message, Current current) {
        alarmas.add(message);
        System.out.println("I just added something!");
        System.out.println(message);
        /*
         * System.out.println(current.con);
         * Connection connection = current.con;
         * ConnectionInfo cf = connection.getInfo();
         * IPConnectionInfo ipCf = (IPConnectionInfo) cf;
         * String localAddress = ipCf.localAddress;
         * int localPort = ipCf.localPort;
         * System.out.println(ipCf.localAddress);
         * System.out.println(ipCf.localPort);
         * 
         * String proxyString = "CoffeMach:tcp -h " + localAddress + " -p " + localPort;
         * rmOrigin =
         * ReliableMessagingServicePrx.checkedCast(communicator.stringToProxy(
         * proxyString));
         * 
         * 
         * rmOrigin.sendMessage("ack - " + message + " - Recibido");
         */

    }

    private ReliableMessagingServicePrx createReliableMessagingProxy(String address, int port) {
        String proxyString = "CM:tcp -h " + address + " -p " + port;
        ObjectPrx base = communicator.stringToProxy(proxyString);
        ReliableMessagingServicePrx rmProxy = ReliableMessagingServicePrx.checkedCast(base);
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
                        ReliableMessagingServicePrx rm = createReliableMessagingProxy(address, port);
                        rm.sendMessage(alarm);
                        alarmas.poll();
                    } catch (RuntimeException e1) {
                        System.out.println("Error al enviar la alarma: " + e1.getMessage());
                        alarmas.offer(alarm);
                        alarmas.poll();
                        // Handle the exception, log it, or perform any other necessary actions
                    }
                    // Para demorarse medio segundo entre un envío de mensaje y otro, para que no
                    // envíe a millón
                    // Pues se consumen recursos.
                    try {
                        Thread.sleep(500);
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
