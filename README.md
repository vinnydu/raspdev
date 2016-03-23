## What is RaspDev?

RaspDev is a Raspberry SDK, framework and plugin for Eclipse, for Python software development.
 - QEMU integration
 - Template projects
 - Deploy to physical or virtual machine and many others
 - Release 1.6.0


## Requirements

 - Java 6 or higher
 - Python 2.6 or newer
 - Eclipse 4.2 or newer
 - Raspbian Image
 - Sshpass
 - Putty To generate the ssh key.(only for Windows)
 - URL for pre-configured Soft-float Debian Wheezy

Image 2013-05-29 with apps directory, WxPython, enable ssh, it keyboard layout.
https://dl.dropboxusercontent.com/s/ak3l8ibi18ltsot/2013-05-29-wheezy-armel.img?token_hash=AAGwV9ngXZnZB1NPwLS6GTzVgsxPctSYd5LqHQB-zx1zsg&dl=1

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

Every time you start eclipse, select the directory raspdevSDK on menubar: RaspDev, Select RaspdevSDK and give ok. <br />
The first time you use raspdevSDK with the plugin, you have to create the target with: RaspDev, Create Target. After selecting the sdk directory. <br />
When you create a device, generate the key for the deploy, go to menubar. <br />
Before the deploy, create the setup.py, using the tool in menubar. <br />
Configure the file raspConf.xml for deployment. <br />
When using tools raspdev in the menubar,keep the selection on the current project.


## Presentations:

 - RaspdevSDK: https://web.uniparthenope.it/raspdev/RaspdevSDKPresentation.pdf
 - RaspdevPlugin: https://web.uniparthenope.it/raspdev/raspdevPlugin.pdf


## About Raspdev

Raspdev has been supported by "Progetto Sebeto" of University of Naples Parthenope. <br />
Continues to be maintained by the same authors <br />
Sabino Parziale and Vincenzo Duraccio <br />
with open source license: EPL (Eclipse Public License) <br />
