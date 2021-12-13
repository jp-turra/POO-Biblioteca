package Item;
public class Livro extends Item {

	private String autorLivro;
	private int totPagLivro;

	public Livro(String titulo, String autor, int numeroPaginas, int id) {
        super(titulo, id);
		this.autorLivro = autor;
		this.totPagLivro = numeroPaginas;
	}
}
