package Main;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import es.uned.scc.grados.appdist.trabajos.ws.OperationInfo;
import es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorLocator;
import es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorWS;

public class WSClient {

	public static void main(String[] args) throws ServiceException, RemoteException {
		SignalGeneratorLocator ws = new SignalGeneratorLocator();
		SignalGeneratorWS port = ws.getSignalGeneratorWSImplPort();
		port.start();
		OperationInfo info = port.isRunning();
		if (info.isOk()) {
			System.out.print("Running the signal generator");
			PlottingFrame window = new PlottingFrame(port);
			window.show();
		} else {
			System.out.print("Not running: " + info.getMessage());
		}
	}

}
