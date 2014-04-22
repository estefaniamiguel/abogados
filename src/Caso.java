import java.util.HashSet;
import java.util.Set;


public class Caso {
	private Set<Abogado> abogadosConLectura = new HashSet<Abogado>();
	private Set<Abogado> abogadosConEdicion = new HashSet<Abogado>();
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

	public void leer(Abogado abogado) throws AccessDeniedException {
		if (!this.puedeSerLeidoPor(abogado))
			throw new AccessDeniedException("No tiene permisos para leer el caso");
	}

	public void darPermisoTotalPara(Abogado abogado, Caso caso) {
		abogadosConLectura.add(abogado);
		abogado.agregarCasoConPermisoLectura(this);
		abogadosConEdicion.add(abogado);
		abogado.agregarCasoConPermisoEdicion(this);
	}

	public void editar(Abogado abogado) throws AccessDeniedException {
		if (!this.puedeSerEditadoPor(abogado))
			throw new AccessDeniedException("No tiene permisos para leer el caso");
	}

	private boolean puedeSerEditadoPor(Abogado abogado) {
		return abogadosConEdicion.contains(abogado);
	}

}
