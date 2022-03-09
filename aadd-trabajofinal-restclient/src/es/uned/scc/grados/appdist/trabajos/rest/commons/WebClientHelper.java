package es.uned.scc.grados.appdist.trabajos.rest.commons;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

public class WebClientHelper {

	private static final String CONTENT_TYPE_GET = "application/xml";
	private static final String CONTENT_TYPE_POST = "text/xml";

	private WebClient client = null;
	private String url = null;

	public WebClientHelper(String url) {
		client = WebClient.create(url);
		this.url = url;
	}

	public Response get(String url) {
		client.replacePath(url);
		client.type(CONTENT_TYPE_GET);
		return client.get();
	}

	public Response post(String url, Object object) {
		client.replacePath(url);
		client.header("Content-Type", CONTENT_TYPE_POST);
		client.accept(CONTENT_TYPE_POST);
		return client.post(object);
	}

	public String getWebServiceUrl() {
		return url;
	}
}
