import java.util.HashSet;
import java.util.Set;


public class Abogado {

	private Set<Caso> casos = new HashSet<Caso>();

	public void darPermisoLecturaParaTodosLosCasosA(Abogado otroAbogado) {
		otroAbogado.darPermisoLecturaParaLosCasos(casos);
	}

	private void darPermisoLecturaParaLosCasos(Set<Caso> losCasos) {
		losCasos.iterator().forEachRemaining(caso -> caso.permisoLecturaPara(this));
	}

	public void agregarCaso(Caso caso) {
		casos.add(caso);
	}

	public boolean puedeLeerCaso(Caso caso) {
		return caso.puedeSerLeidoPor(this);
	}

	public void darPermisoLectura(Abogado abogadoRoberto,
			Caso casoFraudeGilberto) {
		// TODO Auto-generated method stub
		
	}

}
