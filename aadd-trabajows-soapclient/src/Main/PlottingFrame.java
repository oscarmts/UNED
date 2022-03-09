package Main;

import java.rmi.RemoteException;

import es.uned.scc.grados.appdist.trabajos.plot.ClientGUI;
import es.uned.scc.grados.appdist.trabajos.plot.ClientPlot;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalData;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalParameters;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.WaveForm;
import es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorWS;

public class PlottingFrame implements ClientPlot {

	SignalGeneratorWS wsClient = null;
	ClientGUI frame = null;

	public PlottingFrame(SignalGeneratorWS client) {
		wsClient = client;
		frame = new ClientGUI(this);
	}

	public void show() {
		frame.getFrame().setVisible(true);
	}

	@Override
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

	@Override
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

	@Override
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

	@Override
	public boolean start() {
		try {
			wsClient.start();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean stop() {

		try {
			wsClient.stop();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
