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
}
