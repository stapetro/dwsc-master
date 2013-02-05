package bg.unisofia.fmi.dwsc.qosmodel.api;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.ws.handler.MessageContext;

/**
 * Stores non-functional metrics for an exchanged service message.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageQoSData")
public class MessageQoSData {

	public static final int REQUEST_MESSAGE = 1;

	public static final int RESPONSE_MESSAGE = 2;

	/**
	 * Stores timestamp when message is processed.
	 */
	@XmlElement(name = "processed")
	private Date processed;

	/**
	 * Stores message size in bytes.
	 */
	@XmlElement(name = "size")
	private int size;

	@XmlElement(name = "successful")
	private boolean successful;

	/**
	 * See {@link MessageContext}
	 */
	@XmlElement(name = "flow")
	private int flow;

	@XmlElement(name = "serviceName")
	private String serviceName;

	@XmlElement(name = "operationName")
	private String operationName;

	public MessageQoSData() {

	}

	public Date getProcessed() {
		return processed;
	}

	public void setProcessed(Date processed) {
		this.processed = processed;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
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
