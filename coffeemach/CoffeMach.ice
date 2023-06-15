
module servicios{

    sequence<string> StringArr;
    ["java:type:java.util.ArrayList<String>"] 
    sequence<string> StringSeq;
    ["java:serializable:java.util.Date"]
    sequence<byte> Date;

    dictionary<string,int> MapStrInt;

    interface ServicioComLogistica{

      StringSeq asignacionMaquina(int codigoOperador);
	  StringSeq asignacionMaquinasDesabastecidas(int codigoOperador);
	  bool inicioSesion(int codigoOperador, string password);
	  int petitionCount();
    }

    interface ServicioAbastecimiento {
	   void abastecer(int codMaquina, int tipoAlarma);
	   int petitionCount();
    }

    enum Moneda{
      CIEN, DOCIENTOS, QUINIENTOS
    }
  


    interface AlarmaService{
      void recibirNotificacionEscasezIngredientes(string iDing, int idMaq);
      void recibirNotificacionInsuficienciaMoneda(Moneda moneda, int idMaq);
      void recibirNotificacionEscasezSuministro(string idSumin, int idMaq);
      void recibirNotificacionAbastesimiento(int idMaq, string idInsumo, int cantidad );
      void recibirNotificacionMalFuncionamiento(int idMaq, string descri);
      int petitionCount();
    }

    interface VentaService{
      void registrarVenta(int codMaq, StringArr ventas);
      int petitionCount();
    }

    interface RecetaService{
      StringArr consultarIngredientes();
	  StringArr consultarRecetas();
      StringArr consultarProductos();
      void definirProducto(string nombre, int precio, MapStrInt ingredientes);

	  void borrarReceta(int cod);
	  void definirRecetaIngrediente(int idReceta, int idIngrediente,int valor);
	  string registrarReceta(string nombre, int precio);
	  string registrarIngrediente(string nombre);

	  int petitionCount();
    }

    interface BrokerService{
        void subscribeAlarma(AlarmaService* service);
        void subscribeVenta(VentaService* service);
        void subscribeReceta(RecetaService* service);
        void subscribeAbastecimiento(ServicioAbastecimiento* service);
        void subscribeLogistica(ServicioComLogistica* service);

        AlarmaService* getAlarma();
        VentaService* getVenta();
        RecetaService* getReceta();
        ServicioAbastecimiento* getAbastecimiento();
        ServicioComLogistica* getLogistica();
    }
    interface ReliableMessagingService {
      void receiveAlertMessage(string message);
      void receiveEscasezIngrediente(string ing, int cod);
    };

    interface BodegaService {
      void receiveMessages(string message);
    };

    interface LogisticService {
      void receiveMessage(string message);
    };

}