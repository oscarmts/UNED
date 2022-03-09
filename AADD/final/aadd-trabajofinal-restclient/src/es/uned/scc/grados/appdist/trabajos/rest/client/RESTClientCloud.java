package es.uned.scc.grados.appdist.trabajos.rest.client;

import es.uned.scc.grados.appdist.trabajos.rest.commons.Constants;
import es.uned.scc.grados.appdist.trabajos.rest.commons.WebClientHelper;
import es.uned.scc.grados.appdist.trabajos.rest.frame.PlottingFrame;

public class RESTClientCloud {

	public static void main(String[] args) {

		if (isNotEmpty(args)) {
			String webServiceUrl = Constants.PROTOCOL + args[0] + ":" + Constants.PORT + Constants.APPLICATION_NAME
					+ Constants.SERVICE_URL;
			WebClientHelper wcHelper = new WebClientHelper(webServiceUrl);

			PlottingFrame window = new PlottingFrame(wcHelper);
			window.show();
		} else {
			System.out.println("Debe introducir la dirección pública DNS de Amazon");
		}
	}

	private static boolean isNotEmpty(String[] args) {
		String value = null;
		if (args.length > 0) {
			value = args[0];
		}
		return value != null && !value.isEmpty();
	}

}
