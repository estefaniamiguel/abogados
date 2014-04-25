import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Caso {
	private Map<Abogado, Permiso> accesos = new HashMap<Abogado, Permiso>();
	public void darPermisoLecturaPara(Abogado abogado) {
		accesos.put(abogado, new PermisoLectura());
		abogado.agregarCasoConPermisoLectura(this);
	}

	public boolean puedeSerLeidoPor(Abogado abogado) {
		if (accesos.get(abogado) != null)
			return accesos.get(abogado).puedeLeer();
		return false;
	}

	public void quitarPermisoLecturaPara(Abogado abogado) {
		accesos.remove(abogado);
		abogado.quitarCasoConPermisoLectura(this);
	}

	public Set<Abogado> abogadosConPermisoLectura() {
		Set<Abogado> abogados = new HashSet<Abogado>();
		for(Abogado abogado : accesos.keySet()) {
			if(this.puedeSerLeidoPor(abogado))
			abogados.add(abogado);
		}
		return abogados;
	}

	public void leer(Abogado abogado) {
		if (!this.puedeSerLeidoPor(abogado))
			throw new AccesoDenegadoException("No tiene permisos para leer el caso");
	}

	public void darPermisoTotalPara(Abogado abogado) {
		accesos.put(abogado, new PermisoTotal());
		abogado.agregarCasoConPermisoTotal(this);
	}

	public void editar(Abogado abogado) {
		if (!this.puedeSerEditadoPor(abogado))
			throw new AccesoDenegadoException("No tiene permisos para leer el caso");
	}

	private boolean puedeSerEditadoPor(Abogado abogado) {
		if (accesos.get(abogado) != null)
			return accesos.get(abogado).puedeEditar();
		return false;
	}

	public void darPermisoPropietario(Abogado abogado) {
		accesos.put(abogado, new PermisoPropietario());
	}

}
