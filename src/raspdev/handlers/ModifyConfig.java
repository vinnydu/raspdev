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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import raspdev.ParsConf;

public class ModifyConfig extends AbstractHandler{
	Shell shell;
	private Text frameBufferWidthText;
	private Text frameBufferHeightText;
	private String frameBufferWidth;
	private String frameBufferHeight;
    private static int countxt;
	
	public ModifyConfig() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		shell = new Shell(window.getShell());
		shell.setText("Modify Config.txt");
		shell.setSize(600, 150);
		center(shell);
		GridLayout layout = new GridLayout(2, false);
	
		shell.setLayout(layout);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace=true;
		Label label5 = new Label(shell, SWT.BEGINNING);
		label5.setText("Frame buffer width");
   
		frameBufferWidthText = new Text(shell, SWT.BORDER);
	    frameBufferWidthText.setLayoutData(gridData);
	    
	    Label label6 = new Label(shell, SWT.BEGINNING);
	    label6.setText("Frame buffer height");
	   
	    frameBufferHeightText = new Text(shell, SWT.BORDER);
	    frameBufferHeightText.setLayoutData(gridData);
	    GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.grabExcessHorizontalSpace=false;
	    
	    Button buttonok= new Button(shell, SWT.PUSH);
		   
  	    
  	    buttonok.setText("Ok");
  	    buttonok.setLayoutData(gridData2);
  	 
		buttonok.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveInput();
				shell.close();
			}
		});
	    shell.open();
		
		return null;
	}

	 private void saveInput() {
		 ParsConf pc = new ParsConf();
		 pc.generatePars();
		 countxt++;
		 frameBufferWidth = frameBufferWidthText.getText();
	     frameBufferHeight = frameBufferHeightText.getText();
	     pc.setFrameBuffer(frameBufferWidth, frameBufferHeight,countxt);
		 
	 }
	public static void center(Shell shell) {

		Rectangle bds = shell.getDisplay().getBounds();

		Point p = shell.getSize();

		int nLeft = (bds.width - p.x) / 2;
		int nTop = (bds.height - p.y) / 2;

		shell.setBounds(nLeft, nTop, p.x, p.y);
	}
	
	 public String getWidth() {
		    return frameBufferWidth;
	  }
	  
	  public String geHeight() {
		    return frameBufferHeight;
	  }
	
}
