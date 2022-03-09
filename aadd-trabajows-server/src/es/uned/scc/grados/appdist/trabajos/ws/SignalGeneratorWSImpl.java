package es.uned.scc.grados.appdist.trabajos.ws;

import javax.jws.WebService;

import es.uned.scc.grados.appdist.trabajos.signal.model.SignalGenerator;
import es.uned.scc.grados.appdist.trabajos.signal.model.SignalGeneratorThread;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.OperationInfo;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalData;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalParameters;

@WebService(endpointInterface = "es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorWS", serviceName = "SignalGenerator")
public class SignalGeneratorWSImpl implements SignalGeneratorWS {

	private SignalGeneratorThread sgThread;

	public SignalGeneratorWSImpl(long time) {
		this.sgThread = new SignalGeneratorThread(time);
	}

	@Override
	public OperationInfo start() {
		return this.sgThread.start();
	}

	@Override
	public OperationInfo stop() {
		return this.sgThread.stop();
	}

	@Override
	public OperationInfo isRunning() {
		return this.sgThread.isThreadRunning();
	}

	@Override
	public SignalData getSignalValue() {
		SignalGenerator sg = this.sgThread.getSignalgenerator();
		SignalData sd = new SignalData(sg.getTime(), sg.getOutput());
		return sd;
	}

	@Override
	public SignalParameters getSignalParameters() {
		SignalGenerator sg = this.sgThread.getSignalgenerator();
		SignalParameters sp = new SignalParameters(sg.getType(), sg.getAmplitude(), sg.getFrequency());
		return sp;
	}

	@Override
	public void setSignalParameters(SignalParameters sp) {
		SignalGenerator sg = this.sgThread.getSignalgenerator();
		sg.setSignalType(sp.getType());
		sg.setAmplitude(sp.getAmplitude());
		sg.setFrequency(sp.getFrequency());
		sg.setSignalType(sp.getType());
	}

}
