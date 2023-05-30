package cola;

public class Cola<T> {

	private NodoCola<T> inicio;
	private NodoCola fin;
	private int largo;

	public Cola() {
		this.inicio = null;
		this.fin = null;
		this.largo = 0;
	}

	public void encolar(T dato) {
		if (this.inicio == null) {
//			inicio = fin = new NodoCola<T>(dato); // Alternativa 
			inicio = new NodoCola<T>(dato);
			fin = inicio;
		} else {
			fin.sig = new NodoCola<T>(dato);
			fin = fin.sig;
		}
		this.largo++;
	}

	// Pre: !esVacia()
	public T desencolar() {
		T dato = this.inicio.dato;
		inicio = inicio.sig;
		this.largo--;
		if(this.inicio == null) {
			fin = null;
		}
		return dato;
	}

	public boolean esVacia() {
		return this.largo == 0;
	}

	class NodoCola<T> {
		private T dato;
		private NodoCola<T> sig;

		public NodoCola(T dato, NodoCola<T> sig) {
			this.dato = dato;
			this.sig = sig;
		}

		public NodoCola(T dato) {
			this.dato = dato;
		}
	}

}
