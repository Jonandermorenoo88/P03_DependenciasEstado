package p03.c01;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Adrián Zamor Sánchez
 * @author Jon Ander Incera Moreno
 */
public class ActividadSalidaPuerta implements Runnable {

	// estos son los atributos
	private static final int NUMENTRADAS = 20;
	private String puerta;
	private IParque parque;

	public ActividadSalidaPuerta(String puerta, IParque parque) {
		this.puerta = puerta;
		this.parque = parque;
	}

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
