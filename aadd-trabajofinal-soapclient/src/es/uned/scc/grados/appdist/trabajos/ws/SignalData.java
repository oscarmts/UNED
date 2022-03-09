/**
 * SignalData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.uned.scc.grados.appdist.trabajos.ws;

public class SignalData  implements java.io.Serializable {
    private double output;

    private double time;

    public SignalData() {
    }

    public SignalData(
           double output,
           double time) {
           this.output = output;
           this.time = time;
    }


    /**
     * Gets the output value for this SignalData.
     * 
     * @return output
     */
    public double getOutput() {
        return output;
    }


    /**
     * Sets the output value for this SignalData.
     * 
     * @param output
     */
    public void setOutput(double output) {
        this.output = output;
    }


    /**
     * Gets the time value for this SignalData.
     * 
     * @return time
     */
    public double getTime() {
        return time;
    }


    /**
     * Sets the time value for this SignalData.
     * 
     * @param time
     */
    public void setTime(double time) {
        this.time = time;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SignalData)) return false;
        SignalData other = (SignalData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.output == other.getOutput() &&
            this.time == other.getTime();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += new Double(getOutput()).hashCode();
        _hashCode += new Double(getTime()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SignalData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.trabajos.appdist.grados.scc.uned.es/", "signalData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("output");
        elemField.setXmlName(new javax.xml.namespace.QName("", "output"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("time");
        elemField.setXmlName(new javax.xml.namespace.QName("", "time"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
