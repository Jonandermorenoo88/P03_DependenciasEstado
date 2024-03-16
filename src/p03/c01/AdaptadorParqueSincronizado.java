package p03.c01;

import p03.c01.AdaptadorParqueSincronizado;

/**
 * Adaptador parque sincronizado
 * 
 * @author Adrián Zamora Sánchez
 * @author Jon Ander Incera Moreno
 *
 */
public class AdaptadorParqueSincronizado implements IParque {
	private IParque parque;

	private static AdaptadorParqueSincronizado instancia = new AdaptadorParqueSincronizado();

	public AdaptadorParqueSincronizado() {
		parque = new Parque();
	}

	
	public static AdaptadorParqueSincronizado getInstancia() {
		return instancia;
	}

	@Override
	public synchronized void entrarAlParque(String puerta) {
		parque.entrarAlParque(puerta);
	}

	@Override
	public synchronized void salirDelParque(String puerta) {
		parque.salirDelParque(puerta);
	}
}