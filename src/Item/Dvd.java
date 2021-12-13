package Item;
public class Dvd extends Item {

    public int duracao;
    public String autor;

    public Dvd(String titulo, String autor, int duracaoMinutos, int id) {
        super(titulo, id);
        this.duracao = duracaoMinutos;
        this.autor = autor;
    }

}
