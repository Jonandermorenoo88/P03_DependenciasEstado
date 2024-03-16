package p03.c01;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Sistema encargado de ejecutar el programa recogiendo para ello los
 * argumentos de programa. Este argumento se empleará para lanzar más o
 * menos tareas de entrada/salida del parque. 
 * 
 * Para su ejecución se ha decidido utilizar un cached thread pool, pues
 * es un método suficientemente flexible para gestionar tantos hilos como
 * se necesiten.
 * 
 * @author Adrián Zamora Sánchez
 * @author Jon Ander Incera Moreno
 */
public class SistemaLanzador {
	public static void main(String[] args) {
		// Se instancia un nuevo adaptador sincronizado para el
		IParque parque = AdaptadorParqueSincronizado.getInstancia();
		
		// Se declara la primera letra identificativa de las puertas
		char letra_puerta = 'A';

		// Se instancia el cache thread pool
		Executor exec = Executors.newCachedThreadPool();
		
		// Este bucle genera tantos hilos como el número pasado como argumento al programa
		for (int i = 0; i < Integer.parseInt(args[0]); i++) { 
			//Formato de los nombres de las puertas
			String puerta = "" + ((char) (letra_puerta++));

			// Se ejecuta una nueva actividad de entrada y de salida del parque
			exec.execute(new ActividadEntradaPuerta(puerta, parque));
			exec.execute(new ActividadSalidaPuerta(puerta, parque));
			
		}
	}
}