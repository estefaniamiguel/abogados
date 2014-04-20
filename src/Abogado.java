import java.util.HashSet;
import java.util.Set;


public class Abogado {

	private Set<Caso> casos = new HashSet<Caso>();
	private Set<Caso> casosLectura = new HashSet<Caso>();
	public void darPermisoLecturaParaTodosLosCasosA(Abogado otroAbogado) {
		otroAbogado.darPermisoLecturaParaLosCasos(casos);
	}

	private void darPermisoLecturaParaLosCasos(Set<Caso> losCasos) {
		losCasos.iterator().forEachRemaining(caso -> caso.darPermisoLecturaPara(this));
	}

	public void agregarCaso(Caso caso) {
		casos.add(caso);
	}

	public boolean puedeLeerCaso(Caso caso) {
		return caso.puedeSerLeidoPor(this);
	}

	public void darPermisoLectura(Abogado abogado,
			Caso caso) {
		caso.darPermisoLecturaPara(abogado);
	}

	public void quitarPermisoLectura(Abogado abogado,
			Caso caso) {
		caso.quitarPermisoLecturaPara(abogado);
	}

	public Set<Caso> casosAccesibles() {
		return casosLectura;
	}

	public void agregarCasoConPermisoLectura(Caso caso) {
		casosLectura.add(caso);
	}

	public void quitarCasoConPermisoLectura(Caso caso) {
		casosLectura.remove(caso);
	}

}
