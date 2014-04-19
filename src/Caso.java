import java.util.HashSet;
import java.util.Set;


public class Caso {
	private Set<Abogado> abogadosConLectura = new HashSet<Abogado>();
	public void permisoLecturaPara(Abogado abogado) {
		abogadosConLectura.add(abogado);
	}

	public boolean puedeSerLeidoPor(Abogado abogado) {
		return abogadosConLectura.contains(abogado);
	}

}
