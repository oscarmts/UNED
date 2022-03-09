package es.uned.scc.grados.appdist.trabajos.rest.client;

import es.uned.scc.grados.appdist.trabajos.rest.commons.Constants;
import es.uned.scc.grados.appdist.trabajos.rest.commons.WebClientHelper;
import es.uned.scc.grados.appdist.trabajos.rest.frame.PlottingFrame;

public class RESTClient {

	public static void main(String[] args) {

		WebClientHelper wcHelper = new WebClientHelper(Constants.WEB_SERVICE_URL);

		PlottingFrame window = new PlottingFrame(wcHelper);
		window.show();
	}

}
