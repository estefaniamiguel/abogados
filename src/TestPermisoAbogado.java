import java.util.HashSet;
import java.util.Set;

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
	
	public void testQuitarPermisoParaUnCaso() {
		abogado.darPermisoLectura(abogadoRoberto, casoFraudeGilberto);
		abogado.quitarPermisoLectura(abogadoRoberto, casoFraudeGilberto);
		assertFalse(abogadoRoberto.puedeLeerCaso(casoSucesionManolo));
		assertFalse(abogadoRoberto.puedeLeerCaso(casoFraudeGilberto));
	}
	
	//Conocer todos los casos a los que puede acceder un abogado
	public void testConocerCasos() {
		abogado.darPermisoLecturaParaTodosLosCasosA(abogadoRoberto);
		Set<Caso> casos = abogadoRoberto.casosAccesibles();
		Set<Caso> casosEsperados = new HashSet<Caso>();
		casosEsperados.add(casoSucesionManolo);
		casosEsperados.add(casoFraudeGilberto);
		assertEquals(casosEsperados, casos);
	}
	
	//Conocer todos los abogados que pueden acceder a un caso
	public void testConocerAbogados() {
		abogado.darPermisoLectura(abogadoRoberto, casoFraudeGilberto);
		Set<Caso> abogados = casoFraudeGilberto.abogadosConPermisoLectura();
		Set<Caso> abogadosEsperados = new HashSet<Caso>();
		assertEquals(abogadosEsperados, abogados);
		assertEquals(null, casoSucesionManolo.abogadosConPermisoLectura());
	}

	
}
