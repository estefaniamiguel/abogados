import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Abogado {
	private Map<Caso, Permiso> accesos = new HashMap<Caso, Permiso>();

	public void darPermisoLecturaParaTodosLosCasosA(Abogado otroAbogado) {
		otroAbogado.darPermisoLecturaParaLosCasos(casosPropios());
	}
	
	public void darPermisoTotalParaTodosLosCasosA(Abogado otroAbogado) {
		otroAbogado.darPermisoTotalParaLosCasos(casosPropios());
	}

	private Set<Caso> casosPropios() {
		Set<Caso> casos = new HashSet<Caso>();
		for(Caso caso : accesos.keySet()) {
			if (accesos.get(caso).esDuenio()) {
				casos.add(caso);
			}
		}
		return casos;
	}

	private void darPermisoLecturaParaLosCasos(Set<Caso> losCasos) {
		losCasos.iterator().forEachRemaining(caso -> caso.darPermisoLecturaPara(this));
	}
	
	private void darPermisoTotalParaLosCasos(Set<Caso> losCasos) {
		losCasos.iterator().forEachRemaining(caso -> caso.darPermisoTotalPara(this));
	}

	public void agregarCaso(Caso caso) {
		accesos.put(caso, new PermisoPropietario());
		caso.darPermisoPropietario(this);
	}
	
	private boolean puedeDarPermiso(Caso caso) {
		if (accesos.get(caso) != null)
			return accesos.get(caso).puedeDarPermiso();
		return false;
	}

	public boolean puedeLeerCaso(Caso caso) {
		return caso.puedeSerLeidoPor(this);
	}

	public void darPermisoLectura(Abogado abogado,
			Caso caso) {
		if (this.puedeDarPermiso(caso))
			caso.darPermisoLecturaPara(abogado);
		else
			throw new AccesoDenegadoException("Debe ser propietario del caso para dar permisos");
	}

	public void quitarPermisoLectura(Abogado abogado,
			Caso caso) {
		if (this.puedeDarPermiso(caso))
			caso.quitarPermisoLecturaPara(abogado);
		else
			throw new AccesoDenegadoException("Debe ser propietario del caso para dar permisos");
	}

	public Set<Caso> casosAccesibles() {
		Set<Caso> casos = new HashSet<Caso>();
		for(Caso caso : accesos.keySet()) {
			if(this.puedeLeerCaso(caso))
			casos.add(caso);
		};
		return casos;
	}

	public void agregarCasoConPermisoLectura(Caso caso) {
		accesos.put(caso, new PermisoLectura());
	}

	public void quitarCasoConPermisoLectura(Caso caso) {
		accesos.remove(caso);
	}

	public void leer(Caso caso) {
		caso.leer(this);
	}

	public void darPermisoTotal(Abogado abogado, Caso caso) {
		if (this.puedeDarPermiso(caso))
			caso.darPermisoTotalPara(abogado);
		else
			throw new AccesoDenegadoException("Debe ser propietario del caso para dar permisos");
	}

	public void agregarCasoConPermisoTotal(Caso caso) {
		accesos.put(caso, new PermisoTotal());
	}

	public void editar(Caso caso) {
		caso.editar(this);
	}

}
