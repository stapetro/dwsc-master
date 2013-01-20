package bg.unisofia.fmi.dwsc.bpel.extension;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.extension.ExtensionOperation;
import org.apache.ode.bpel.runtime.extension.ExtensionContext;
import org.apache.ode.utils.DOMUtils;
import org.w3c.dom.Element;

import bg.unisofia.fmi.dwsc.bpel.extension.util.ProcessUtils;
import bg.unisofia.fmi.dwsc.bpel.extension.util.QoSUtils;
import bg.unisofia.fmi.dwsc.yani.model.QualityProfile;

/**
 * This is the Dynamic Web Service Composer providing the extension operation
 * in BPEL
 * 
 */
public class YaniWsComposerOperation implements ExtensionOperation {

	private final Log log = LogFactory.getLog(getClass());

	/**
	 * Extension operation that analyses the BPEL model and updates the endpoint
	 * references based on the requested QoS metrics
	 */
	@Override
	public void run(Object obj, String channelID, Element element)
			throws FaultException {
		log.info("Yani starts analysing the current process model for service composition...");
		
		QoSUtils qosUtil = new QoSUtils();
		QualityProfile qualityProfile = qosUtil.getQualityProfile(element);
		
		log.info("Activity Element is : \n" + DOMUtils.domToString(element));

		ExtensionContext context = (ExtensionContext) obj;

		ProcessUtils processUtils = new ProcessUtils();
		processUtils.getQoSPartnerLinksDefinition(context);
		
		context.complete(channelID);

	}

}
