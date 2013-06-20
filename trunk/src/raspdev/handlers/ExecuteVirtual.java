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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.w3c.dom.Document;
import raspdev.ParsForDevice;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ExecuteVirtual extends AbstractHandler {


	/**
	 * The constructor.
	 */
	public ExecuteVirtual() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		//Display display = new Display();
		Shell shell = new Shell(window.getShell());
		shell.setText("Virtual Device Manager");
		shell.setSize(600, 350);
		center(shell);
		GridLayout layout = new GridLayout(2, false);
		// set the layout of the shell
		//layout.makeColumnsEqualWidth=true;
		shell.setLayout(layout);
		Label listlabel = new Label(shell, SWT.NONE);
		listlabel.setText("List of existing virtual device");

		GridData gridData2 = new GridData(SWT.FILL, 
				SWT.FILL, true, true);
		gridData2.widthHint = SWT.DEFAULT;
		gridData2.heightHint = SWT.DEFAULT;
		gridData2.horizontalSpan=2;
		final Table table = new Table(shell,SWT.SINGLE);

		TableColumn col2 =new TableColumn(table,SWT.LEFT);
		col2.setText("User");
		col2.setWidth(80);
		TableColumn col3 =new TableColumn(table,SWT.LEFT);
		col3.setText("Host");
		col3.setWidth(80);
		TableColumn col4 = new TableColumn(table,SWT.LEFT);
		col4.setText("Port");
		col4.setWidth(80);
		TableColumn col5 =new TableColumn(table,SWT.LEFT);
		col5.setText("Framebuffer width");
		col5.setWidth(140);
		TableColumn col6 =new TableColumn(table,SWT.LEFT);
		col6.setText("Framebuffer height");
		col6.setWidth(80);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setLayoutData(gridData2);

		gridData2 = new GridData(SWT.TOP, 
				SWT.BEGINNING, false, false);
		gridData2.widthHint = SWT.DEFAULT;
		gridData2.heightHint = SWT.DEFAULT;
		gridData2.horizontalSpan=2;

		Button b3 = new Button(shell, SWT.PUSH);
		b3.setText("Start  ");
		b3.setLayoutData(gridData2);
		b3.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {



			}
		});


		///////////////////////////////////////////////////

		Button b = new Button(shell, SWT.PUSH);
		b.setText("Add    ");

		b.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				insertValue(table);
			}
		});

		Button b2 = new Button(shell, SWT.PUSH);
		b2.setText("Delete");
		b2.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				table.remove(table.getSelectionIndices());
			}


		});

		shell.open();

		return null;
	}
	public static void insertValue(Table table){
		ParsForDevice pars = new ParsForDevice() ;
		pars.generatePars();
		Document doc = pars.getDoc();
		String user = doc.getElementsByTagName("user").item(0).getTextContent();
		String host = doc.getElementsByTagName("host").item(0).getTextContent();
		String port = doc.getElementsByTagName("port").item(0).getTextContent();
		TableItem item = new TableItem(table, SWT.NONE);
		item.setText(new  String[]{user,host,port});

	}
	public static void center(Shell shell) {

		Rectangle bds = shell.getDisplay().getBounds();

		Point p = shell.getSize();

		int nLeft = (bds.width - p.x) / 2;
		int nTop = (bds.height - p.y) / 2;

		shell.setBounds(nLeft, nTop, p.x, p.y);
	}

}
