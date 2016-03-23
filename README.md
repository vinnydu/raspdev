## What is RaspDev?

RaspDev is a Raspberry SDK, framework and plugin for Eclipse, for Python software development.
 - QEMU integration
 - Template projects
 - Deploy to physical or virtual machine and many others
 - Release 1.6.0


## Integration in Eclipse.

- Creation of four kinds of project: empty, command, daemon, GUI.
- Generation of template project with default files.
- Configuration with a xml file and manipolation of config.txt for Raspberry SO.
- Raspberry QEMU emulation.
- Project deploy with ssk key, for virtual and physical machine.
- Autogeneration setup.py with RaspManifest.
- Compressing and decompressing directory to deploy.
- Configuration GUI RVD (Raspberry Virtual Device).

## Use of raspdevPlugin:

Every time you start eclipse, select the directory raspdevSDK on menubar: RaspDev-->Select RaspdevSDK and give ok.
The first time you use raspdevSDK with the plugin, you have to create the target with: RaspDev -> Create Target. After selecting the sdk directory.
When you create a device, generate the key for the deploy, go to menubar.
Before the deploy, create the setup.py, using the tool in menubar.
Configure the file raspConf.xml for deployment.
When using tools raspdev in the menubar,keep the selection on the current project.


## Presentations:

 - RaspdevSDK: https://web.uniparthenope.it/raspdev/RaspdevSDKPresentation.pdf
 - RaspdevPlugin: https://web.uniparthenope.it/raspdev/raspdevPlugin.pdf
