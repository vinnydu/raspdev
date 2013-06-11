package raspdev.projects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import raspdev.natures.ProjectNature;
 
public class RaspProjectSupport {
    /**
     * For this marvelous project we need to:
     * - create the default Eclipse project
     * - add the custom project nature
     * - create the folder structure
     *
     * @param projectName
     * @param location
     * @param natureId
     * @return
     */
	
	private static int nProject;
    public static IProject createProject(String projectName, URI location) {
        Assert.isNotNull(projectName);
   
		Assert.isTrue(projectName.trim().length() > 0);
		
        IProject project = createBaseProject(projectName, location);
        try {
            addNature(project);
            
            String[] paths = { "src" ,"config"}; //$NON-NLS-1$ //$NON-NLS-2$
            addToProjectStructure(project, paths);
        } catch (CoreException e) {
            e.printStackTrace();
            project = null;
        }
 
        return project;
    }
 
    /**
     * Just do the basics: create a basic project.
     *
     * @param location
     * @param projectName
     */
    private static IProject createBaseProject(String projectName, URI location) {
        // it is acceptable to use the ResourcesPlugin class
        IProject newProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);

        
        if (!newProject.exists()) {
            URI projectLocation = location;
            IProjectDescription desc = newProject.getWorkspace().newProjectDescription(newProject.getName());
            if (location != null && ResourcesPlugin.getWorkspace().getRoot().getLocationURI().equals(location)) {
                projectLocation = null;
            }
 
            desc.setLocationURI(projectLocation);
            try {
                newProject.create(desc, null);
                if (!newProject.isOpen()) {
                    newProject.open(null);
                }
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }
//////////////////////////////////////////////////////////////////////////

        IFolder confFolder = newProject.getFolder("config");
        try {
		     confFolder.create(false, true, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

         confFolder.getFile("raspConf.xml");

	    confFolder = newProject.getFolder("/config");
		System.out.println(newProject);
		confFolder.getFullPath().append("raspConf.xml");
		
		
		File path=new File(System.getProperty("user.home"),"/raspdevConf/raspConf.xml");
		IFile newxml = confFolder.getFile("raspConf.xml");
		if(!newxml.exists()){

			try {
				newxml.create(new FileInputStream(path), false, null);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

		}
		//////////////////////config.txt

         confFolder.getFile("config.txt");

		IFolder cFolder = newProject.getFolder("/config");

		confFolder.getFullPath().append("config.txt");
		
		
		File cpath=new File(System.getProperty("user.home"),"/raspdevConf/config.txt");
		IFile newtxt = cFolder.getFile("config.txt");
		if(!newtxt.exists()){

			try {
				newtxt.create(new FileInputStream(cpath), false, null);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

		}
		/////////////////////////////////////////////////
		if(nProject == 2)
			createDaemonProject(newProject,projectName);
    
        return newProject;
    }
 
    private static void createDaemonProject(IProject newProject, String name){
    	
    	IFolder srcFolder = newProject.getFolder("src");
        try {
		     srcFolder.create(false, true, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

         srcFolder.getFile("daemon.py");

		srcFolder = newProject.getFolder("/src");
		System.out.println(newProject);
		System.out.println(srcFolder);
		srcFolder.getFullPath().append("daemon.py");
		
		
		File path=new File(System.getProperty("user.home"),"/raspdevConf/projectype/Daemon/daemon.py");
		IFile newxml = srcFolder.getFile("daemon.py");
		if(!newxml.exists()){

			try {
				newxml.create(new FileInputStream(path), false, null);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

		}
    }
    
    private static void createFolder(IFolder folder) throws CoreException {
        IContainer parent = folder.getParent();
        if (parent instanceof IFolder) {
            createFolder((IFolder) parent);
        }
        if (!folder.exists()) {
            folder.create(false, true, null);
        }
    }
 
    /**
     * Create a folder structure with a parent root, overlay, and a few child
     * folders.
     *
     * @param newProject
     * @param paths
     * @throws CoreException
     */
    private static void addToProjectStructure(IProject newProject, String[] paths) throws CoreException {
        for (String path : paths) {
            IFolder etcFolders = newProject.getFolder(path);
            createFolder(etcFolders);
        }
    }
 
    private static void addNature(IProject project) throws CoreException {
        if (!project.hasNature(ProjectNature.NATURE_ID)) {
            IProjectDescription description = project.getDescription();
            String[] prevNatures = description.getNatureIds();
            String[] newNatures = new String[prevNatures.length + 1];
            System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
            newNatures[prevNatures.length] = ProjectNature.NATURE_ID;
            description.setNatureIds(newNatures);
 
            IProgressMonitor monitor = null;
            project.setDescription(description, monitor);
        }
    }
    public static void setPrototype(int nProject){
    	
    	System.out.println("progetto"+nProject);
    	RaspProjectSupport.nProject = nProject;
    }
    public int getPrototype(){
    	
    	return nProject;
    }
 
}