package bg.unisofia.fmi.dwsc.service.adapter;

import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.omg.PortableInterceptor.SUCCESSFUL;

import bg.unisofia.fmi.dwsc.qosmodel.api.ServiceOperation;
import bg.unisofia.fmi.dwsc.qosmodel.api.ServiceOperation.OperationInvocations;
import bg.unisofia.fmi.dwsc.qosmodel.api.ServiceOperationInvocation;
import bg.unisofia.fmi.dwsc.qosmodel.api.ServiceQoSData;
import bg.unisofia.fmi.dwsc.qosmodel.api.ServiceQoSData.Operations;
import bg.unisofia.fmi.dwsc.yani.model.WebService;
import bg.unisofia.fmi.dwsc.yani.model.qos.IQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.QualityAttributeEnum;
import bg.unisofia.fmi.dwsc.yani.model.qos.RawQos;

public class ServiceTransformer {

	public List<WebService> getWebServiceList(List<ServiceQoSData> qosDataList) {
		List<WebService> wsList = new LinkedList<WebService>();
		for (ServiceQoSData qosData : qosDataList) {
//			System.out.println("=======" + qosData.getServiceEndpointUrl());
			String category = qosData.getServiceCategory();
			String endpoint = qosData.getServiceEndpointUrl();

			Operations operations = qosData.getOperations();

			List<ServiceOperation> serviceOperationList = operations
					.getOperation();

			for (ServiceOperation serviceOperation : serviceOperationList) {
				OperationInvocations operationInvocations = serviceOperation
						.getOperationInvocations();
				List<ServiceOperationInvocation> serviceOperationInvocationList = operationInvocations
						.getOperationInvocation();

				List<RawQos> rawQosList = getRawQosList(serviceOperationInvocationList);
				WebServiceUtils wsUtils = new WebServiceUtils(rawQosList);

				Map<QualityAttributeEnum, IQualityAttribute> qualityMap = wsUtils
						.getQualityMap();
				Map<QualityAttributeEnum, IQualityAttribute> worstQualityMap = wsUtils
						.getWorstQualityMap();
				Map<QualityAttributeEnum, IQualityAttribute> bestQualityMap = wsUtils
						.getBestQualityMap();

				WebService webService = new WebService(endpoint, category,
						qualityMap, worstQualityMap, bestQualityMap);
				wsList.add(webService);
			}
		}

		return wsList;
	}

	private List<RawQos> getRawQosList(
			List<ServiceOperationInvocation> serviceOperationInvocationList) {
		List<RawQos> rawQosList = new LinkedList<RawQos>();
		for (ServiceOperationInvocation invocation : serviceOperationInvocationList) {

			boolean successful = invocation.isSuccessful();
			long requestMessageSize = 0;
			long requestResponseSize = 0;

			Time requestReceived = null;
			Time requestSent = null;

			if (successful == true) {
				long receivedTimeMilis = invocation.getRequestReceived()
						.toGregorianCalendar().getTimeInMillis();
				requestReceived = new Time(receivedTimeMilis);

				long sentTimeMilis = invocation.getResponseSent()
						.toGregorianCalendar().getTimeInMillis();
				requestSent = new Time(sentTimeMilis);

				requestMessageSize = invocation.getRequestMsgSize();

				requestResponseSize = invocation.getResponseMsgSize();
			}

			RawQos rawQos = new RawQos(successful, requestReceived,
					requestSent, requestMessageSize, requestResponseSize);

			rawQosList.add(rawQos);
		}

		return rawQosList;
	}
}
