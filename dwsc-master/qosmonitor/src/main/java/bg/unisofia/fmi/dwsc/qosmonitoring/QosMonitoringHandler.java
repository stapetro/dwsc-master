package bg.unisofia.fmi.dwsc.qosmonitoring;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.OperationContext;
import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.handlers.AbstractHandler;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

public class QosMonitoringHandler extends AbstractHandler {

	private final Logger logger;

	public QosMonitoringHandler() {
		super();
		logger = Logger.getLogger(this.getClass());
	}

	@Override
	public InvocationResponse invoke(MessageContext msgContext)
			throws AxisFault {
		SOAPEnvelope envelope = msgContext.getEnvelope();
		XMLStreamReader xmlReader = envelope.getXMLStreamReader();
		int bytes = 0;
		try {
			while (xmlReader.hasNext()) {
				try {
					bytes += xmlReader.getTextLength();
				} catch(IllegalStateException ex) {
					
				}
				xmlReader.next();
			}
		} catch (XMLStreamException e) {
			logger.error("Error reading SOAP msg", e);
			throw new AxisFault("Error reading SOAP msg");
		}
		ServiceContext srvContext = msgContext.getServiceContext();
		OperationContext opContext = msgContext.getOperationContext();
		logger.info("Soap msg size '" + bytes + "' for srv '"
				+ srvContext.getName() + "' opName '"
				+ opContext.getOperationName() + "'");
		return InvocationResponse.CONTINUE;
	}

}
