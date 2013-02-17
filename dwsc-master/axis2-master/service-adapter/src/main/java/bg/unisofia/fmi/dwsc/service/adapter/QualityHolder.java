package bg.unisofia.fmi.dwsc.service.adapter;

import bg.unisofia.fmi.dwsc.qosmodel.api.ServiceOperationInvocation;

public class QualityHolder {
	private ServiceOperationInvocation invocation;
	
	public QualityHolder(ServiceOperationInvocation invocation){
		this.invocation = invocation;
	}
	
}
