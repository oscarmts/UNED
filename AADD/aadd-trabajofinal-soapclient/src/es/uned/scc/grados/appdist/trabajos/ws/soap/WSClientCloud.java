package es.uned.scc.grados.appdist.trabajos.ws.soap;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import es.uned.scc.grados.appdist.trabajos.ws.OperationInfo;
import es.uned.scc.grados.appdist.trabajos.ws.common.Constants;
import es.uned.scc.grados.appdist.trabajos.ws.common.SignalGeneratorWSHelper;
import es.uned.scc.grados.appdist.trabajos.ws.frame.PlottingFrame;

public class WSClientCloud {

	public static void main(String[] args) throws ServiceException, RemoteException {

		if (isNotEmpty(args)) {
			String webServiceUrl = Constants.PROTOCOL + args[0] + ":" + Constants.PORT + Constants.APPLICATION_NAME
					+ Constants.SERVICE_URL;
			SignalGeneratorWSHelper wsHelper = new SignalGeneratorWSHelper(webServiceUrl);
			OperationInfo info = wsHelper.getOperationInfo();
			if (info.isOk()) {
				System.out.print("Running the signal generator");
				PlottingFrame window = new PlottingFrame(wsHelper);
				window.show();
			} else {
				System.out.print("Not running: " + info.getMessage());
			}
		} else {
			System.out.println("Debe introducir la dirección DNS de Amazon");
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
