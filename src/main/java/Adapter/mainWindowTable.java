package Adapter;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Registered;

public class mainWindowTable {

	public static void main(String[] args) {
		
		try {
			BLFacade blFacade= new BLFacadeImplementation(); 
			Registered register = new Registered("registered","123",1234);
			Registered register2 = blFacade.findUser(register);
			
			WindowTable vt = new WindowTable(register2);
			vt.setVisible(true);
		} 
		catch (Exception e) {
			e.printStackTrace();
			}
	}

}