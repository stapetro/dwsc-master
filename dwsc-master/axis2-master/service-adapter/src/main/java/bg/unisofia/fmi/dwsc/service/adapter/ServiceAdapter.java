package bg.unisofia.fmi.dwsc.service.adapter;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;

import bg.unisofia.fmi.dwsc.qosmodel.api.GetServiceQoSDataByCategory;
import bg.unisofia.fmi.dwsc.qosmodel.api.GetServiceQoSDataByCategoryResponse;
import bg.unisofia.fmi.dwsc.qosmodel.api.QoSDataProvidingServiceImplServiceStub;
import bg.unisofia.fmi.dwsc.qosmodel.api.ServiceQoSData;
import bg.unisofia.fmi.dwsc.yani.model.WebService;

public class ServiceAdapter {

	private QoSDataProvidingServiceImplServiceStub stub;

	public ServiceAdapter() throws Exception {
		stub = new QoSDataProvidingServiceImplServiceStub();
	}

	public Map<String, List<WebService>> getServices(List<String> categoryList)
			throws RemoteException {
		if (categoryList == null) {
			return null;
		}
		Map<String, List<WebService>> availableWebServices = new HashMap<String, List<WebService>>();

		ServiceTransformer transformer = new ServiceTransformer();
		for (String category : categoryList) {
//			System.out.println("========== " + category + " ============");

			GetServiceQoSDataByCategory serviceCategory = new GetServiceQoSDataByCategory();
			serviceCategory.setArg0(category);

			GetServiceQoSDataByCategoryResponse response = stub
					.getServiceQoSDataByCategory(serviceCategory);
			List<ServiceQoSData> webServices = response.getReturn();

			List<WebService> wsList = transformer
					.getWebServiceList(webServices);
			availableWebServices.put(category, wsList);
			
//			for(WebService ws : wsList){
//				System.out.println("--> " + ws.getEndPoint());
//				System.out.println("qos: " + ws.getQos());
//				//System.out.println("best: " + ws.getBestQos());
//				//System.out.println("worst: " + ws.getWorstQos());
//			}

		}

		return availableWebServices;

	}

//	public static void main(String[] args) throws RemoteException {
//		ServiceAdapter sa = new ServiceAdapter();
//		List<String> categories = new LinkedList<String>();
//		categories.add("addition");
//		sa.getServices(categories);
//	}

}
