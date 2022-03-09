package es.uned.scc.grados.appdist.trabajos.ws.common;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalData;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalParameters;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.WaveForm;
import es.uned.scc.grados.appdist.trabajos.ws.OperationInfo;
import es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorLocator;
import es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorWS;

public class SignalGeneratorWSHelper {

	private SignalGeneratorWS wsClient = null;

	public SignalGeneratorWSHelper(String webServiceUrl) {
		startServer(webServiceUrl);
	}

	public void startServer(String webServiceUrl) {
		SignalGeneratorLocator ws = new SignalGeneratorLocator();
		ws.setSignalGeneratorWSImplPortEndpointAddress(webServiceUrl);
		SignalGeneratorWS signalGeneratorWSPort = null;
		try {
			signalGeneratorWSPort = ws.getSignalGeneratorWSImplPort();
			signalGeneratorWSPort.start();
		} catch (RemoteException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.wsClient = signalGeneratorWSPort;
	}

	public SignalGeneratorWS getPort() {
		return wsClient;
	}

	public OperationInfo getOperationInfo() {
		OperationInfo operationInfo = null;
		try {
			operationInfo = wsClient.isRunning();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return operationInfo;
	}

	public SignalParameters getSignalParameters() {
		SignalParameters signalParameters = new SignalParameters();
		es.uned.scc.grados.appdist.trabajos.ws.SignalParameters signalParametersWS;
		try {
			signalParametersWS = wsClient.getSignalParameters();
			signalParameters.setAmplitude(signalParametersWS.getAmplitude());
			signalParameters.setFrequency(signalParametersWS.getFrequency());
			es.uned.scc.grados.appdist.trabajos.ws.WaveForm waveFormWS = signalParametersWS.getType();
			signalParameters.setType(WaveForm.valueOf(waveFormWS.getValue()));

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return signalParameters;
	}

	public SignalData getSignalValue() {
		SignalData signalData = new SignalData();
		es.uned.scc.grados.appdist.trabajos.ws.SignalData signalDataWS;
		try {
			signalDataWS = wsClient.getSignalValue();
			signalData.setOutput(signalDataWS.getOutput());
			signalData.setTime(signalDataWS.getTime());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return signalData;
	}

	public void setSignalParameters(SignalParameters signalParametersWS) {
		es.uned.scc.grados.appdist.trabajos.ws.SignalParameters signalParameters = new es.uned.scc.grados.appdist.trabajos.ws.SignalParameters();
		signalParameters.setAmplitude(signalParametersWS.getAmplitude());
		signalParameters.setFrequency(signalParametersWS.getFrequency());
		WaveForm waveFormWS = signalParametersWS.getType();

		es.uned.scc.grados.appdist.trabajos.ws.WaveForm waveForm2 = es.uned.scc.grados.appdist.trabajos.ws.WaveForm
				.fromValue(waveFormWS.name());

		signalParameters.setType(waveForm2);
		try {
			wsClient.setSignalParameters(signalParameters);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean isRunning() {
		return getOperationInfo().isOk();
	}

	public boolean start() {
		try {
			wsClient.start();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isRunning();
	}

	public boolean stop() {
		try {
			wsClient.stop();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return !isRunning();
	}

}
