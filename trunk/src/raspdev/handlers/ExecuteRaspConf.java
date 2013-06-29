package raspdev.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import raspdev.ParsConf;

public class ExecuteRaspConf extends AbstractHandler{
	private static Text confText;
	private String selectedDir;
    Shell shell;
	public ExecuteRaspConf() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		//Display display = new Display();
	    shell = new Shell(window.getShell());
		shell.setText("Select directory raspdevConf");
		shell.setSize(600, 130);
		center(shell);
		GridLayout layout = new GridLayout(2, false);
		shell.setLayout(layout);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace=true;
		Label labelconf = new Label(shell,SWT.NONE);
		labelconf.setText("Configuration directory");
		   
		    
		    confText = new Text(shell, SWT.BORDER);
		    confText.setLayoutData(gridData);
		    createOpenButton(shell);
		    shell.open();
		return null;

	}
	 protected void createOpenButton(final Composite parent){
	   	  
	    	GridData gridDatap = new GridData();
	  	    gridDatap.verticalAlignment = GridData.FILL;
	  	    gridDatap.horizontalSpan = 3;
	  	    gridDatap.grabExcessHorizontalSpace = false;
	  	    gridDatap.grabExcessVerticalSpace = false;
	  	    gridDatap.horizontalAlignment = SWT.LEFT;
	  	    final Button buttonCl = new Button(parent, SWT.PUSH);
	  		   
	  	    
	  	    buttonCl.setText("Open");
	  	    buttonCl.setLayoutData(gridDatap);
	  	    buttonCl.addListener(SWT.Selection, new Listener() {
	  	      public void handleEvent(Event event) {
	  	        DirectoryDialog directoryDialog = new DirectoryDialog(parent.getShell());
	  	        
	  	        directoryDialog.setFilterPath(selectedDir);
	  	        
	  	        String dir = directoryDialog.open();
	  	        if(dir != null) {
	  	          confText.setText(dir);
	  	          selectedDir = dir;
	  	        }
	  	      }
	  	    });
	  	  Button buttonok= new Button(parent, SWT.PUSH);
 		   
	  	    
	  	    buttonok.setText("Ok");
	  	    buttonok.setLayoutData(gridDatap);
	  	 
			buttonok.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ParsConf.setConfig(confText.getText());
					shell.close();
				}
			});
	  	 
	  	    
	    	  
	      }
		public static void center(Shell shell) {

			Rectangle bds = shell.getDisplay().getBounds();

			Point p = shell.getSize();

			int nLeft = (bds.width - p.x) / 2;
			int nTop = (bds.height - p.y) / 2;

			shell.setBounds(nLeft, nTop, p.x, p.y);
		}
}
