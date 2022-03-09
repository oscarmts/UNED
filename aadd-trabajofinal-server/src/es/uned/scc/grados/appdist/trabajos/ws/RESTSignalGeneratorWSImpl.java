package es.uned.scc.grados.appdist.trabajos.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import es.uned.scc.grados.appdist.trabajos.signal.model.SignalGeneratorThread;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.OperationInfo;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalData;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalParameters;
import es.uned.scc.grados.appdist.trabajos.ws.common.Constants;
import es.uned.scc.grados.appdist.trabajos.ws.helper.SignalGeneratorHelper;

@Path("SignalGenerator")
public class RESTSignalGeneratorWSImpl implements RESTSignalGenerator {

	private static final String CONTENT_TYPE = "text/xml";

	private SignalGeneratorHelper helper = null;

	public RESTSignalGeneratorWSImpl() {
		this.helper = new SignalGeneratorHelper(new SignalGeneratorThread(Constants.SAMPLE_TIME));
	}

	@GET
	@Path("start")
	@Produces(CONTENT_TYPE)
	@Override
	public OperationInfo start() {
		return helper.start();
	}

	@GET
	@Produces({ CONTENT_TYPE })
	@Path("stop")
	@Override
	public OperationInfo stop() {
		return helper.stop();
	}

	@GET
	@Produces({ CONTENT_TYPE })
	@Path("isrunning")
	@Override
	public OperationInfo isRunning() {
		return helper.isRunning();
	}

	@GET
	@Produces({ CONTENT_TYPE })
	@Path("get")
	@Override
	public SignalData getSignalValue() {
		return helper.getSignalValue();
	}

	@GET
	@Produces({ CONTENT_TYPE })
	@Path("getParams")
	@Override
	public SignalParameters getSignalParameters() {
		return helper.getSignalParameters();
	}

	@POST
	@Consumes(CONTENT_TYPE)
	@Path("setParams")
	@Override
	public void setSignalParameters(SignalParameters sp) {
		helper.setSignalParameters(sp);

	}

}
