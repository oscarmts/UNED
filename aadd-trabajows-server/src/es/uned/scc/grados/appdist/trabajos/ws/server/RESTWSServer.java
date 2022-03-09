package es.uned.scc.grados.appdist.trabajos.ws.server;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

import es.uned.scc.grados.appdist.trabajos.ws.RESTSignalGeneratorWSImpl;

public class RESTWSServer {

	public static void main(String[] args) {
		// Create the JAX-RS Server with CXF
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		// Set REST implementor class
		sf.setResourceClasses(RESTSignalGeneratorWSImpl.class);
		sf.setResourceProvider(RESTSignalGeneratorWSImpl.class,
				new SingletonResourceProvider(new RESTSignalGeneratorWSImpl()));
		// Create absolute Path
		sf.setAddress("http://localhost:9002/");
		// Start JAX-RS Server
		sf.create();

	}

}
