package es.uned.scc.grados.appdist.trabajos.rest.client;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

import es.uned.scc.grados.appdist.trabajos.plot.ClientGUI;
import es.uned.scc.grados.appdist.trabajos.plot.ClientPlot;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.OperationInfo;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalData;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalParameters;

public class PlottingFrameRest implements ClientPlot {

	WebClient client = null;
	ClientGUI frame = null;

	public PlottingFrameRest(WebClient client) {
		this.client = client;
		this.frame = new ClientGUI(this);
	}

	public void show() {
		frame.getFrame().setVisible(true);
	}

	@Override
	public SignalParameters getSignalParameters() {
		client.replacePath("http://localhost:9002/SignalGenerator/getParams");
		// Set type of data received
		client.type("application/xml");
		// Call the REST method
		Response r = client.get();
		// Get the XML entity in response and cast to class
		SignalParameters signalParameters = r.readEntity(SignalParameters.class);
		return signalParameters;
	}

	@Override
	public SignalData getSignalValue() {
		client.replacePath("http://localhost:9002/SignalGenerator/get");
		// Set type of data received
		client.type("application/xml");
		// Call the REST method
		Response r = client.get();
		// Get the XML entity in response and cast to class
		SignalData signalData = r.readEntity(SignalData.class);
		return signalData;
	}

	@Override
	public void setSignalParameters(SignalParameters signalParameters) {
		client.replacePath("http://localhost:9002/SignalGenerator/setParams");
		client.header("Content-Type", "text/xml");
		client.accept("text/xml");
		Response r = client.post(signalParameters);
		System.out.println("RESPONSE POST: " + r.getStatus());
	}

	@Override
	public boolean start() {
		// Set relative path to REST method
		// REST_PATH_START = "start";
		client.replacePath("http://localhost:9002/SignalGenerator/start");
		// Set type of data received
		client.type("application/xml");
		// Call the REST method
		Response r = client.get();
		// Get the XML entity in response and cast to class
		OperationInfo i = r.readEntity(OperationInfo.class);
		System.out.println("Response: " + i.getMessage());
		return i.isOk();
	}

	@Override
	public boolean stop() {
		// Set relative path to REST method
		// REST_PATH_START = "start";
		client.replacePath("http://localhost:9002/SignalGenerator/stop");
		// Set type of data received
		client.type("application/xml");
		// Call the REST method
		Response r = client.get();
		// Get the XML entity in response and cast to class
		OperationInfo i = r.readEntity(OperationInfo.class);
		System.out.println("Response: " + i.getMessage());
		return i.isOk();
	}

}
