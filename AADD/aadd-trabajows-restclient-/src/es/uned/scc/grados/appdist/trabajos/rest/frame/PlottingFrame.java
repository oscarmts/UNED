package es.uned.scc.grados.appdist.trabajos.rest.frame;

import javax.ws.rs.core.Response;

import es.uned.scc.grados.appdist.trabajos.plot.ClientGUI;
import es.uned.scc.grados.appdist.trabajos.plot.ClientPlot;
import es.uned.scc.grados.appdist.trabajos.rest.commons.Constants;
import es.uned.scc.grados.appdist.trabajos.rest.commons.WebClientHelper;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.OperationInfo;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalData;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalParameters;

public class PlottingFrame implements ClientPlot {

	private WebClientHelper wcHelper;
	private ClientGUI frame = null;

	public PlottingFrame(WebClientHelper wcHelper) {
		this.wcHelper = wcHelper;
		this.frame = new ClientGUI(this);
	}

	public void show() {
		frame.getFrame().setVisible(true);
	}

	@Override
	public SignalParameters getSignalParameters() {
		Response r = wcHelper.get(Constants.WEB_SERVICE_URL + "/getParams");
		return r.readEntity(SignalParameters.class);
	}

	@Override
	public SignalData getSignalValue() {
		Response r = wcHelper.get(Constants.WEB_SERVICE_URL + "/get");
		return r.readEntity(SignalData.class);

	}

	@Override
	public void setSignalParameters(SignalParameters signalParameters) {
		wcHelper.post(Constants.WEB_SERVICE_URL + "/setParams", signalParameters);
	}

	@Override
	public boolean start() {
		Response r = wcHelper.get(Constants.WEB_SERVICE_URL + "/start");
		OperationInfo i = r.readEntity(OperationInfo.class);
		return i.isOk();
	}

	@Override
	public boolean stop() {
		Response r = wcHelper.get(Constants.WEB_SERVICE_URL + "/stop");
		OperationInfo i = r.readEntity(OperationInfo.class);
		return i.isOk();
	}

}
