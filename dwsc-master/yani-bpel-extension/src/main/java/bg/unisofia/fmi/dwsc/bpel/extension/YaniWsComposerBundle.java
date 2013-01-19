package bg.unisofia.fmi.dwsc.bpel.extension;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ode.bpel.runtime.extension.AbstractExtensionBundle;

/**
 * The bundle implementation of the YaniWsComposerOperation class.
 * 
 * @author krasimir.baylov
 * 
 */
public class YaniWsComposerBundle extends AbstractExtensionBundle {

	/**
	 * The namespace for the current BPEL extension operation
	 */
	public static final String NAMESPACE = "http://yaniwscomposer.com/YaniWsComposer";

	/**
	 * Name of the extension operation
	 */
	private static final String EXTENSION_ACTIVITY_LOCAL_NAME = "ComposeQoS";

	private final Log log = LogFactory.getLog(getClass());

	@Override
	public String getNamespaceURI() {
		return NAMESPACE;
	}

	@Override
	public void registerExtensionActivities() {
		log.info("Registering YaniWsComposerBundle");
		registerExtensionOperation(EXTENSION_ACTIVITY_LOCAL_NAME,
				YaniWsComposerOperation.class);
	}

}
