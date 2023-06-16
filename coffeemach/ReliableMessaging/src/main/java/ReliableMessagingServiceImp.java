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
import com.zeroc.Ice.ObjectPrx;

public class ReliableMessagingServiceImp extends Thread implements ReliableMessagingService {

    private Communicator communicator;
    // private ReliableMessagingServicePrx rmDestiny;
    // private ReliableMessagingServicePrx rmOrigin;
    // private ReliableMessagingServicePrx rm;
    private Queue<String> alarmas;
    private Queue<String> escasezIng;
    private BrokerServicePrx broker;

    public ReliableMessagingServiceImp() {
        alarmas = new LinkedList<>();
        escasezIng = new LinkedList<>();
    }

    public synchronized void setCommunicator(Communicator com) {
        communicator = com;
    }

    public void setBroker(BrokerServicePrx broker) {
        this.broker = broker;
    }

    @Override
    public void receiveAlertMessage(String message, Current current) {
        System.out.println("Test error!4");
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

    @Override
    public void receiveEscasezIngrediente(String ing, int cod, Current current) {
        // los parametros recibidos se tienen que guardar en algún lado
        escasezIng.add(ing +"-"+cod);
    }

    private void sendEscasezIngredientes(String msg) {
        String ing = msg.split("-")[0];
        int cod = Integer.parseInt(msg.split("-")[1]);
        broker.getAlarma().recibirNotificacionEscasezIngredientes(ing, cod);
    }

    @Override
    public void receiveEscasezSuministro(String idSumin, int idMaq, Current current) {

    }

    private void sendEscasezSuministro(String msg) {

    }

    @Override
    public void receiveMalFuncionamiento(int idMaq, String descri, Current current) {

    }

    private void sendMalFuncionamiento(String msg) {

    }

    private void sendAlertMessage(String message) {
        //Aquí se llama a la alarma dependiendo del tipo
        // Se deben hacer varios metodos de envio dependiendo del tipo de alarma porque cada una
        // recibe parametros diferentes
        // a parte del broker.getAlarma() se obtiene el metodo para informar
        System.out.println("Se recibe para enviar");
        broker.getAlarma();
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
            String alarmIng = "";
            try {
                while (!alarmas.isEmpty() || !escasezIng.isEmpty()) {
                    alarm = alarmas.peek();
                    alarmIng = escasezIng.peek();
                    try {
                        System.out.println(alarm);
                        //sendAlertMessage(alarm);
                        sendEscasezIngredientes(alarmIng);
                        escasezIng.poll();
                        //alarmas.poll();
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
