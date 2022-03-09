package es.uned.scc.grados.appdist.trabajos.ws;

import javax.jws.WebService;

import es.uned.scc.grados.appdist.trabajos.signal.model.SignalGeneratorThread;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.OperationInfo;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalData;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalParameters;
import es.uned.scc.grados.appdist.trabajos.ws.common.Constants;
import es.uned.scc.grados.appdist.trabajos.ws.helper.SignalGeneratorHelper;

@WebService(endpointInterface = "es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorWS", serviceName = "SignalGenerator")
public class SignalGeneratorWSImpl implements SignalGeneratorWS {

	private SignalGeneratorHelper helper = null;

	public SignalGeneratorWSImpl() {
		this.helper = new SignalGeneratorHelper(new SignalGeneratorThread(Constants.SAMPLE_TIME));
	}

	@Override
	public OperationInfo start() {
		return helper.start();
	}

	@Override
	public OperationInfo stop() {
		return helper.stop();
	}

	@Override
	public OperationInfo isRunning() {
		return helper.isRunning();
	}

	@Override
	public SignalData getSignalValue() {
		return helper.getSignalValue();
	}

	@Override
	public SignalParameters getSignalParameters() {
		return helper.getSignalParameters();
	}

	@Override
	public void setSignalParameters(SignalParameters sp) {
		helper.setSignalParameters(sp);
	}

}
