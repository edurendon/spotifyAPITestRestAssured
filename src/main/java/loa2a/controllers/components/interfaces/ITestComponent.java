package loa2a.controllers.components.interfaces;

import com.got.model.Recurso;

public interface ITestComponent {

	public boolean test(String entorno, Recurso recurso);

	public Recurso getRecurso();

	public boolean evidenceFile(String entorno, Recurso recurso, String prueba);

	public boolean resultCode(String entorno, Recurso recurso, String microservice);

	public boolean connect(String entorno, Recurso recurso);

	
}
