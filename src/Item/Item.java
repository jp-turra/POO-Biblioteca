package Item;

public abstract class Item {

	private int IdItem;
	private String tituloItem;
	private Disponibilidade dispItem;

	public Item(String titulo, int id) {
		this.tituloItem = titulo;
		this.IdItem = id;
		this.dispItem = Disponibilidade.DISPONIVEL;
	}

	public void alterarDisponibilidade(Disponibilidade disponibilidade) {
		this.dispItem = disponibilidade;
	}

	public String getTituloItem() {
		return tituloItem;
	}

	public int getIdItem() {
		return IdItem;
	}

	public Disponibilidade getDispItem() {
		return dispItem;
	}
}
