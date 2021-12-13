package Item;
public class Documentario extends Item {

	public String tema;
	public int totalMinutos;

	public Documentario(String titulo, String tema, int tempoTotalMinutos) {
		super(titulo);
		this.tema = tema;
		this.totalMinutos = tempoTotalMinutos;
	}

}
