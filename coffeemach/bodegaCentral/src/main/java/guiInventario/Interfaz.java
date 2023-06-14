package guiInventario;

import java.util.Scanner;

import bodega.Bodega;
import mantenimientoExistencias.Inventario;

public class Interfaz implements Runnable{

    private Inventario inventario;
    private Bodega bodega;
	private Communicator communicator;

	public Interfaz(Communicator com) {
		communicator = com;
	}

	
	/*public Interfaz(Inventario inventario, Bodega bodega)
	{
		this.inventario = inventario;
        this.bodega = bodega;ca
	}*/

	@Override
	public void run() 
	{
		
		while(true)
		{
			Scanner lector=new Scanner(System.in);
			
			System.out.println("*************************//");
			System.out.println("Consola de Bodega Central");
			System.out.println("*************************//");
			
			System.out.println("\nOpciones:" +
					"\n1.Ver todos los mensajes:");
			int valor=lector.nextInt();
			
			switch(valor)
			{
			case 1:

				break;
			default:
				System.out.println("¡¡¡Opción incorrecta seleccionada!!!");
				break;
			}
		}
		
	}
}
