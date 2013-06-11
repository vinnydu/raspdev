package raspdev;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class RaspDevSDKManager {
	
	public RaspDevSDKManager() {
		// TODO Auto-generated constructor stub
	}
	
	
	 public static void main(String[] args) {
		 
		 Display display = new Display();
	        Shell shell = new Shell(display);
	        shell.setText("Raspdev SDK Manager");
	        shell.setSize(600, 350);
	        center(shell);
	        // Create a new Gridlayout with 2 columns 
	        // where the 2 column do not have the 
	        // same size
	        GridLayout layout = new GridLayout(2, false);
	        // set the layout of the shell
	        shell.setLayout(layout);
	       
	        // Create a label and a button
	        Label pathlabel = new Label(shell, SWT.NONE);
	        pathlabel.setText("SDK Path:");
	        
	        Label sdkpath = new Label(shell,SWT.NONE);
	    
	        sdkpath.setText("/home/sprawl/");
	        sdkpath.setEnabled(false);
	        Button button = new Button(shell, SWT.CHECK);
	        button.setText("Installed");
	     
	        //////////////////////////////////////////////////////////////////////////////////////
	        

	        GridData gridData2 = new GridData(SWT.FILL, 
	                SWT.FILL, true, false);
	        gridData2.widthHint = SWT.DEFAULT;
	        gridData2.heightHint = SWT.DEFAULT;
	        gridData2.horizontalSpan=2;
	     // 
	        Group group = new Group(shell, SWT.NONE);
	        group.setText("Packages");
	        gridData2 = new GridData(SWT.FILL, SWT.FILL, true, false);
	        gridData2.horizontalSpan= 2;
	        gridData2.grabExcessVerticalSpace = true;
	        group.setLayoutData(gridData2);
	        group.setLayout(new FillLayout());

	        treeMenu(group);
	      
	     
	        Composite composite = new Composite(shell, SWT.BORDER);
	        gridData2 = new GridData(SWT.FILL, SWT.FILL, true, false);
	        gridData2.horizontalSpan= 2;
	        composite.setLayoutData(gridData2);
	        composite.setLayout(new GridLayout(1, false));
	        
	        
	        Text text2 = new Text(composite, SWT.NONE);
	        text2.setText("Testing");
	        gridData2 = new GridData(SWT.FILL, SWT.FILL, true, false);
	        text2.setLayoutData(gridData2);
	        
//	        text2 = new Text(composite, SWT.NONE);
//	        text2.setText("Another test");
//	        gridData2 = new GridData(SWT.FILL, SWT.FILL, true, false);
//	        text2.setLayoutData(gridData2);
//	      
	 /////////////////////////////////////////////////////////////////////////////////////       
	        // Create new layout data
	        GridData data = new GridData(GridData.FILL, 
	                GridData.BEGINNING, true, false, 2, 1);
	        Label label = new Label(shell,SWT.NONE);
	        label.setLayoutData(data);
	        
	        // Create a new label which is used as a separator
	        label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
	        // Create new layout data
	        data = new GridData(GridData.FILL, GridData.BEGINNING, true,
	                false, 2, 1);
	        data.horizontalSpan=2;
	        label.setLayoutData(data);
	        
	        // Create a right aligned button
	        Button b = new Button(shell, SWT.PUSH);
	        b.setText("Install packages");
	        
	        
	        data = new GridData(GridData.END, GridData.BEGINNING, false,
	                false, 2, 1);
	        b.setLayoutData(data);
	        ProgressBar progressBar = new ProgressBar(composite, SWT.HORIZONTAL);
	        GridData progressGridData = new GridData();
//	        progressGridData.heightHint = 15;
//	        progressGridData.widthHint = 100;
	        progressGridData.horizontalAlignment = GridData.FILL;
	        progressBar.setLayoutData(progressGridData);
	        
	      //  shell.pack();
	        shell.open();
	        while (!shell.isDisposed()) {
	            if (!display.readAndDispatch())
	                display.sleep();
	        }
	        display.dispose();
	    }
	 public static void center(Shell shell) {

	        Rectangle bds = shell.getDisplay().getBounds();

	        Point p = shell.getSize();

	        int nLeft = (bds.width - p.x) / 2;
	        int nTop = (bds.height - p.y) / 2;

	        shell.setBounds(nLeft, nTop, p.x, p.y);
	    }
	 
	 
	 public static void treeMenu(Group group){

	        final Tree tree = new Tree(group, SWT.CHECK | SWT.V_SCROLL);
	        boolean checked=false,grayed = false;
	        for (int i=0; i<5;i++) {
	            TreeItem item = new TreeItem(tree, SWT.NONE);
	            item.setText(String.valueOf(i));
	            item.setChecked(true);
	            for (int j=0; j<3;j++) {
	                TreeItem subItem = new TreeItem(item, SWT.NONE);
	                subItem.setText(String.valueOf(i) + " " + String.valueOf(j));
	                if (subItem.getGrayed() || checked != subItem.getChecked()) {
		                checked = grayed = true;
		                break;
	            }}
	        }
	        tree.pack();
	        
	        Menu menu = new Menu(tree);
	        MenuItem menuItem = new MenuItem(menu, SWT.NONE);
	        menuItem.setText("Print Element");
	        menuItem.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent event) {
	                System.out.println(tree.getSelection()[0].getText());
	                tree.getSelection()[0].setChecked(true);
	            }
	        });
	        tree.setMenu(menu);

		 
	 }

}
