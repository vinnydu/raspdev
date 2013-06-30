package raspdev.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import raspdev.ParsConf;
import raspdev.projects.RaspProjectSupport;

public class PageOne extends WizardPage{

	private Composite container;
	private Text userText;
	private Text hostText;
	private Text hostPathText;
	private Text projectPathToDeployText;
	private Text privateKeyText;
	
	private String user;
	private String host;
	private String hostPath;
	private String projectPathToDeploy;
	private String privateKey;

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

		GridLayout layout = new GridLayout(2, false);
		// set the layout of the composite
		container.setLayout(layout);
		GridData data = new GridData(GridData.FILL, GridData.BEGINNING, false,
				false, 2, 1);
		new Label (container, SWT.NONE).setText("Choose a project: ");	
		// radio button	
		final Button planeButton = new Button(container, SWT.RADIO);


		planeButton.setText("Empty");
		planeButton.setLayoutData(data);
		data = new GridData(GridData.FILL, GridData.BEGINNING, true,
				false, 2, 1);
		final Button planeButton2 = new Button(container, SWT.RADIO);
		planeButton2.setText("Daemon");
		planeButton2.setLayoutData(data);
		final Button planeButton3 = new Button(container, SWT.RADIO);
		planeButton3.setText("Command");
		planeButton3.setLayoutData(data);
		final Button planeButton4 = new Button(container, SWT.RADIO);
		planeButton4.setText("GUI");
		planeButton4.setLayoutData(data);

		planeButton.setSelection(false);


		Listener listener = new Listener() {
			public void handleEvent (Event e) {
				doSelection((Button)e.widget);
			}

			private void doSelection(Button widget) {
				// TODO Auto-generated method stub
				if (planeButton.getSelection())
					RaspProjectSupport.setPrototype(1);

				if(planeButton2.getSelection())
					RaspProjectSupport.setPrototype(2);
				if(planeButton3.getSelection()) 
					RaspProjectSupport.setPrototype(3);
				if(planeButton4.getSelection())
					RaspProjectSupport.setPrototype(4);
				setPageComplete(true);
				isPageComplete();

			}
		};
		planeButton.addListener(SWT.Selection, listener);
		planeButton2.addListener(SWT.Selection, listener);
		planeButton3.addListener(SWT.Selection, listener);
		planeButton4.addListener(SWT.Selection, listener);

		setControl(container);

		//////////////////////////////PAGE CONFIG
		Group group = new Group(container, SWT.FILL);
		group.setText("Xml Host Config");
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace=true;
		group.setLayoutData(gridData);
		group.setLayout(layout);
		Label label1 = new Label(group, SWT.NONE);
		label1.setText("User");

		userText = new Text(group, SWT.BORDER);
		userText.setText("pi");
		userText.setLayoutData(gridData);

		Label label2 = new Label(group, SWT.NONE);
		label2.setText("Host");


		hostText = new Text(group, SWT.BORDER);
		hostText.setText("localhost");
		hostText.setLayoutData(gridData);
		Label label3 = new Label(group, SWT.NONE);
		label3.setText("Host path");

		hostPathText = new Text(group, SWT.BORDER);
		hostPathText.setText("/home/pi/apps/");
		hostPathText.setLayoutData(gridData);
		Label label4 = new Label(group, SWT.NONE);
		label4.setText("Project path to deploy");

		projectPathToDeployText = new Text(group, SWT.BORDER);
		projectPathToDeployText.setText(System.getProperty("user.home")+"/workspace/");
		projectPathToDeployText.setLayoutData(gridData);
		Label label5 = new Label(group, SWT.NONE);
		label5.setText("Private key path");

		privateKeyText = new Text(group, SWT.BORDER);
		privateKeyText.setText(System.getProperty("user.home")+"/eclipse/plugins/Raspdev_1.5.0/raspdevConf/id_raspberry_rsa");
		privateKeyText.setLayoutData(gridData);
		 
		setPageComplete(false);


	}

	@Override
	public boolean isPageComplete() {
		// TODO Auto-generated method stub
		saveInput();
		return super.isPageComplete();
	}

	private void saveInput() {
		ParsConf pc = new ParsConf();
		pc.generatePars();
		user = userText.getText();

		host = hostText.getText();

		hostPath = hostPathText.getText();

		projectPathToDeploy = projectPathToDeployText.getText();
		privateKey = privateKeyText.getText();
		pc.setHostValues(user, host, hostPath, projectPathToDeploy, privateKey);

	}
	
}
