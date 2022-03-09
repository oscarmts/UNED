package es.uned.scc.grados.appdist.trabajos.ws.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.ws.Endpoint;

import es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorWSImpl;
import es.uned.scc.grados.appdist.trabajos.ws.common.Constants;

public class WSServer {

	private static final String URL_ADDRESS = "http://localhost:9000/SignalGenerator";

	public static void main(String[] args) {
		SignalGeneratorWSImpl implementor = new SignalGeneratorWSImpl(Constants.SAMPLE_TIME);
		Endpoint endPoint = Endpoint.create(implementor);
		endPoint.publish(URL_ADDRESS);
		waitForKey();
		serverShutdown(endPoint);
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

	private static void serverShutdown(Endpoint endPoint) {
		endPoint.stop();
		System.exit(0);
	}

}
