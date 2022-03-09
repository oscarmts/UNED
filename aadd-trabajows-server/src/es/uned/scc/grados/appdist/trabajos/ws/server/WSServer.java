package es.uned.scc.grados.appdist.trabajos.ws.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.ws.Endpoint;

import es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorWSImpl;

public class WSServer {

	public static void main(String[] args) {
		long time = 10;
		SignalGeneratorWSImpl implementor = new SignalGeneratorWSImpl(time);
		String address = "http://localhost:9000/SignalGenerator";
		Endpoint endPoint = Endpoint.create(implementor);
		endPoint.publish(address);
		waitForKey();
		endPoint.stop();

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

}
