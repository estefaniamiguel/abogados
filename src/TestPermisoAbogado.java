import java.util.HashSet;
import java.util.Set;

import org.junit.*;
import static org.junit.Assert.*;

public class TestPermisoAbogado {

	private Abogado abogadoDuenioDeLosCasos;
	private Abogado abogado;
	private Caso casoSucesionManolo;
	private Caso casoFraudeGilberto;

	@Before
	public void setearCasos(){
		abogadoDuenioDeLosCasos = new Abogado();
		abogado = new Abogado();
		casoSucesionManolo = new Caso();
		casoFraudeGilberto = new Caso();
		abogadoDuenioDeLosCasos.agregarCaso(casoSucesionManolo);
		abogadoDuenioDeLosCasos.agregarCaso(casoFraudeGilberto);
	}
	
	//Permitir a un abogado darle permisos a otro miembro de su firma para todos sus casos
	@Test
	public void testAgregarPermisoLecturaAOtroMiembroParaTodosSusCasos() {
		abogadoDuenioDeLosCasos.darPermisoLecturaParaTodosLosCasosA(abogado);
		assertTrue(abogado.puedeLeerCaso(casoSucesionManolo));
		assertTrue(abogado.puedeLeerCaso(casoFraudeGilberto));
	}
	
	//Permitir a un abogado darle o denegarle permisos a otro miembro de su firma para un caso particular
	@Test
	public void testAgregarPermisoParaUnCaso() {
		abogadoDuenioDeLosCasos.darPermisoLectura(abogado, casoFraudeGilberto);
		assertFalse(abogado.puedeLeerCaso(casoSucesionManolo));
		assertTrue(abogado.puedeLeerCaso(casoFraudeGilberto));
	}
	
	@Test
	public void testQuitarPermisoParaUnCaso() {
		abogadoDuenioDeLosCasos.darPermisoLectura(abogado, casoFraudeGilberto);
		assertTrue(abogado.puedeLeerCaso(casoFraudeGilberto));
		abogadoDuenioDeLosCasos.quitarPermisoLectura(abogado, casoFraudeGilberto);
		assertFalse(abogado.puedeLeerCaso(casoSucesionManolo));
		assertFalse(abogado.puedeLeerCaso(casoFraudeGilberto));
	}
	
	//Conocer todos los casos a los que puede acceder un abogado
	@Test
	public void testConocerCasos() {
		abogadoDuenioDeLosCasos.darPermisoLecturaParaTodosLosCasosA(abogado);
		Set<Caso> casos = abogado.casosAccesibles();
		Set<Caso> casosEsperados = new HashSet<Caso>();
		casosEsperados.add(casoSucesionManolo);
		casosEsperados.add(casoFraudeGilberto);
		assertEquals(casosEsperados, casos);
	}
	
	//Conocer todos los abogados que pueden acceder a un caso
	@Test
	public void testConocerAbogados() {
		abogadoDuenioDeLosCasos.darPermisoLectura(abogado, casoFraudeGilberto);
		Set<Abogado> abogados = casoFraudeGilberto.abogadosConPermisoLectura();
		Set<Abogado> abogadosEsperados = new HashSet<Abogado>();
		abogadosEsperados.add(abogado);
		abogadosEsperados.add(abogadoDuenioDeLosCasos);
		assertEquals(abogadosEsperados, abogados);
		Set<Abogado> abogadosEsperadosManolo = new HashSet<Abogado>();
		abogadosEsperadosManolo.add(abogadoDuenioDeLosCasos);
		assertEquals(abogadosEsperadosManolo, casoSucesionManolo.abogadosConPermisoLectura());
	}

	//Autorizar o rechazar el acceso de lectura a un abogado sobre un caso
	@Test
	public void testAutorizarAccesoLecturaConPermisoLectura() throws AccessDeniedException {
		abogadoDuenioDeLosCasos.darPermisoLectura(abogado, casoFraudeGilberto);
		abogado.leer(casoFraudeGilberto);
	}
	
	@Test
	public void testAutorizarAccesoLecturaConPermisoTotal() throws AccessDeniedException {
		abogadoDuenioDeLosCasos.darPermisoTotal(abogado, casoFraudeGilberto);
		abogado.leer(casoFraudeGilberto);
	}
	
	@Test
	public void testAutorizarAccesoLecturaSiendoDuenio() throws AccessDeniedException {
		abogadoDuenioDeLosCasos.leer(casoFraudeGilberto);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void testRechazarAccesoLecturaSinPermiso() throws AccessDeniedException {
		abogado.leer(casoFraudeGilberto);
	}
	
	//Autorizar o rechazar el acceso total a un abogado sobre un caso
	@Test
	public void testAutorizarAccesoEscrituraConPermisoTotal() throws AccessDeniedException {
		abogadoDuenioDeLosCasos.darPermisoTotal(abogado, casoFraudeGilberto);
		abogado.editar(casoFraudeGilberto);
	}
	
	@Test
	public void testAutorizarAccesoEscrituraSiendoDuenio() throws AccessDeniedException {
		abogadoDuenioDeLosCasos.editar(casoFraudeGilberto);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void testRechazarAccesoEscrituraConPermisoLectura() throws AccessDeniedException {
		abogadoDuenioDeLosCasos.darPermisoLectura(abogado, casoFraudeGilberto);
		abogado.editar(casoFraudeGilberto);
	}
	
	@Test(expected = AccessDeniedException.class)
	public void testRechazarAccesoEscrituraSinPermiso() throws AccessDeniedException {
		abogado.editar(casoFraudeGilberto);
	}
}
