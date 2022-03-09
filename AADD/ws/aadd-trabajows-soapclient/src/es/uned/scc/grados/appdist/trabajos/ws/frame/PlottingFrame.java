package es.uned.scc.grados.appdist.trabajos.ws.frame;

import es.uned.scc.grados.appdist.trabajos.plot.ClientGUI;
import es.uned.scc.grados.appdist.trabajos.plot.ClientPlot;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalData;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalParameters;
import es.uned.scc.grados.appdist.trabajos.ws.common.SignalGeneratorWSHelper;

public class PlottingFrame implements ClientPlot {

	private SignalGeneratorWSHelper wsHelper;
	private ClientGUI frame = null;

	public PlottingFrame(SignalGeneratorWSHelper wsHelper) {
		this.wsHelper = wsHelper;
		frame = new ClientGUI(this);
	}

	public void show() {
		frame.getFrame().setVisible(true);
	}

	@Override
	public SignalParameters getSignalParameters() {
		return wsHelper.getSignalParameters();
	}

	@Override
	public SignalData getSignalValue() {
		return wsHelper.getSignalValue();
	}

	@Override
	public void setSignalParameters(SignalParameters signalParametersWS) {
		wsHelper.setSignalParameters(signalParametersWS);

	}

	@Override
	public boolean start() {
		return wsHelper.start();
	}

	@Override
	public boolean stop() {
		return wsHelper.stop();
	}

}
