package Item;
public class Documentario extends Item {

	public String tema;
	public int totalMinutos;

	public Documentario(String titulo, String tema, int tempoTotalMinutos, int id) {
        super(titulo, id);
		this.tema = tema;
		this.totalMinutos = tempoTotalMinutos;
	}

}
