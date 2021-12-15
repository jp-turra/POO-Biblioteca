package Item;

public enum Disponibilidade {
	DISPONIVEL(1), CONSULTALOCAL(2), EMPRESTADO(3), DANIFICADO(4), EXTRAVIADO(5);

	private int valor;

	Disponibilidade(int opt) {
		valor = opt;
	}

	public int getValor() {
		return valor;
	}

	public Disponibilidade getDisponibilidade(int id) {
		for (Disponibilidade d : Disponibilidade.values()) {
			if (d.getValor() == id)
				return d;
		}
		return null;
	}
}