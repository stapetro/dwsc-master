package bg.unisofia.fmi.dwsc.qosmodel.api;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Stores non-functional metrics for an exchanged service message.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageQoSData")
public class MessageQoSData {

	/**
	 * Stores timestamp when message is processed.
	 */
	@XmlElement(name = "processed")
	private Date processed;
	
	@XmlElement(name = "processedNanoTime")
	private long processedNanoTime;

	/**
	 * Stores message size in bytes.
	 */
	@XmlElement(name = "size")
	private long size;


	@XmlElement(name = "flow")
	private int flow;

	@XmlElement(name = "serviceName")
	private String serviceName;

	@XmlElement(name = "operationName")
	private String operationName;

	@XmlElement(name = "operationCorrelationId")
	private String operationCorrelationId;

	public MessageQoSData() {

	}

	public String getOperationCorrelationId() {
		return operationCorrelationId;
	}

	public void setOperationCorrelationId(String operationCorrelationId) {
		this.operationCorrelationId = operationCorrelationId;
	}

	public Date getProcessed() {
		return processed;
	}

	public void setProcessed(Date processed) {
		this.processed = processed;
	}
	
	public long getProcessedNanoTime() {
		return processedNanoTime;
	}

	public void setProcessedNanoTime(long processedNanoTime) {
		this.processedNanoTime = processedNanoTime;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

}
