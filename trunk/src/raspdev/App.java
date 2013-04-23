package raspdev;

import java.io.IOException;


import com.jcraft.jsch.JSchException;

import deploy.ScpTo;

public class App {
	public static void main(String[] args) throws IOException, JSchException {
    // create secure context

     String filename = "fibonacci.py";//System.console().readPassword().toString());
     ScpTo.scpCli("/home/sprawl/workspace1/RaspDev/src/dir/"+filename, "pi@192.168.1.100:"+"/home/pi/");
  
	}

}
