
public class PermisoPropietario extends Permiso {
	public boolean puedeLeer() {
		return true;		
	}

	public boolean puedeEditar() {
		return true;
	}

	public boolean esDuenio() {
		return true;
	}
	
	public boolean puedeDarPermiso() {
		return true;
	}

}
