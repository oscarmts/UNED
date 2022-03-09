/**
 * SignalGeneratorWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.uned.scc.grados.appdist.trabajos.ws;

public interface SignalGeneratorWS extends java.rmi.Remote {
    public es.uned.scc.grados.appdist.trabajos.ws.SignalParameters getSignalParameters() throws java.rmi.RemoteException;
    public void setSignalParameters(es.uned.scc.grados.appdist.trabajos.ws.SignalParameters signal_parameters) throws java.rmi.RemoteException;
    public es.uned.scc.grados.appdist.trabajos.ws.OperationInfo stop() throws java.rmi.RemoteException;
    public es.uned.scc.grados.appdist.trabajos.ws.OperationInfo isRunning() throws java.rmi.RemoteException;
    public es.uned.scc.grados.appdist.trabajos.ws.OperationInfo start() throws java.rmi.RemoteException;
    public es.uned.scc.grados.appdist.trabajos.ws.SignalData getSignalValue() throws java.rmi.RemoteException;
}
