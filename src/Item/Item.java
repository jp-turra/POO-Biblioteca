package Item;
public abstract class Item {

	private int IdItem;
	private String tituloItem;
	private Disponibilidade dispItem;
	
	public Item(String titulo) {
		this.tituloItem = titulo;
		this.dispItem = Disponibilidade.DISPONIVEL;
		// TODO: Função para retornar o valor incremental do id
	}
	
}
