package es.uned.scc.grados.appdist.trabajos.ws.soap;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import es.uned.scc.grados.appdist.trabajos.ws.OperationInfo;
import es.uned.scc.grados.appdist.trabajos.ws.common.Constants;
import es.uned.scc.grados.appdist.trabajos.ws.common.SignalGeneratorWSHelper;
import es.uned.scc.grados.appdist.trabajos.ws.frame.PlottingFrame;

public class WSClient {

	public static void main(String[] args) throws ServiceException, RemoteException {
		SignalGeneratorWSHelper wsHelper = new SignalGeneratorWSHelper(Constants.WEB_SERVICE_URL);
		OperationInfo info = wsHelper.getOperationInfo();
		if (info.isOk()) {
			System.out.print("Running the signal generator");
			PlottingFrame window = new PlottingFrame(wsHelper);
			window.show();
		} else {
			System.out.print("Not running: " + info.getMessage());
		}
	}

}
