package ejemplo.soap.service;

public class HelloWorld implements HelloWorldSEI {

	@Override
	public String helloWorld(String user) {
		return "Hello " + user;
	}

}
