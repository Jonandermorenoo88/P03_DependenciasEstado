package p03.c01;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 
 * Clase Actividad Entrada Puerta. Esta clase representa una actividad de
 * entrada a través de una puerta en un parque. Implementa la interfaz Runnable
 * para poder ser ejecutada en un hilo de ejecución.
 * 
 * @author Adrián Zamora Sánchez
 * @author Jon Ander Incera Moreno
 */

public class ActividadEntradaPuerta implements Runnable {

	// Atributos de la clase
	private static final int NUMENTRADAS = 20;
	private String puerta;
	private IParque parque;

	/**
	 * Constructor de la clase ActividadEntradaPuerta. Inicializa los atributos
	 * puerta y parque con los valores especificados en los parámetros.
	 * 
	 * @param puerta una cadena que representa la puerta por la que se realizarán
	 *               las entradas.
	 * @param parque una instancia de la interfaz IParque que representa el parque
	 *               en el que se realizarán las entradas.
	 */

	public ActividadEntradaPuerta(String puerta, IParque parque) {
		this.puerta = puerta;
		this.parque = parque;
	}

	/**
	 * Implementación del método run() de la interfaz Runnable. Realiza la actividad
	 * de entrada al parque a través de la puerta especificada. Se realizan 20
	 * entradas, con un tiempo de espera aleatorio entre 0 y 5 segundos entre cada
	 * entrada. Si la actividad es interrumpida, se registra un mensaje de log
	 * indicando la interrupción y la excepción lanzada.
	 */

	@Override
	public void run() {
		for (int i = 0; i < NUMENTRADAS; i++) {
			try {
				parque.entrarAlParque(puerta);
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5) * 500);
			} catch (InterruptedException e) {

				System.out.println("Entrada interrumpida" + puerta);
				System.out.println(e.toString());

				return;
			}
		}
		System.out.println("Finalizada entrada por la puerta " + puerta);
	}
}
