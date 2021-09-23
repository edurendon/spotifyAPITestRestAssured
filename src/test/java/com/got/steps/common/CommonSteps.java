package com.got.steps.common;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.got.model.Recurso;
import com.got.utils.ReadConfig;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import loa2a.controllers.components.TestComponent;
import loa2a.controllers.components.interfaces.ITestComponent;


public class CommonSteps {

	static Logger log = Logger.getLogger(CommonSteps.class);
	private Recurso recurso;
	private String MICROSERVICE;
	
	
	ReadConfig conf = new ReadConfig();
	String entorno = conf.getProperties("app.execution_environment");
	
	public CommonSteps() {
		log.debug("######################################################################");
		log.debug("######################################################################");
		log.debug("######################   CommonSteps  #####################@@@@@@@@@@@");
		log.debug("######################################################################");
		log.debug("######################################################################");
	}
	
	@Given("^Data for the test$")
	public void test_data() throws Throwable {
		log.info("Given: Data for the test");
		try {
			Recurso rtdo = null;
			ITestComponent testComponent = new TestComponent();	
			rtdo = testComponent.getRecurso();
			if (rtdo==null) {
				throw new Exception("ERROR: Given: Data for the test");
			}
			else {
				recurso=rtdo;
			}
		} catch (Exception oException) {
			log.error("Excepcion en Given " + oException.getMessage());
			Assert.assertTrue("Excepcion" + oException.getMessage(), false);
		}
	}
	
	@When("^Connect with the url$")
	public void connect_url() throws Throwable {
		log.info("WHEN: Connect with the url");
		try {
			boolean rtdo = false;
			ITestComponent testComponent = new TestComponent();	
			rtdo = testComponent.connect(entorno, recurso);
			if (!rtdo) {
				throw new Exception("ERROR: WHEN: Connect with the url");
			}
		} catch (Exception oException) {
			log.error("Excepcion en When " + oException.getMessage());
			Assert.assertTrue("Excepcion" + oException.getMessage(), false);
		}
	}

	@When("^Invoke microservice \"(.+?)\"$")
	public void invoke_microservice(String microservice) throws Throwable {
		log.info("THEN: Invoke microservice");
		try {
			boolean rtdo = false;
			ITestComponent testComponent = new TestComponent();	
			MICROSERVICE = microservice;
			rtdo = testComponent.resultCode(entorno, recurso, microservice);
			if (!rtdo) {
				throw new Exception("ERROR: THEN: Invoke microservice");
			}
		} catch (Exception oException) {
			log.error("Excepcion en When " + oException.getMessage());
			Assert.assertTrue("Excepcion" + oException.getMessage(), false);
		}
	}

	@Then("^Do evidences file$")
	public void do_evidences_file() throws Throwable {
		log.info("THEN: Do evidences file");
		try {
			boolean rtdo = false;
			ITestComponent testComponent = new TestComponent();	
			rtdo = testComponent.evidenceFile(entorno, recurso, MICROSERVICE);
			if (!rtdo) {
				throw new Exception("ERROR: THEN: Do evidences file");
			}
		} catch (Exception oException) {
			log.error("Excepcion en When " + oException.getMessage());
			Assert.assertTrue("Excepcion" + oException.getMessage(), false);
		}
	}
}
