package Factory;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import businessLogic.BLFacade;
import configuration.ConfigXML;

public class BLFacadeFactoryRemote implements BLFacadeStrategy{

	@Override
	public BLFacade createBLFacade(ConfigXML c) {
		String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
		 
		//URL url = new URL("http://localhost:9999/ws/ruralHouses?wsdl");
		URL url = null;
		try {
			url = new URL(serviceName);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //1st argument refers to wsdl document above
		//2nd argument is service name, refer to wsdl document above
//        QName qname = new QName("http://businessLogic/", "FacadeImplementationWSService");
        QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
        Service service = Service.create(url, qname);
        return service.getPort(BLFacade.class);
	}
	
}
