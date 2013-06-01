package raspdev.handlers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.w3c.dom.Document;


import raspdev.ParsConf;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ExecuteEmulation extends AbstractHandler {


	/**
	 * The constructor.
	 */
	public ExecuteEmulation() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"Raspdev",
				"Execution Raspbian wheezy");
		List<String> args = new ArrayList<String>();

		ParsConf pars = new ParsConf();
		pars.generatePars();
		Document doc = pars.getDoc();


		args.add (doc.getElementsByTagName("qemu-path").item(0).getTextContent()); // command name
		args.add ("-kernel"); // optional args added as separate list items
		args.add (doc.getElementsByTagName("qemu-kernel").item(0).getTextContent());
		args.add ("-cpu");
		args.add ("arm1176");
		args.add ("-m");
		args.add ("256");
		args.add ("-M");
		args.add ("versatilepb");
		args.add ("-no-reboot");
		args.add ("-serial");
		args.add ("stdio");
		args.add ("-append");
		args.add ("root=/dev/sda2 panic=1");
		args.add ("-hda");
		args.add (doc.getElementsByTagName("raspbian-path").item(0).getTextContent());
		args.add ("-redir");
		args.add ("tcp:"+doc.getElementsByTagName("port").item(0).getTextContent()+"::22");


		ProcessBuilder pb = new ProcessBuilder (args);
		try {
			 pb.start();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 


		return null;
	}
}
