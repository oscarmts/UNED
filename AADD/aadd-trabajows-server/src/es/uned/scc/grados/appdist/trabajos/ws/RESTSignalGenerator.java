package es.uned.scc.grados.appdist.trabajos.ws;

import es.uned.scc.grados.appdist.trabajos.signal.model.data.OperationInfo;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalData;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalParameters;

public interface RESTSignalGenerator {

	public OperationInfo start();

	public OperationInfo stop();

	public OperationInfo isRunning();

	public SignalData getSignalValue();

	public SignalParameters getSignalParameters();

	public void setSignalParameters(SignalParameters signal_parameters);

}
