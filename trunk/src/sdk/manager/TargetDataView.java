package sdk.manager;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchWindow;


public class TargetDataView {
	
	private boolean closed = false;
	
	public TargetDataView(IWorkbenchWindow window){
	    
		final Shell shell = new Shell(window.getShell(), SWT.CLOSE | SWT.APPLICATION_MODAL);
		shell.setText("List of Targets");
		shell.setSize(720, 250);
		centerWindow(shell);
		
		GridLayout layout = new GridLayout(1, false);

		shell.setLayout(layout);
		
		
		Label subTitleLabel = new Label(shell, SWT.NONE);
		subTitleLabel.setText("List of all available targets");

		
		final Table table = new Table(shell,SWT.SINGLE);

		TableColumn colID = new TableColumn(table,SWT.LEFT);
		colID.setText("ID");
		colID.setWidth(25);
		TableColumn colName = new TableColumn(table,SWT.LEFT);
		colName.setText("Name");
		colName.setWidth(240);
		TableColumn colType = new TableColumn(table,SWT.LEFT);
		colType.setText("Type");
		colType.setWidth(70);
		TableColumn colResolution = new TableColumn(table,SWT.LEFT);
		colResolution.setText("Kernel");
		colResolution.setWidth(100);
		TableColumn colPath = new TableColumn(table,SWT.LEFT);
		colPath.setText("Path");
		colPath.setWidth(280);
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		GridData gridDataTable = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridDataTable.horizontalSpan = 1;
		table.setLayoutData(gridDataTable);
		
		TargetList list = new TargetList();
		ArrayList<ArrayList<String>> listRS = list.getResults();
		
		insertValue(table, listRS);
		

		GridData gridDataGroupButtons = new GridData(SWT.CENTER);
		gridDataGroupButtons.grabExcessHorizontalSpace = true;
		gridDataGroupButtons.horizontalAlignment = GridData.CENTER;
		gridDataGroupButtons.horizontalSpan = 1;
		
		Button closeButton = new Button(shell, SWT.PUSH);
		closeButton.setText("Close");
		closeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				shell.close();
				closed = true;
			}
		});
		closeButton.setLayoutData(gridDataGroupButtons);

		
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

	public boolean isClosed() {
		return closed;
	}

}