package core.toolFactory;

import core.actionInterface.IActionUI;
import core.modal.UiActionsSelenium;

public class ToolFactory {
	
	static IActionUI actionUI=null;

	
	public static IActionUI uiToolInstance(String uiDriverName) {
		switch (uiDriverName.toLowerCase()) {
		case "selenium":{
			System.out.println("Create Instance for "+uiDriverName);
			actionUI = new UiActionsSelenium();
			break;
		}
		default:{
			System.out.println("Unsupported UI Driver Name: " + uiDriverName);
		}	
		}
		return actionUI;
	}	
}
