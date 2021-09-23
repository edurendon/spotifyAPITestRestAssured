package loa2a.controllers.components;

import java.io.FileWriter;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

import loa2a.controllers.components.interfaces.ICreateEvidenceComponent;
public class CreateEvidenceComponent implements ICreateEvidenceComponent{
	
	public CreateEvidenceComponent() {
	}
	
	private static Logger log = Logger.getLogger(CreateEvidenceComponent.class);
	
	public boolean doEvidenceFile(String dato, String name, String ruta) {
		log.warn("Dentro de -----> doEvidenceFile()");
		boolean rtdo = false;
				
		try {
			FileWriter fichero = null;
	        PrintWriter pw = null;
	        try
	        {
	        	log.info("Creando fichero en: " + ruta + name +".txt");
	            fichero = new FileWriter(ruta + name +".txt");
	            pw = new PrintWriter(fichero);
	            	if(dato!=null)
	                	pw.println(dato);
	                else
	                	pw.println("Resultado de consulta: " + name + " vacio.");

	        } catch (Exception e) {
	        	log.warn("Exception="+e.getMessage());
	        } finally {
	           try {
	           if (null != fichero)
	              fichero.close();
	           } catch (Exception e) {
	        	   log.warn("Exception="+e.getMessage());
	           }
	        }
		}catch(Exception e)
		{
			log.warn("Exception="+e.getMessage());
		}
		return true;
		
	}
	
}
