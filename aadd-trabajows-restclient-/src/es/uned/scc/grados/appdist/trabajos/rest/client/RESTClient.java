package es.uned.scc.grados.appdist.trabajos.rest.client;

import org.apache.cxf.jaxrs.client.WebClient;

public class RESTClient {

	public static void main(String[] args) {

		String REST_SERVICE = "http://localhost:9002/SignalGenerator";

		WebClient client = WebClient.create(REST_SERVICE);

		PlottingFrameRest window = new PlottingFrameRest(client);
		window.show();
	}

}
