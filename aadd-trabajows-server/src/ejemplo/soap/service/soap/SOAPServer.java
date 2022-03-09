package ejemplo.soap.service.soap;

import javax.xml.ws.Endpoint;

import ejemplo.soap.service.HelloWorld;

public class SOAPServer {

	public static void main(String[] args) {
		HelloWorld implementor = new HelloWorld();
		String address = "http://localhost:9000/HelloWorld";
		Endpoint.publish(address, implementor);

	}

}
