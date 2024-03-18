package p03.c01;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Clase Parque. Esta clase representa al objeto del parque
 * el cual cuenta las personas que hay en el, estas personas entran
 * y salen por las puertas mediante las actividades de salir y entrar.
 * 
 * 
 * @author Adrián Zamora Sánchez
 * @author Jon Ander Incera Moreno
 */
public class Parque implements IParque {

	// Atributos de la calse 
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
	 * Comprobación de invariantes, comprueba para las tres condiciones 
	 * que se cumplan para detectar errores de sincronización y funcionamiento
	 */
	protected void checkInvariante() {
		assert sumarContadoresPuerta() == contadorPersonasTotales: "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parque";
		assert contadorPersonasTotales <= aforo : "INV: El número de personas supera el aforo máximo permitido";
		assert contadorPersonasTotales >= 0 : "INV: No pueden salir personas si está vacío el parque";
	}

	/**
	 * Imprime información sobre un cambio por una puerta
	 * 
	 * @param puerta id de la puerta sobre la que se imprime la información
	 * @param movimiento string que indica si es una salida o entrada
	 */
	private void imprimirInfo(String puerta, String movimiento) {
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales);
		for (String p : contadoresPersonasPuerta.keySet()) {
			System.out.println("----> Por puerta " + p + " " + contadoresPersonasPuerta.get(p));
		}
		System.out.println(" ");
	}

	/**
	 * Suma de los contadores parciales de cada una de las puertas
	 * 
	 * @return int
	 */
	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
		Enumeration<Integer> iterPuertas = contadoresPersonasPuerta.elements();
		while (iterPuertas.hasMoreElements()) {
			sumaContadoresPuerta += iterPuertas.nextElement();
		}
		return sumaContadoresPuerta;
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

	/**
	 * Método sincronizado para comprobar si el parque está lleno antes de permitir
	 * que una persona entre. El método espera mientras el parque está lleno y
	 * despierta a los hilos en espera cuando hay espacio disponible
	 */
	protected synchronized void comprobarAntesDeEntrar() {
		// Se mantiene a la espera comprobando si el parque está lleno.
		if (contadorPersonasTotales == aforo) {
			try {
				wait(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Método que permite a una persona salir del parque a través de una puerta
	 * especificada.
	 */
	@Override
	public void salirDelParque(String puerta) {
		// TODO Auto-generated method stub
		// En caso de que no haya salidas por esa puerta, la inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null) {
			contadoresPersonasPuerta.put(puerta, 0);
		}

		// Llamamos a la postcondición.
		comprobarAntesDeSalir(puerta);

		// Decrementamos el contador total de la gente que está en el parque y el de la
		// puerta
		contadorPersonasTotales--;
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta) - 1);

		// Imprimimos el estado actual del parque
		imprimirInfo(puerta, "Salida");

		// Se comprueba el invariante
		checkInvariante();

		// Avisamos al resto de hilos que están a la espera de que hemos liberado el
		// recurso
		notifyAll();

	}

	/**
	 * Método que mantiene al hilo a la espera de que se cumpla la postcondición
	 * antes de salir del parque. Se mantiene a la espera comprobando si el parque
	 * está vacío
	 */
	protected synchronized void comprobarAntesDeSalir(String puerta) {
		if (contadorPersonasTotales == 0) {
			try {
				wait(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
