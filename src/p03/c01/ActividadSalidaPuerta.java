package p03.c01;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Clase Actividad salida Puerta. Esta clase representa una actividad de salida
 * a traves de una puerta en un parque. imprementa la interfaz runnable para
 * poder ser ejecutada en un hilo de ejecución.
 * 
 * 
 * @author Adrián Zamor Sánchez
 * @author Jon Ander Incera Moreno
 */
public class ActividadSalidaPuerta implements Runnable {

	// estos son los atributos
	private static final int NUMENTRADAS = 20;
	private String puerta;
	private IParque parque;

	/**
	 * contructor de la clase ActividadSalidaPuerta. Inicializa los atributos puerta
	 * y parque con los valores especificos en los parametros.
	 * 
	 * @param puerta una cadena que representa la puerta por la que se realizarán
	 *               las entradas.
	 * @param parque una instancia de la interfaz IParque que representa el parque
	 *               en el que se realizarán las entradas.
	 */
	public ActividadSalidaPuerta(String puerta, IParque parque) {
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
				parque.salirDelParque(puerta);
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5) * 500);
			} catch (InterruptedException e) {
				System.out.println("Salida interrumpida " + puerta);
				System.out.println(e.toString());
				return;
			}
		}
	}
}
