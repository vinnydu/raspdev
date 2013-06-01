package raspdev.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;

import raspdev.DeviceManager;

public class OpenDevice extends AbstractHandler{

	public OpenDevice() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		DeviceManager dialog = new DeviceManager(null);
		dialog.create();
		if (dialog.open() == Window.OK) {
			System.out.println(dialog.getQemuPath());
			System.out.println(dialog.getKernel());
			System.out.println(dialog.getSOPath());
			System.out.println(dialog.getPort());
		}

		return null;
	}

}
