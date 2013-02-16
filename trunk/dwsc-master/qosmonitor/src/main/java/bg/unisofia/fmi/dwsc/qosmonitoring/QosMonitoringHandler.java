package bg.unisofia.fmi.dwsc.qosmonitoring;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.OperationContext;
import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.handlers.AbstractHandler;
import org.apache.log4j.Logger;

import bg.unisofia.fmi.dwsc.qosmodel.api.MessageQoSData;
import bg.unisofia.fmi.dwsc.qosmodel.api.QoSDataCollectingServiceImplServiceCallbackHandler;
import bg.unisofia.fmi.dwsc.qosmodel.api.QoSDataCollectingServiceImplServiceStub;
import bg.unisofia.fmi.dwsc.qosmodel.api.Save;

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
		// envelope.get
		int bytes = 0;
		String envContent = envelope.toString();
		byte[] envBytes = envContent.getBytes();
		bytes = envBytes.length;
		int flow = msgContext.getFLOW();
		EndpointReference from = msgContext.getFrom();
		String fromAddr = (from != null) ? from.getAddress() : "UNKNOWN";
		ServiceContext srvContext = msgContext.getServiceContext();
		OperationContext opContext = msgContext.getOperationContext();
		Calendar cal = Calendar.getInstance();
		Timestamp nowTS = new Timestamp(cal.getTimeInMillis());
		String opCorrId = opContext.getLogCorrelationIDString();
		String msgCorrId = msgContext.getLogCorrelationID();
		logger.info(String
				.format("%s SOAP msg size '%d' bytes for srv '%s', operation '%s' from '%s', timestamp '%s', "
						+ "corr ids: {'%s', '%s'}",
						((flow == MessageContext.IN_FLOW) ? "Request"
								: "Response"), bytes, srvContext.getName(),
						opContext.getOperationName(), fromAddr, nowTS,
						opCorrId, msgCorrId));
		save(srvContext.getName(), opContext.getOperationName(), opCorrId,
				flow, nowTS.getTime(), bytes);
		return InvocationResponse.CONTINUE;
	}

	private void save(String serviceName, String operationName,
			String opCorrId, int flow, long timeInMilis, long size) {
		GregorianCalendar gregorianCal = new GregorianCalendar();
		gregorianCal.setTimeInMillis(timeInMilis);
		DatatypeFactory dataTypeFactory = null;
		try {
			dataTypeFactory = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e2) {
			logger.error(e2);
		}
		if (dataTypeFactory != null) {
			XMLGregorianCalendar xmlGregorianCal = dataTypeFactory
					.newXMLGregorianCalendar(gregorianCal);
			MessageQoSData msgQoSData = new MessageQoSData();
			msgQoSData.setFlow(flow);
			msgQoSData.setProcessed(xmlGregorianCal);
			msgQoSData.setProcessedNanoTime(System.nanoTime());
			msgQoSData.setServiceName(serviceName);
			msgQoSData.setOperationName(operationName);
			msgQoSData.setOperationCorrelationId(opCorrId);
			msgQoSData.setSize(size);
			QoSDataCollectingServiceImplServiceStub stub = null;
			try {
				stub = new QoSDataCollectingServiceImplServiceStub();
			} catch (AxisFault e1) {
				logger.error(e1);
			}
			if (stub != null) {
				Save save = new Save();
				save.getArg0().add(msgQoSData);
				QoSDataCollectingServiceImplServiceCallbackHandler callback = new QoSDataCollectingServiceImplServiceCallbackHandler() {
					@Override
					public void receiveResultsave(
							bg.unisofia.fmi.dwsc.qosmodel.api.SaveResponse result) {
						logger.info("Save operation: "
								+ (result.isReturn() ? "OK" : "FAILED"));
					}

					@Override
					public void receiveErrorsave(java.lang.Exception e) {
						logger.error(
								"QoSDataCollectingService returned exception: ",
								e);
					}
				};
				try {
					stub.startsave(save, callback);
				} catch (RemoteException e) {
					logger.error(e);
				}
			}
		}
	}
}
