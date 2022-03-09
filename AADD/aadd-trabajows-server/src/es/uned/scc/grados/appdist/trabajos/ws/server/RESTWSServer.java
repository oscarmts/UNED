package es.uned.scc.grados.appdist.trabajos.ws.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

import es.uned.scc.grados.appdist.trabajos.ws.RESTSignalGeneratorWSImpl;
import es.uned.scc.grados.appdist.trabajos.ws.common.Constants;

public class RESTWSServer {

	private static final String URL_ADDRESS = "http://localhost:9002/";

	public static void main(String[] args) {
		JAXRSServerFactoryBean sfb = new JAXRSServerFactoryBean();
		sfb.setResourceClasses(RESTSignalGeneratorWSImpl.class);
		sfb.setResourceProvider(RESTSignalGeneratorWSImpl.class,
				new SingletonResourceProvider(new RESTSignalGeneratorWSImpl(Constants.SAMPLE_TIME)));

		sfb.setAddress(URL_ADDRESS);
		// Start server
		Server server = sfb.create();
		waitForKey();
		// Stop server
		serverShutdown(sfb, server);
	}

	private static void waitForKey() {
		boolean waiting = true;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter 'yes' to exit...");
		String c;
		while (waiting) {
			try {
				c = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				c = "Yes";
			}
			if (c.equalsIgnoreCase("yes")) {
				waiting = false;
			} else {
				System.out.println("Entered '" + c + "' string (wrong!!!)\nPress 'yes' to exit...");
			}
		}
	}

	private static void serverShutdown(JAXRSServerFactoryBean sfb, Server server) {
		server.stop();
		server.destroy();
		sfb.getBus().shutdown(true);
	}

}
