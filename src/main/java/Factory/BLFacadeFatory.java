package Factory;

import businessLogic.BLFacade;
import configuration.ConfigXML;

public class BLFacadeFatory {
	BLFacadeStrategy strategy;
	
	
	public BLFacadeFatory(ConfigXML c) {
		if (c.isBusinessLogicLocal()) {
			strategy = new BLFacadeFactoryLocal();
		}else {
			strategy = new BLFacadeFactoryRemote();
		}
	}
	
	
	
	public BLFacade createBLFacade(ConfigXML c) {
		return strategy.createBLFacade(c);
	}
}
