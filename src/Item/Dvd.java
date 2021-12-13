package Item;
public class Dvd extends Item {

    public int duracao;
    public String autor;

    public Dvd(String titulo, String autor, int duracaoMinutos) {
        super(titulo);
        this.duracao = duracaoMinutos;
        this.autor = autor;
    }

}
