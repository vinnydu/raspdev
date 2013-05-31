package raspdev.projects;

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
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
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
    public static IProject createProject(String projectName, URI location) {
        Assert.isNotNull(projectName);
   
		Assert.isTrue(projectName.trim().length() > 0);
		
        IProject project = createBaseProject(projectName, location);
        try {
            addNature(project);
 
            String[] paths = { "src" }; //$NON-NLS-1$ //$NON-NLS-2$
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
        FileInputStream fileStream = null;
//////////////////////////////////////////////////////////////////////////

//        ClassLoader.class.getResourceAsStream("/home/sprawl/raspdev/src/raspConf.xml");
//        
//        IFolder proFolder = newProject.getFolder("src");
//        if (proFolder.exists()) {
//           // create a new file
//           IFile newxml = proFolder.getFile("raspConf.xml");
//           try {
//			fileStream = new FileInputStream(
//			      "/home/sprawl/raspdev/src/raspConf.xml");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//           try {
//			newxml.create(fileStream, false, null);
//		} catch (CoreException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//           // create closes the file stream, so no worries.   
//        }
//
//        IFile xmlfile = proFolder.getFile("/src/raspConf.xml");
//        if (xmlfile.exists()) {
//           IPath newxmlPath = new Path("raspConf.xml");
//           try {
//			xmlfile.copy(newxmlPath, false, null);
//		} catch (CoreException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//            proFolder.getFile("raspConf.xml");
//        }
        IFolder newFolder = newProject.getFolder("src");
        try {
		     newFolder.create(false, true, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        IPath renamedPath = newFolder.getFullPath().append("raspConf.xml");
        IFile newXml = newFolder.getFile("raspConf.xml");
        try {
			newXml.move(renamedPath, false, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         newFolder.getFile("raspConf.xml");

		IFolder srcFolder = newProject.getFolder("/src");
		System.out.println(newProject);
		System.out.println(srcFolder);
		srcFolder.getFullPath().append("raspConf.xml");

		IFile newxml = srcFolder.getFile("raspConf.xml");
		if(!newxml.exists()){

			try {
				newxml.create(new FileInputStream("/home/sprawl/raspdev/src/raspConf.xml"), false, null);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

		}   
    
        return newProject;
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
 
}