package raspdev.wizards;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "raspdev.wizards.messages"; //$NON-NLS-1$
	public static String RaspWizard_0;
	public static String RaspWizard_1;
	public static String RaspWizard_2;
	public static String RaspWizard_3;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
