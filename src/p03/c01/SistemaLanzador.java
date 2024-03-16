package p03.c01;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Adrián Zamora Sánchez
 * @author Jon Ander Incera Moreno
 */
public class SistemaLanzador {

	public static void main(String[] args) {

		IParque parque = AdaptadorParqueSincronizado.getInstancia();
		char letra_puerta = 'A';

		Executor exec = Executors.newCachedThreadPool();
		
		for (int i = 0; i < Integer.parseInt(args[0]); i++) { // Bucle que recoge las entradas por argumento

			String puerta = "" + ((char) (letra_puerta++));

			exec.execute(new ActividadEntradaPuerta(puerta, parque));
			exec.execute(new ActividadSalidaPuerta(puerta, parque));
			
		}
	}
}