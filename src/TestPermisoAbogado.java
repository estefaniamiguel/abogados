import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import junit.framework.TestCase;


public class TestPermisoAbogado extends TestCase {

	private Abogado abogadoDuenioDeLosCasos;
	private Abogado abogado;
	private Caso casoSucesionManolo;
	private Caso casoFraudeGilberto;

	protected void setUp() throws Exception {
		super.setUp();
		abogadoDuenioDeLosCasos = new Abogado();
		abogado = new Abogado();
		casoSucesionManolo = new Caso();
		casoFraudeGilberto = new Caso();
		abogadoDuenioDeLosCasos.agregarCaso(casoSucesionManolo);
		abogadoDuenioDeLosCasos.agregarCaso(casoFraudeGilberto);
	}
	
	//Permitir a un abogado darle permisos a otro miembro de su firma para todos sus casos
	public void testAgregarPermisoLecturaAOtroMiembroParaTodosSusCasos() {
		abogadoDuenioDeLosCasos.darPermisoLecturaParaTodosLosCasosA(abogado);
		assertTrue(abogado.puedeLeerCaso(casoSucesionManolo));
		assertTrue(abogado.puedeLeerCaso(casoFraudeGilberto));
	}
	
	//Permitir a un abogado darle o denegarle permisos a otro miembro de su firma para un caso particular
	public void testAgregarPermisoParaUnCaso() {
		abogadoDuenioDeLosCasos.darPermisoLectura(abogado, casoFraudeGilberto);
		assertFalse(abogado.puedeLeerCaso(casoSucesionManolo));
		assertTrue(abogado.puedeLeerCaso(casoFraudeGilberto));
	}
	
	public void testQuitarPermisoParaUnCaso() {
		abogadoDuenioDeLosCasos.darPermisoLectura(abogado, casoFraudeGilberto);
		assertTrue(abogado.puedeLeerCaso(casoFraudeGilberto));
		abogadoDuenioDeLosCasos.quitarPermisoLectura(abogado, casoFraudeGilberto);
		assertFalse(abogado.puedeLeerCaso(casoSucesionManolo));
		assertFalse(abogado.puedeLeerCaso(casoFraudeGilberto));
	}
	
	//Conocer todos los casos a los que puede acceder un abogado
	public void testConocerCasos() {
		abogadoDuenioDeLosCasos.darPermisoLecturaParaTodosLosCasosA(abogado);
		Set<Caso> casos = abogado.casosAccesibles();
		Set<Caso> casosEsperados = new HashSet<Caso>();
		casosEsperados.add(casoSucesionManolo);
		casosEsperados.add(casoFraudeGilberto);
		assertEquals(casosEsperados, casos);
	}
	
	//Conocer todos los abogados que pueden acceder a un caso
	public void testConocerAbogados() {
		abogadoDuenioDeLosCasos.darPermisoLectura(abogado, casoFraudeGilberto);
		Set<Abogado> abogados = casoFraudeGilberto.abogadosConPermisoLectura();
		Set<Abogado> abogadosEsperados = new HashSet<Abogado>();
		abogadosEsperados.add(abogado);
		assertEquals(abogadosEsperados, abogados);
		assertTrue(casoSucesionManolo.abogadosConPermisoLectura().isEmpty());
	}

	//Autorizar o rechazar el acceso de lectura a un abogado sobre un caso
	public void testAutorizarAccesoLecturaConPermisoLectura() throws AccessDeniedException {
		abogadoDuenioDeLosCasos.darPermisoLectura(abogado, casoFraudeGilberto);
		abogado.leer(casoFraudeGilberto);
	}
	
	public void testAutorizarAccesoLecturaConPermisoTotal() throws AccessDeniedException {
	}
	
	public void testAutorizarAccesoLecturaSiendoDuenio() throws AccessDeniedException {

	}
	
	@Test(expected = AccessDeniedException.class)
	public void testRechazarAccesoLecturaSinPermiso() throws AccessDeniedException {
		abogado.leer(casoFraudeGilberto);
	}
	
	//Autorizar o rechazar el acceso total a un abogado sobre un caso
	public void testAutorizarAccesoEscrituraConPermisoTotal() throws AccessDeniedException {
		
	}
	
	public void testAutorizarAccesoEscrituraSiendoDuenio() throws AccessDeniedException {
		
	}
	
	@Test(expected = AccessDeniedException.class)
	public void testRechazarAccesoEscrituraConPermisoLectura() throws AccessDeniedException {
	}
	
	@Test(expected = AccessDeniedException.class)
	public void testRechazarAccesoEscrituraSinPermiso() throws AccessDeniedException {
	}
}
