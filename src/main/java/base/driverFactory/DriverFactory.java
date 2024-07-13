package base.driverFactory;

import base.actionInterface.IActionUI;
import base.modal.SeleniumActions;

public class DriverFactory {
	
	static IActionUI actionUI=null;

	
	public static IActionUI uiDriverInstance(String uiDriverName) {
		switch (uiDriverName.toLowerCase()) {
		case "selenium":{
			System.out.println("Create Instance for "+uiDriverName);
			actionUI = new SeleniumActions();
			break;
		}
		default:{
			System.out.println("Unsupported UI Driver Name: " + uiDriverName);
		}	
		}
		return actionUI;
	}	
}
