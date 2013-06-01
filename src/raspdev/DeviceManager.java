package raspdev;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import raspdev.ParsConf;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class DeviceManager extends TitleAreaDialog{

	
	  private Text qemuPathText;
	  private Text qemuKernelText;
	  private Text soPathText;
	  private Text portForWardText;
	  private String qemuPath;
	  private String qemuKernel;
	  private String soPath;
	  private String portForWard;
	  
	public DeviceManager(Shell parentShell) {
	
		// TODO Auto-generated constructor stub
		 super(parentShell);
	}
	
	@Override
	  public void create() {
	    super.create();
	    // Set the title
	    setTitle("Device Xml Manager");
	    // Set the message
	    setMessage("Device Manager", 
	        IMessageProvider.INFORMATION);
	  }
	
//	 public static void main(String[] args) {
//		 
//		 Display display = new Display();
//
//	        Shell shell = new Shell(display);
//	        GridLayout layout = new GridLayout(2, false);
//	        // set the layout of the shell
//	        shell.setLayout(layout);
//	        // Layout manager handle the layout
//	        // of the widgets in the container
//	     
//	        Button button =  new Button(shell, SWT.PUSH);
//	        button.setText("press");
//			
//	        button.addSelectionListener(new SelectionAdapter() {
//			     @Override
//			     public void widgetSelected(SelectionEvent e) {
//			         // Handle the selection event
//			         System.out.println("Called!");
//			     }
//			 }); 
//	        shell.pack();
//	        //TODO add some widgets to the Shell
//	        shell.open();
//	        while (!shell.isDisposed()) {
//	            if (!display.readAndDispatch())
//	                display.sleep();
//	        }
//	      
//		  display.dispose();
//	 }
	
	  @Override
	  protected Control createDialogArea(Composite parent) {
	    GridLayout layout = new GridLayout();
	    layout.numColumns = 2;
	    // layout.horizontalAlignment = GridData.FILL;
	    parent.setLayout(layout);

	    // The text fields will grow with the size of the dialog
	    GridData gridData = new GridData();
	    gridData.grabExcessHorizontalSpace = true;
	    gridData.horizontalAlignment = GridData.FILL;

	    Label label1 = new Label(parent, SWT.NONE);
	    label1.setText("Qemu path");

	    qemuPathText = new Text(parent, SWT.BORDER);
	    qemuPathText.setLayoutData(gridData);
	    
	    Label label2 = new Label(parent, SWT.NONE);
	    label2.setText("Qemu kernel path");
	    
	    
	    // You should not re-use GridData
	    gridData = new GridData();
	    gridData.grabExcessHorizontalSpace = true;
	    gridData.horizontalAlignment = GridData.FILL;
	    qemuKernelText = new Text(parent, SWT.BORDER);
	    qemuKernelText.setLayoutData(gridData);
	    
	    Label label3 = new Label(parent, SWT.NONE);
	    label3.setText("SO path");
	    gridData = new GridData();
	    gridData.grabExcessHorizontalSpace = true;
	    gridData.horizontalAlignment = GridData.FILL;
	    soPathText = new Text(parent, SWT.BORDER);
	    soPathText.setLayoutData(gridData);
	    Label label4 = new Label(parent, SWT.NONE);
	    label4.setText("Port for redirect");
	    gridData = new GridData();
	    gridData.grabExcessHorizontalSpace = true;
	    gridData.horizontalAlignment = GridData.FILL;
	    portForWardText = new Text(parent, SWT.BORDER);
	    portForWardText.setLayoutData(gridData);
	    return parent;
	  }

	  @Override
	  protected void createButtonsForButtonBar(Composite parent) {
	    GridData gridData = new GridData();
	    gridData.verticalAlignment = GridData.FILL;
	    gridData.horizontalSpan = 3;
	    gridData.grabExcessHorizontalSpace = true;
	    gridData.grabExcessVerticalSpace = true;
	    gridData.horizontalAlignment = SWT.CENTER;

	    parent.setLayoutData(gridData);
	    // Create Add button
	    // Own method as we need to overview the SelectionAdapter
	    createOkButton(parent, OK, "Add", true);
	    // Add a SelectionListener

	    // Create Cancel button
	    Button cancelButton = 
	        createButton(parent, CANCEL, "Cancel", false);
	    // Add a SelectionListener
	    cancelButton.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	        setReturnCode(CANCEL);
	        close();
	      }
	    });
	  }

	  protected Button createOkButton(Composite parent, int id, 
	      String label,
	      boolean defaultButton) {
	    // increment the number of columns in the button bar
	    ((GridLayout) parent.getLayout()).numColumns++;
	    Button button = new Button(parent, SWT.PUSH);
	    button.setText(label);
	    button.setFont(JFaceResources.getDialogFont());
	    button.setData(new Integer(id));
	    button.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent event) {
	        if (isValidInput()) {
	          okPressed();
	        }
	      }
	    });
	    if (defaultButton) {
	      Shell shell = parent.getShell();
	      if (shell != null) {
	        shell.setDefaultButton(button);
	      }
	    }
	    setButtonLayoutData(button);
	    return button;
	  }

	  private boolean isValidInput() {
	    boolean valid = true;
	    if (qemuPathText.getText().length() == 0) {
	      setErrorMessage("Please maintain the Qemu path");
	      valid = false;
	    }
	    if (qemuKernelText.getText().length() == 0) {
	      setErrorMessage("Please maintain the Qemu kernel path");
	      valid = false;
	    }
	    if (soPathText.getText().length() == 0) {
		      setErrorMessage("Please maintain the SO path");
		      valid = false;
		    }
	    if (portForWardText.getText().length() == 0) {
		      setErrorMessage("Please maintain the port");
		      valid = false;
		    }
	    return valid;
	  }
	  
	  @Override
	  protected boolean isResizable() {
	    return true;
	  }

	  // Copy textFields because the UI gets disposed
	  // and the Text Fields are not accessible any more.
	  private void saveInput() {
		  ParsConf pc = new ParsConf();
		  pc.generatePars();
		  qemuPath = qemuPathText.getText();
		  //pc.setQemuPath(qemuPath);
	      qemuKernel = qemuKernelText.getText();
	      //pc.setQemuPath(qemuKernel);
	      soPath = soPathText.getText();
	      //pc.setQemuPath(soPath);
	      portForWard = portForWardText.getText();
	      pc.setEmuValues(qemuPath, qemuKernel, soPath, portForWard);

	  }

	  @Override
	  protected void okPressed() {
	    saveInput();
	    System.out.println("saved");
	    super.okPressed();
	  }

	  public String getQemuPath() {
	    return qemuPath;
	  }

	  public String getKernel() {
	    return qemuKernel;
	  }
	  
	  public String getSOPath() {
		    return soPath;
	  }
	  
	  public String getPort() {
		    return portForWard;
	  }

	
  
}
