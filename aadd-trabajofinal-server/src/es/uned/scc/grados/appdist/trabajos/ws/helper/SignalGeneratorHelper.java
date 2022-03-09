package es.uned.scc.grados.appdist.trabajos.ws.helper;

import es.uned.scc.grados.appdist.trabajos.signal.model.SignalGenerator;
import es.uned.scc.grados.appdist.trabajos.signal.model.SignalGeneratorThread;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.OperationInfo;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalData;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalParameters;

public class SignalGeneratorHelper {

	private SignalGeneratorThread sgThread = null;

	public SignalGeneratorHelper(SignalGeneratorThread sgThread) {
		this.sgThread = sgThread;
	}

	public OperationInfo start() {
		return this.sgThread.start();
	}

	public OperationInfo stop() {
		return this.sgThread.stop();
	}

	public OperationInfo isRunning() {
		return this.sgThread.isThreadRunning();
	}

	public SignalData getSignalValue() {
		SignalGenerator sg = this.sgThread.getSignalgenerator();
		return new SignalData(sg.getTime(), sg.getOutput());
	}

	public SignalParameters getSignalParameters() {
		SignalGenerator sg = this.sgThread.getSignalgenerator();
		return new SignalParameters(sg.getType(), sg.getAmplitude(), sg.getFrequency());
	}

	public void setSignalParameters(SignalParameters sp) {
		SignalGenerator sg = this.sgThread.getSignalgenerator();
		sg.setSignalType(sp.getType());
		sg.setAmplitude(sp.getAmplitude());
		sg.setFrequency(sp.getFrequency());
		sg.setSignalType(sp.getType());
	}

}
