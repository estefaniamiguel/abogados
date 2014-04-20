import java.util.HashSet;
import java.util.Set;


public class Caso {
	private Set<Abogado> abogadosConLectura = new HashSet<Abogado>();
	public void darPermisoLecturaPara(Abogado abogado) {
		abogadosConLectura.add(abogado);
		abogado.agregarCasoConPermisoLectura(this);
	}

	public boolean puedeSerLeidoPor(Abogado abogado) {
		return abogadosConLectura.contains(abogado);
	}

	public void quitarPermisoLecturaPara(Abogado abogado) {
		abogadosConLectura.remove(abogado);
		abogado.quitarCasoConPermisoLectura(this);
	}

	public Set<Abogado> abogadosConPermisoLectura() {
		return abogadosConLectura;
	}

}
