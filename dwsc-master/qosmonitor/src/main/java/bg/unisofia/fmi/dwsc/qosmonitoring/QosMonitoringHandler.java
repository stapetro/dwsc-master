package bg.unisofia.fmi.dwsc.qosmonitoring;

import java.sql.Timestamp;
import java.util.Calendar;

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
		String envContent =  envelope.toString();
		byte[] envBytes = envContent.getBytes();
		bytes = envBytes.length;
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
