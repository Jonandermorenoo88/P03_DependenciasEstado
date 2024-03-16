package p03.c01;

/**
 * Interfaz del parque, implementa de forma abstracta los métodos  
 * de entrada y salida del parque
 * 
 * @author Adrián Zamora Sánchez
 * @author Jon Ander Incera Moreno
 */
public interface IParque {
	/**
	 * Método de entrada al parque
	 * 
	 * @param puerta id de la puerta
	 */
	public abstract void entrarAlParque(String puerta);
	
	/**
	 * Método de salida del parque
	 * 
	 * @param puerta id de la puerta
	 */
	public abstract void salirDelParque(String puerta);

}
