package sdk.manager;


import java.io.File;


import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchWindow;

public class CreationDataView {

	private Text nameText;
	private Text targetText;
	private Text pathText;
	private Text resolutionText;
	private Text sdCardText;
	
	private String name;
	private String target;
	private String path;
	private String resolution;
	private String sdCard;
	
	private boolean ready = false;
	private boolean closed = false;
    
	public CreationDataView() {


	}

	
    public void ExecDataView(final IWorkbenchWindow window, final Table table) {
    	
    	
    	final Shell shell = new Shell(window.getShell(), SWT.CLOSE | SWT.APPLICATION_MODAL);
		shell.setText("Raspdev Virtual Device info");
		shell.setSize(450, 275);
		centerWindow(shell);

		GridLayout layout = new GridLayout(2, false);

		shell.setLayout(layout);
		
		Label subTitleLabel = new Label(shell, SWT.NONE);
		subTitleLabel.setText("Set informations for device configuration");
		GridData gridDataLabel = new GridData();
		gridDataLabel.horizontalAlignment = GridData.FILL;
		gridDataLabel.horizontalSpan = 2;
		subTitleLabel.setLayoutData(gridDataLabel);	
		


		Group group = new Group(shell, SWT.FILL);
		group.setText("Device Configuration");
		GridData gridDataGroup = new GridData();
		gridDataGroup.grabExcessHorizontalSpace = true;
		gridDataGroup.horizontalAlignment = GridData.FILL;
		gridDataGroup.horizontalSpan = 2;
		group.setLayoutData(gridDataGroup);
		group.setLayout(layout);
		
		
		GridData gridDataGroupComponents = new GridData();
		gridDataGroupComponents.grabExcessHorizontalSpace = true;
		gridDataGroupComponents.horizontalAlignment = GridData.FILL;
		gridDataGroupComponents.horizontalSpan = 1;

		Label nameLabel = new Label(group, SWT.NONE);
		nameLabel.setText("Device Name");
		nameText = new Text(group, SWT.BORDER);
		nameText.setText("");
		nameText.setLayoutData(gridDataGroupComponents);
		

		Label targetLabel = new Label(group, SWT.NONE);
		targetLabel.setText("Target ID");
		targetText = new Text(group, SWT.BORDER);
		targetText.setText("");
		targetText.setLayoutData(gridDataGroupComponents);
		
		Label pathLabel = new Label(group, SWT.NONE);
		pathLabel.setText("Path*");
		pathText = new Text(group, SWT.BORDER);
		pathText.setText(System.getProperty("user.home") + File.separatorChar + "raspdevDevices" + File.separatorChar);
		pathText.setLayoutData(gridDataGroupComponents);
		
		Label resolutionLabel = new Label(group, SWT.NONE);
		resolutionLabel.setText("Resolution");
		resolutionText = new Text(group, SWT.BORDER);
		resolutionText.setText("600x480");
		resolutionText.setLayoutData(gridDataGroupComponents);
		
		Label sdCardLabel = new Label(group, SWT.NONE);
		sdCardLabel.setText("SD card size");
		sdCardText = new Text(group, SWT.BORDER);
		sdCardText.setText("1GB");
		sdCardText.setLayoutData(gridDataGroupComponents);
		
		
		Label resolutionLabel1 = new Label(shell, SWT.NONE);
		resolutionLabel1.setText("*Please, insert slash (/) on Unix-system or backslash (\\) on Windows-system at the of the path.");
	//	resolutionLabel1.setFont( new Font(display,"Arial", 10, SWT.NORMAL ) );
		resolutionLabel1.setLayoutData(gridDataLabel);
		
		
		GridData gridDataGroupButtons = new GridData(SWT.CENTER);
		gridDataGroupButtons.grabExcessHorizontalSpace = true;
		gridDataGroupButtons.horizontalAlignment = GridData.CENTER;
		gridDataGroupButtons.horizontalSpan = 1;
		
		Button createButton = new Button(shell, SWT.PUSH);
		createButton.setText("Create device");
		createButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (!nameText.getText().isEmpty()  && !targetText.getText().isEmpty()) {
					String[] args = new String[5];
					shell.setEnabled(false);
					args=saveInput();
					ready = true;
					closed = true;
					
					RVDManager rvd = new RVDManager();
					rvd.creationRow(args,table);
					shell.close();
					
					
				} else {
					MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Error");
					dialog.setMessage("Error: Name and Target ID must be specified.");

					dialog.open(); 
				}
				
				
			}

		});
		createButton.setLayoutData(gridDataGroupButtons);
		
		
		Button listTargetButton = new Button(shell, SWT.PUSH);
		listTargetButton.setText("List targets");
		listTargetButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				
				TargetDataView tdw = new TargetDataView(window);
//				while (!tdw.isClosed()) {
//					if (!display.readAndDispatch())
//						display.sleep();
//				}

			}

		});
		listTargetButton.setLayoutData(gridDataGroupButtons);
		
		
		shell.addListener(SWT.Close, new Listener() 
	    { 
			@Override 
	        public void handleEvent(Event event)	{
				closed = true;
	            shell.dispose(); 
	        } 
	    }); 
		
		shell.open();
    	
    	
    }
	private String[] saveInput() {
		name = nameText.getText();
		target = targetText.getText();
		path = pathText.getText();
		resolution = resolutionText.getText();
		sdCard = sdCardText.getText();
		 String[] args = new String[5];
		 args[0] = name;
		 args[1] = target;
		 args[2] = path;
		 args[3] = sdCard;
		 args[4] = resolution;
		 return args;
		
		
	}
	
	public void centerWindow(Shell shell) {

		Rectangle bds = shell.getDisplay().getBounds();

		Point p = shell.getSize();

		int nLeft = (bds.width - p.x) / 2;
		int nTop = (bds.height - p.y) / 2;

		shell.setBounds(nLeft, nTop, p.x, p.y);
	}
	
	public String getName() {
		return name;
	}

	public String getTarget() {
		return target;
	}

	public String getPath() {
		return path;
	}

	public String getResolution() {
		return resolution;
	}

	public String getSdCard() {
		return sdCard;
	}

	public boolean isReady() {
		return ready;
	}

	public boolean isClosed() {
		return closed;
	}

	
}