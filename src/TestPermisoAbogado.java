import junit.framework.TestCase;


public class TestPermisoAbogado extends TestCase {

	private Abogado abogado;
	private Abogado abogadoRoberto;
	private Caso casoSucesionManolo;
	private Caso casoFraudeGilberto;

	protected void setUp() throws Exception {
		super.setUp();
		abogado = new Abogado();
		abogadoRoberto = new Abogado();
		casoSucesionManolo = new Caso();
		casoFraudeGilberto = new Caso();
		abogado.agregarCaso(casoSucesionManolo);
		abogado.agregarCaso(casoFraudeGilberto);
	}
	
	//Permitir a un abogado darle permisos a otro miembro de su firma para todos sus casos
	public void testAgregarPermisoLecturaAOtroMiembroParaTodosSusCasos() {
		abogado.darPermisoLecturaParaTodosLosCasosA(abogadoRoberto);
		assertTrue(abogadoRoberto.puedeLeerCaso(casoSucesionManolo));
		assertTrue(abogadoRoberto.puedeLeerCaso(casoFraudeGilberto));
	}
	
	//Permitir a un abogado darle o denegarle permisos a otro miembro de su firma para un caso particular
	public void testAgregarPermisoParaUnCaso() {
		abogado.darPermisoLectura(abogadoRoberto, casoFraudeGilberto);
		assertFalse(abogadoRoberto.puedeLeerCaso(casoSucesionManolo));
		assertTrue(abogadoRoberto.puedeLeerCaso(casoFraudeGilberto));
	}
	
	
}
