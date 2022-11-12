package Factory;

import businessLogic.BLFacade;
import configuration.ConfigXML;

public interface BLFacadeStrategy {
	public BLFacade createBLFacade(ConfigXML c);
}
