package bg.unisofia.fmi.dwsc.bpel.extension;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.extension.ExtensionOperation;
import org.apache.ode.bpel.runtime.extension.ExtensionContext;
import org.apache.ode.utils.DOMUtils;
import org.w3c.dom.Element;

/**
 * This is the Dynamic Web Service Compositor providing the extension operation
 * in BPEL
 * 
 */
public class YaniWsComposerOperation implements ExtensionOperation {
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * Extension operation that analyses the BPEL model and updates the endpoint
	 * references based on the requested QoS metrics
	 */
	@Override
	public void run(Object obj, String channelID, Element element)
			throws FaultException {
		log.info("executing LOG ACTIVITY -------------");
		log.info("argument => " + channelID);
		log.info("Activity Element is : \n" + DOMUtils.domToString(element));
		log.info("Parent -> " + DOMUtils.domToString(element.getParentNode()));

		ExtensionContext context = (ExtensionContext) obj;

		context.complete(channelID);

	}

}
