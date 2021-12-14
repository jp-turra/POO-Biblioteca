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

	public void alterarDisponibilidade(int disponibilidade) {
		Disponibilidade d = this.dispItem.setDisponibilidade(disponibilidade);
		if (d != null) this.dispItem = d;
	}
	public String getTituloItem() {
		return tituloItem;
	}
	public int getIdItem() {
		return IdItem;
	}
}
