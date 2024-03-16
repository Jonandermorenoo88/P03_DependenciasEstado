package p03.c01;

import java.util.Hashtable;

public class Parque implements IParque {

	/** Atributos */
	private int contadorPersonasTotales;
	private Hashtable<String, Integer> contadoresPersonasPuerta;
	private int aforo;

	/**
	 * Constructor de la clase Parque que inicializa los contadores de personas y el
	 * aforo máximo.
	 * 
	 * @param aforo el número máximo de personas permitidas en el parque
	 */
	public Parque() {
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
		aforo = 50;
	}

	/**
	 * Método synchronized que permite a una persona entrar al parque por una puerta
	 * determinada.
	 * 
	 * @param puerta la puerta por la cual la persona entra al parque
	 */
	@Override
	public void entrarAlParque(String puerta) {
		// TODO Auto-generated method stub
		// En caso de que no haya entradas por esa puerta, la inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null) {
			contadoresPersonasPuerta.put(puerta, 0);
		}

		// Llamamos a la precondición.
		comprobarAntesDeEntrar();

		// Aumentamos el contador total de la gente que está en el parque y el de la
		// puerta
		contadorPersonasTotales++;
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta) + 1);

		// Imprimimos el estado actual del parque
		imprimirInfo(puerta, "Entrada");

		// Se comprueba el invariante
		checkInvariante();

		// Avisamos al resto de hilos que están a la espera de que hemos liberado el
		// recurso
		notifyAll();

	}

	@Override
	public void salirDelParque(String puerta) {
		// TODO Auto-generated method stub

	}

	/**
	 * Método sincronizado para comprobar si el parque está lleno antes de permitir
	 * que una persona entre. El método espera mientras el parque está lleno y
	 * despierta a los hilos en espera cuando hay espacio disponible
	 * 
	 */
	protected synchronized void comprobarAntesDeEntrar() {
		// Se mantiene a la espera comprobando si el parque está lleno.
		if (contadorPersonasTotales == aforo) {
			try {
				wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
