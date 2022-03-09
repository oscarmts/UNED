package es.uned.scc.grados.appdist.trabajos.ws;

public class SignalGeneratorWSProxy implements es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorWS {
  private String _endpoint = null;
  private es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorWS signalGeneratorWS = null;
  
  public SignalGeneratorWSProxy() {
    _initSignalGeneratorWSProxy();
  }
  
  public SignalGeneratorWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initSignalGeneratorWSProxy();
  }
  
  private void _initSignalGeneratorWSProxy() {
    try {
      signalGeneratorWS = (new es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorLocator()).getSignalGeneratorWSImplPort();
      if (signalGeneratorWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)signalGeneratorWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)signalGeneratorWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (signalGeneratorWS != null)
      ((javax.xml.rpc.Stub)signalGeneratorWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorWS getSignalGeneratorWS() {
    if (signalGeneratorWS == null)
      _initSignalGeneratorWSProxy();
    return signalGeneratorWS;
  }
  
  public es.uned.scc.grados.appdist.trabajos.ws.SignalParameters getSignalParameters() throws java.rmi.RemoteException{
    if (signalGeneratorWS == null)
      _initSignalGeneratorWSProxy();
    return signalGeneratorWS.getSignalParameters();
  }
  
  public void setSignalParameters(es.uned.scc.grados.appdist.trabajos.ws.SignalParameters signal_parameters) throws java.rmi.RemoteException{
    if (signalGeneratorWS == null)
      _initSignalGeneratorWSProxy();
    signalGeneratorWS.setSignalParameters(signal_parameters);
  }
  
  public es.uned.scc.grados.appdist.trabajos.ws.OperationInfo stop() throws java.rmi.RemoteException{
    if (signalGeneratorWS == null)
      _initSignalGeneratorWSProxy();
    return signalGeneratorWS.stop();
  }
  
  public es.uned.scc.grados.appdist.trabajos.ws.OperationInfo isRunning() throws java.rmi.RemoteException{
    if (signalGeneratorWS == null)
      _initSignalGeneratorWSProxy();
    return signalGeneratorWS.isRunning();
  }
  
  public es.uned.scc.grados.appdist.trabajos.ws.OperationInfo start() throws java.rmi.RemoteException{
    if (signalGeneratorWS == null)
      _initSignalGeneratorWSProxy();
    return signalGeneratorWS.start();
  }
  
  public es.uned.scc.grados.appdist.trabajos.ws.SignalData getSignalValue() throws java.rmi.RemoteException{
    if (signalGeneratorWS == null)
      _initSignalGeneratorWSProxy();
    return signalGeneratorWS.getSignalValue();
  }
  
  
}