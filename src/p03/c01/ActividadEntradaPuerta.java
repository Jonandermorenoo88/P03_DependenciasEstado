package p03.c01;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Alberto Santos Martínez
 * @author Jon Ander Incera Moreno
 */

public class ActividadEntradaPuerta implements Runnable {

	// esto son los atributos
	private static final int NUMENTRADAS = 20;
	private String puerta;
	private IParque parque;


	public ActividadEntradaPuerta(String puerta, IParque parque) {
		this.puerta = puerta;
		this.parque = parque;
	}

	@Override
	public void run() {
		for (int i = 0; i < NUMENTRADAS; i++) {
			try {
				parque.entrarAlParque(puerta);
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5) * 500);
			} catch (InterruptedException e) {
				
				System.out.println( "Entrada interrumpida" + puerta);
				System.out.println(e.toString());
				
				return;
			}
		}
		
		System.out.println("Finalizada entrada por la puerta " + puerta);
	}
}
