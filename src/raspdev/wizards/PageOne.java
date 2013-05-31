package raspdev.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

public class PageOne extends WizardPage{

	private Composite container;


	public PageOne() {
		// TODO Auto-generated constructor stub
		super("Project Type");
		setTitle("Project Type");
		setDescription("Choose Project Type");

	}

	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		container = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		int ncol = 1;
		gl.numColumns = ncol;
		container.setLayout(gl);		
		// create the widgets  and their grid data objects 
		GridData gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		gd.widthHint = 25;

		new Label (container, SWT.NONE).setText("Choose a project: ");				
		//Text  fromText = new Text(container, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol - 1;
		//fromText.setLayoutData(gd);
		// Travel by plane		
		final Button planeButton = new Button(container, SWT.RADIO);
		
		planeButton.setText("Empty Project");
		final Button planeButton2 = new Button(container, SWT.RADIO);
		planeButton2.setText("Daemon Project");
		final Button planeButton3 = new Button(container, SWT.RADIO);
		planeButton3.setText("Command Project");
		final Button planeButton4 = new Button(container, SWT.RADIO);
		planeButton4.setText("GUI Project");
		gd = new GridData(GridData.FILL_VERTICAL);
	
		gd.horizontalSpan = ncol;
		//	    planeButton.setLayoutData(gd);
		planeButton.setSelection(false);


		Listener listener = new Listener() {
			public void handleEvent (Event e) {
			doSelection((Button)e.widget);
			}

			private void doSelection(Button widget) {
				// TODO Auto-generated method stub
				if (planeButton.getSelection() || planeButton2.getSelection() || planeButton3.getSelection() || planeButton4.getSelection()){
					setPageComplete(true);
				}
			}
			};
			planeButton.addListener(SWT.Selection, listener);
			planeButton2.addListener(SWT.Selection, listener);
			planeButton3.addListener(SWT.Selection, listener);
			planeButton4.addListener(SWT.Selection, listener);
		// Similar for carButton
		setControl(container);
		setPageComplete(false);

	}


}
