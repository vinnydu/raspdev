package raspdev.wizards;

import java.net.URI;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

import raspdev.projects.RaspProjectSupport;




public class RaspWizard extends Wizard implements INewWizard , IExecutableExtension{



	private static final String WIZARD_NAME = Messages.RaspWizard_0;
	private static final String PAGE_NAME = Messages.RaspWizard_1;
	private WizardNewProjectCreationPage _pageOne;
	private IConfigurationElement _configurationElement;
	protected PageOne one;
//	protected PageTwo two;



	public RaspWizard() {

		setWindowTitle(WIZARD_NAME);
		//setNeedsProgressMonitor(true);

	}

	@Override
	public void addPages() {
		super.addPages();

		_pageOne = new WizardNewProjectCreationPage(PAGE_NAME);
		_pageOne.setTitle(Messages.RaspWizard_2);
		_pageOne.setDescription(Messages.RaspWizard_3);
		one = new PageOne();
//
//		two = new PageTwo();

		addPage(one);
//

        
		addPage(_pageOne);
	}


	@Override

	public boolean performFinish() {

		String name = _pageOne.getProjectName();
	
	    URI location = null;
	    if (!_pageOne.useDefaults()) {
	        location = _pageOne.getLocationURI();
	        System.err.println("location: " + location.toString());
	    } // else location == null
	 
	    RaspProjectSupport.createProject(name, location);
	    BasicNewProjectResourceWizard.updatePerspective(_configurationElement);

		return true;

	}

	@Override
	public void init(IWorkbench arg0, IStructuredSelection arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		// TODO Auto-generated method stub
		 _configurationElement = config;
	}
	
	
}

