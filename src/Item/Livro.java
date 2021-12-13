package Item;
public class Livro extends Item {

	private String autorLivro;
	private int totPagLivro;

	public Livro(String titulo, String autor, int numeroPaginas) {
		super(titulo);
		this.autorLivro = autor;
		this.totPagLivro = numeroPaginas;
	}
}
