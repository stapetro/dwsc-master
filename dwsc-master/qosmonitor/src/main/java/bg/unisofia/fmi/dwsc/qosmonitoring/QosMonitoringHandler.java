package bg.unisofia.fmi.dwsc.qosmonitoring;

import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.OperationContext;
import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.handlers.AbstractHandler;
import org.apache.log4j.Logger;

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
		int bytes = 0;
		XMLStreamReader xmlReader = envelope.getXMLStreamReader();
		if (xmlReader != null) {
//			StringBuilder sb = new StringBuilder();
			try {
				while (xmlReader.hasNext()) {
					int event = xmlReader.next();
					if (event == XMLStreamReader.START_DOCUMENT
							|| event == XMLStreamReader.START_ELEMENT) {
						logger.info("XMLReader is in state "
								+ (event == XMLStreamReader.START_ELEMENT ? "XMLStreamReader.START_ELEMENT"
										: "XMLStreamReader.START_DOCUMENT"));
						break;
					}
				}
				// logger.info(sb.toString());
			} catch (XMLStreamException e) {
				logger.error(e);
			}
			TransformerFactory tf = TransformerFactory.newInstance();
			try {
				Transformer t = tf.newTransformer();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				StreamResult sResult = new StreamResult(outputStream);
				StAXSource staxSource = new StAXSource(xmlReader);
				try {
					t.transform(staxSource, sResult);
					bytes = outputStream.size();
				} catch (TransformerException e) {
					logger.error("Error transforming", e);
				}
			} catch (TransformerConfigurationException e1) {
				logger.error("Error transforming", e1);
			}
		}
		int flow = msgContext.getFLOW();
		EndpointReference from = msgContext.getFrom();
		String fromAddr = (from != null) ? from.getAddress() : "UNKNOWN";
		ServiceContext srvContext = msgContext.getServiceContext();
		OperationContext opContext = msgContext.getOperationContext();
		Calendar cal = Calendar.getInstance();
		Timestamp nowTS = new Timestamp(cal.getTimeInMillis());
		logger.info(String
				.format("%s SOAP msg size '%d' bytes for srv '%s', operation '%s' from '%s', timestamp '%s'",
						((flow == MessageContext.IN_FLOW) ? "Request"
								: "Response"), bytes, srvContext.getName(),
						opContext.getOperationName(), fromAddr, nowTS));
		return InvocationResponse.CONTINUE;
	}

}
