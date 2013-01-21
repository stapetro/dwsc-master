package bg.unisofia.fmi.dwsc.qosmonitoring;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.OperationContext;
import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.handlers.AbstractHandler;
import org.apache.log4j.Logger;

public class FaultQosMonitoringHandler extends AbstractHandler {

	private final Logger logger;

	public FaultQosMonitoringHandler() {
		super();
		logger = Logger.getLogger(this.getClass());
	}

	@Override
	public InvocationResponse invoke(MessageContext msgContext)
			throws AxisFault {
		int flow = msgContext.getFLOW();
		if (flow == MessageContext.IN_FAULT_FLOW
				|| flow == MessageContext.OUT_FAULT_FLOW) {
			ServiceContext srvContext = msgContext.getServiceContext();
			OperationContext opContext = msgContext.getOperationContext();
			logger.info(String.format("[%d] flow for srv '%s', operation '%s'",
					flow, srvContext.getName(), opContext.getOperationName()));
		}
		return InvocationResponse.ABORT;
	}

}
