package Factory;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;

public class BLFacadeFactoryLocal implements BLFacadeStrategy{

	@Override
	public BLFacade createBLFacade(ConfigXML c) {
		// TODO Auto-generated method stub
		DataAccess da= new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
		return new BLFacadeImplementation(da);
	}

}
