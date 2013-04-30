package deploy;

import java.io.File;
import java.io.IOException;
import java.util.List;
//import org.apache.commons.io.FileUtils;
import com.jcraft.jsch.JSchException;
import deploy.gzip.TarAndGzip;

public class Jscp {

	public static void exec(SecureContext pContext, String pSrcDir,
			String pRemotePath, List<String> pIgnores) throws IOException,
			JSchException {


		String filename = new File(pSrcDir).getName() + ".tar.gz";
		TarAndGzip.folder(new File(pSrcDir), pIgnores);

		Scp.exec(pContext, pSrcDir + "/../" + filename, pRemotePath + "/"
				+ filename);

		Exec.exec(pContext, "cd " + pRemotePath + "; tar zxvf " + filename
				+ "; rm " + filename);

		//FileUtils.deleteDirectory(new File(pSrcDir + "/../" + filename));
	}

}
