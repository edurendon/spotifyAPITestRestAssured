package loa2a.controllers.components;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

import org.apache.log4j.Logger;

import com.got.model.Recurso;
import com.got.utils.ReadConfig;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import loa2a.controllers.components.interfaces.ICreateEvidenceComponent;
import loa2a.controllers.components.interfaces.ITestComponent;

public class TestComponent implements ITestComponent {
	
	static Logger log = Logger.getLogger(TestComponent.class);
	
	//ReadConfig conf = new ReadConfig();
	String ruta = new ReadConfig().getProperties("app.ruta_evidencias");
	
	protected ICreateEvidenceComponent createEvidence = new CreateEvidenceComponent();
	
	private String userIdPetition = "{\"name\": \"New Playlist\",\"description\": \"New playlist description\",\"public\": false}";
	
	public Recurso getRecurso () {
		Recurso recurso = new Recurso();
		recurso.setUserId(new ReadConfig().getProperties("app.userId"));
		recurso.setToken(new ReadConfig().getProperties("app.token"));
		return recurso;
	} 
	
	public boolean test(String entorno, Recurso recurso) {
		boolean result = false;
		String mensaje="test";
		try {
			log.info("Hemos llegado correctamente hasta Test");
			log.info("El entorno es: "+ entorno); 
			
//			String body = RestAssured
//	                  .given()
//	                      .baseUri("https://aotlxppwsb00001.cosmos.es.ftgroup:9061/ms-mdg/v1/getClientMDGRest")
//	                      .and()
//	                      .queryParam("format", "json")
//	                  .when()
//	                      .get("/NumeroDocumento/"+recurso.getDNI()+"/TipoAcceso/NIF_CIF/Marca/Orange/OnlyActive/false")
//	                  .then()
//	                      .log().all()
//	                      .and().assertThat().statusCode(200)
////	                      .and()
////	                      .body("films", response -> notNullValue())
////	                      .body("vehicles", response -> notNullValue())
////	                      .body("people", response -> notNullValue())
////	                      .body("starships", response -> notNullValue())
////	                      .body("species", response -> notNullValue())
////	                      .body("planets", equalTo("f"))
//	                      .and().extract().body().asString();
			
			result = true;
		}catch(Exception e) {
			log.error("No se ha podido lanzar: " + mensaje);
		}
		return result;
	}
	
	@Override
	public boolean evidenceFile(String entorno, Recurso recurso, String prueba) {
		boolean rtdo = false;
		try {
			createEvidence.doEvidenceFile(recurso.getResult(), "Evicencias-"+prueba, ruta);
			rtdo = true;
		} catch (Exception e) {
			rtdo = false;
			log.warn(e.getMessage());
		}
	
	return rtdo;
	}

	@Override
	public boolean resultCode(String entorno, Recurso recurso, String microservice) {
		boolean result = false;
		String mensaje="resultCode";
		try {
			log.info("Hemos llegado correctamente hasta resultCode");
			switch(microservice)
			{
				case "validToken": recurso=this.validTokenPetition(recurso);
					break;
				case "withoutValidToken": recurso=this.withoutValidTokenPetition(recurso);
					break;
				case "invalidToken": recurso=this.invalidTokenPetition(recurso);
					break;
				case "createPlaylist": recurso=this.createPlaylistPetition(recurso);
					break;
				case "playlistInvalidUserId": recurso=this.playlistInvalidUserIdPetition(recurso);
					break;
				default:
					result=false;
				
			}
			log.info("Response: "+recurso.getResult());
			result = true;
		}catch(Exception e) {
			log.error("No se ha podido lanzar: " + mensaje);
		}
		return result;
	}
	
	public boolean connect(String entorno, Recurso recurso) {
		boolean result = false;
		String mensaje="connect";
		try {
			log.info("Hemos llegado correctamente hasta connect");
			
			RequestSpecification requestSpecification = RestAssured
	                  .given()
	                      .baseUri("https://api.spotify.com/v1/")
	                      .and()
	                      .queryParam("format", "json");
			
			recurso.setRequestSpecification(requestSpecification);
			
			result = true;
		}catch(Exception e) {
			log.error("No se ha podido lanzar: " + mensaje);
		}
		return result;
	}
	
	private Recurso validTokenPetition(Recurso recurso) {
		String body =recurso.getRequestSpecification()
				.when()
				.header("authorization", "Bearer "+recurso.getToken())
	            .get("artists/1EXjXQpDx2pROygh8zvHs4")
		        .then()
		            .log().all()
		            .and().assertThat().statusCode(200)
		            .and().extract().body().asString();
		recurso.setResult(body);
		return recurso;
	}
	
	private Recurso withoutValidTokenPetition(Recurso recurso) {
		String body =recurso.getRequestSpecification().when()
	            .get("artists/1EXjXQpDx2pROygh8zvHs4")
		        .then()
		            .log().all()
		            .and().assertThat().statusCode(401)
		            .and().body(containsString("No token provided"))
		            .and().extract().body().asString();
		recurso.setResult(body);
		return recurso;
	}
	
	private Recurso invalidTokenPetition(Recurso recurso) {
		String body =recurso.getRequestSpecification().when()
				.header("authorization", "Bearer falseAuthorization")
	            .get("artists/1EXjXQpDx2pROygh8zvHs4")
		        .then()
		            .log().all()
		            .and().assertThat().statusCode(401)
		            .and().body(containsString("Invalid access token"))
		            .and().extract().body().asString();
		recurso.setResult(body);
		return recurso;
	}
	
	private Recurso createPlaylistPetition(Recurso recurso) {
		String body =recurso.getRequestSpecification()
				.when()
				.header("authorization", "Bearer "+recurso.getToken())
				.body(userIdPetition)
	            .post("users/"+recurso.getUserId()+"/playlists")
		        .then()
		            .log().all()
		            .and().assertThat().statusCode(201)
		            .and().extract().body().asString();
		recurso.setResult(body);
		return recurso;
	}
	
	private Recurso playlistInvalidUserIdPetition(Recurso recurso) {
		String body =recurso.getRequestSpecification()
				.when()
				.header("authorization", "Bearer "+recurso.getToken())
				.body(userIdPetition)
	            .post("users/31j75evg4bl7i3e6umegji4vwa75/playlists")
		        .then()
		            .log().all()
		            .and().assertThat().statusCode(403)
		            .and().extract().body().asString();
		recurso.setResult(body);
		return recurso;
	}
}
