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
import org.eclipse.swt.widgets.FileDialog;
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
	  private String result;
	  
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
	
	  @Override
	  protected Control createDialogArea(final Composite parent) {
	    GridLayout layout = new GridLayout();
	    layout.numColumns = 2;
	
	    parent.setLayout(layout);

	    // The text fields will grow with the size of the dialog
	    GridData gridData = new GridData();
	    gridData.grabExcessHorizontalSpace = true;
	    gridData.horizontalAlignment = GridData.FILL;

	    Label label1 = new Label(parent, SWT.BEGINNING);
	    
	    label1.setText("Qemu path");

	    qemuPathText = new Text(parent, SWT.BORDER);
	    qemuPathText.setText(System.getProperty("user.home")+"/opt/qemu/qemu-1.4.1/arm-softmmu/qemu-system-arm");
	    qemuPathText.setLayoutData(gridData);
	    
	    Label label2 = new Label(parent, SWT.BEGINNING);
	    label2.setText("Qemu kernel path");
	  
	    qemuKernelText = new Text(parent, SWT.BORDER);
	    qemuKernelText.setText(System.getProperty("user.home")+"/opt/qemu/qemu-1.4.1/kernel-qemu");
	    qemuKernelText.setLayoutData(gridData);

	    
	    Label label4 = new Label(parent, SWT.BEGINNING);
	    label4.setText("Port for redirect");
	  
	    portForWardText = new Text(parent, SWT.BORDER);
	    portForWardText.setText("2222");
	    portForWardText.setLayoutData(gridData);
	    
	   
	    Label label3 = new Label(parent, SWT.BEGINNING);
	    label3.setText("Image path");
	   
	    
	    soPathText = new Text(parent, SWT.BORDER);
	    soPathText.setText(System.getProperty("user.home")+"/opt/qemu/qemu-1.4.1/2012-08-08-wheezy-armel.img");
	    soPathText.setLayoutData(gridData);
	    createOpenButton(parent);
	    
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
  	    buttonCl.addSelectionListener(new SelectionAdapter() {
  		      public void widgetSelected(SelectionEvent event) {
  		       
  		        result= openFile(parent);
  		        soPathText.setText(result);
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
		
	      qemuKernel = qemuKernelText.getText();
	    
	      soPath = soPathText.getText();
	
	      portForWard = portForWardText.getText();
	      
	      pc.setEmuValues(qemuPath, qemuKernel, soPath, portForWard);
	     

	  }

	  public String openFile(Composite parent){
			 FileDialog dialog = new FileDialog(parent.getShell(), SWT.OPEN);
			    dialog.setFilterExtensions(new String [] {"*.img"});
			    dialog.setFilterPath(System.getProperty("user.home"));
			    String result = dialog.open();
			 return result;
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