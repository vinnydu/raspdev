package sdk.manager;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchWindow;

import raspdev.ParsConf;


public class RVDManager{

	private Label bottomLabel;
	private ProgressBar pb;
	CreationDataView cdv = new CreationDataView();
	public RVDManager() {
	
		
	}

	public void managerOn(final IWorkbenchWindow window){
		
		//final Display display = new Display();
		final Shell shell = new Shell(window.getShell());
		shell.setText("Raspdev Virtual Device Manager");
		shell.setSize(650, 480);
		centerWindow(shell);
		
		GridLayout layout = new GridLayout(6, false);

		shell.setLayout(layout);
		
		Label imageLabel = new Label(shell, SWT.WRAP);
//		Image image = new Image(window.,"raspy.png");
//		final int width = image.getBounds().width; 
//		final int height = image.getBounds().height; 
//		Image scaledImage = new Image(Display.getDefault(), image.getImageData().scaledTo((int)(width * 0.17),(int)(height * 0.17))); 
//		imageLabel.setImage(scaledImage);
		GridData gridDataImage = new GridData();
		gridDataImage.verticalSpan = 3;
		imageLabel.setLayoutData(gridDataImage);
		
		
		
		Label subTitleLabel = new Label(shell, SWT.NONE);
		subTitleLabel.setText("Raspdev Virtual Device Manager v1.0.0");
//		subTitleLabel.setFont( new Font(display,"Arial", 30, SWT.NORMAL ) );
		GridData gridDataLabel = new GridData();
		gridDataLabel.horizontalAlignment = GridData.FILL;
		gridDataLabel.horizontalSpan = 5;
		subTitleLabel.setLayoutData(gridDataLabel);
		
		
		Label subTitleLabel2 = new Label(shell, SWT.NONE);
		subTitleLabel2.setText("Parthenope University of Naples Project");
//		subTitleLabel2.setFont( new Font(display,"Arial", 14, SWT.ITALIC ) );
		GridData gridDataLabel2 = new GridData();
		gridDataLabel2.horizontalAlignment = GridData.FILL;
		gridDataLabel2.horizontalSpan = 5;
		subTitleLabel2.setLayoutData(gridDataLabel2);
		
		Label subTitleLabel3 = new Label(shell, SWT.NONE);
		subTitleLabel3.setText("List of existing Raspdev Virtual Device located to " + 
				System.getProperty("user.home") + File.separatorChar + "raspdevDevices");
		GridData gridDataLabel3 = new GridData();
		gridDataLabel3.horizontalAlignment = GridData.FILL;
		gridDataLabel3.horizontalSpan = 5;
		subTitleLabel3.setLayoutData(gridDataLabel3);
		
		
		final Table table = new Table(shell,SWT.SINGLE);

		TableColumn colID = new TableColumn(table,SWT.LEFT);
		colID.setText("ID");
		colID.setWidth(25);
		TableColumn colName = new TableColumn(table,SWT.LEFT);
		colName.setText("Name");
		colName.setWidth(100);
		TableColumn colType = new TableColumn(table,SWT.LEFT);
		colType.setText("Type");
		colType.setWidth(70);
		TableColumn colResolution = new TableColumn(table,SWT.LEFT);
		colResolution.setText("Resolution");
		colResolution.setWidth(80);
		TableColumn colPath = new TableColumn(table,SWT.LEFT);
		colPath.setText("Path");
		colPath.setWidth(180);
		TableColumn colTarget = new TableColumn(table,SWT.LEFT);
		colTarget.setText("Target");
		colTarget.setWidth(50);
		TableColumn colSDCard = new TableColumn(table,SWT.LEFT);
		colSDCard.setText("SD Card");
		colSDCard.setWidth(50);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		GridData gridDataTable = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridDataTable.horizontalSpan = 6;
		table.setLayoutData(gridDataTable);
		
		
		RVDList list = new RVDList();
		ArrayList<ArrayList<String>> listRS = list.getResults();
		
		insertValue(table, listRS);
	
		
		bottomLabel = new Label(shell, SWT.NONE);
		bottomLabel.setText("");
		GridData gridDataBottomLabel = new GridData();
		gridDataBottomLabel.horizontalAlignment = GridData.FILL;
		gridDataBottomLabel.horizontalSpan = 2;
		bottomLabel.setLayoutData(gridDataBottomLabel);
		
		Button startButton = new Button(shell, SWT.PUSH);
		startButton.setText("Start Device");
		
		startButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (table.getSelectionIndex() !=-1) {
					String[] args = {"startRVD","-i",table.getItem(table.getSelectionIndex()).getText(0)};
					new RVDStarter(args);
				} else {
					
					MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Error");
					dialog.setMessage("Error: Select a device.");

					dialog.open(); 
				}
			}
		});
		

		Button createButton = new Button(shell, SWT.PUSH);
		createButton.setText("Create Device");
		createButton.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
			    cdv.ExecDataView(window,table);
//				while (!cdv.isClosed()) {
//					if (!display.readAndDispatch()) {
//						pb.setVisible(true);
//						shell.close();
//						display.sleep();
//				}
//				}
				
//				pb.setVisible(false);
				//if (cdv.isReady()) {
					 
				//}
			}
		});

		setBottomLabel();
		Button deleteButton = new Button(shell, SWT.PUSH);
		deleteButton.setText("Delete device");
		deleteButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (table.getSelectionIndex() !=-1) {
					String[] args = {"deleteRVD","-i",table.getItem(table.getSelectionIndex()).getText(0)};
					new RVDDelete(args);
					table.remove(table.getSelectionIndices());					
					
					setBottomLabel();
				
				} else {
					MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Error");
					dialog.setMessage("Error: Select a device.");

					dialog.open(); 
				}
			}

		});
		
		
		pb = new ProgressBar(shell, SWT.INDETERMINATE);
		pb.setSize(120, 40);
		GridData gridDataPb = new GridData(SWT.CENTER);
		gridDataPb.grabExcessHorizontalSpace = true;
		gridDataPb.horizontalAlignment = GridData.CENTER;
		gridDataPb.horizontalSpan = 1;
		pb.setLayoutData(gridDataPb);
		pb.setVisible(false);

		setBottomLabel();
		
	   
		

		shell.open();

		
	}
	
	public void creationRow(String[] argsData,Table table){
		  String[] args = new String[9];
		  args[0] = "createRVD";
		  args[1] = "-n";
		  args[2] = argsData[0];
		  args[3] = "-t";
		  args[4] = argsData[1];
		  args[5] = "-p";
		  args[6] = argsData[2];
		  args[7] = "-c";
		  args[8] = argsData[3];
		  args[7] = "-r";
		  args[8] = argsData[4];

		  
		  new RVDCreation(args);
		  table.removeAll();
		  RVDList list = new RVDList();
		  ArrayList<ArrayList<String>> listRS = list.getResults();
		
		  insertValue(table, listRS);
		  
		 
		 
		
	}
	public void insertValue(Table table, ArrayList<ArrayList<String>> listRS){

		for(int i=0; i<listRS.size(); i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText((String[]) listRS.get(i).toArray(new String[listRS.get(i).size()]));
		}

	}
	
	
	public void centerWindow(Shell shell) {

		Rectangle bds = shell.getDisplay().getBounds();

		Point p = shell.getSize();

		int nLeft = (bds.width - p.x) / 2;
		int nTop = (bds.height - p.y) / 2;

		shell.setBounds(nLeft, nTop, p.x, p.y);
	}
	
	public void setBottomLabel() {
		
		int deviceNumbers = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:"+ParsConf.getConfig()+"/tools/virtual_devices.db"); 
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT count(*) FROM devices"); 
			rs.next();
			deviceNumbers = rs.getInt(1);
			rs.close(); 
			conn.close();
		} catch (ClassNotFoundException e) {
			;
		} catch (SQLException e) {
			;
		}
		
		bottomLabel.setText("Number of devices: " + deviceNumbers);
	}

}