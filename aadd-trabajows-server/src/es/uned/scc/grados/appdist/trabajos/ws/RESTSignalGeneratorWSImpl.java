package es.uned.scc.grados.appdist.trabajos.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import es.uned.scc.grados.appdist.trabajos.signal.model.SignalGenerator;
import es.uned.scc.grados.appdist.trabajos.signal.model.SignalGeneratorThread;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.OperationInfo;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalData;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalParameters;

@Path("SignalGenerator")
public class RESTSignalGeneratorWSImpl implements RESTSignalGenerator {

	private SignalGeneratorThread sgThread = null;

	public RESTSignalGeneratorWSImpl() {
		if (sgThread == null) {
			sgThread = new SignalGeneratorThread(Long.valueOf("001"));
		}
	}

	@GET
	@Path("start")
	@Produces("text/xml")
	@Override
	public OperationInfo start() {
		return this.sgThread.start();
	}

	@GET
	@Produces({ "text/xml" })
	@Path("stop")
	@Override
	public OperationInfo stop() {
		return this.sgThread.stop();
	}

	@GET
	@Produces({ "text/xml" })
	@Path("isrunning")
	@Override
	public OperationInfo isRunning() {
		return this.sgThread.isThreadRunning();
	}

	@GET
	@Produces({ "text/xml" })
	@Path("get")
	@Override
	public SignalData getSignalValue() {
		SignalGenerator sg = this.sgThread.getSignalgenerator();
		SignalData sd = new SignalData(sg.getTime(), sg.getOutput());
		return sd;
	}

	@GET
	@Produces({ "text/xml" })
	@Path("getParams")
	@Override
	public SignalParameters getSignalParameters() {
		SignalGenerator sg = this.sgThread.getSignalgenerator();
		SignalParameters sp = new SignalParameters(sg.getType(), sg.getAmplitude(), sg.getFrequency());
		return sp;
	}

	@POST
	@Consumes("text/xml")
	@Path("setParams")
	@Override
	public void setSignalParameters(SignalParameters sp) {
		SignalGenerator sg = this.sgThread.getSignalgenerator();
		sg.setSignalType(sp.getType());
		sg.setAmplitude(sp.getAmplitude());
		sg.setFrequency(sp.getFrequency());
		sg.setSignalType(sp.getType());

	}

}
