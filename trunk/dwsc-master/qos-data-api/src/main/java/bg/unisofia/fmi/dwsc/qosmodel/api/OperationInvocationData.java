package bg.unisofia.fmi.dwsc.qosmodel.api;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceOperationInvocation")
public class OperationInvocationData {

	@XmlElement(name="successful")
	private boolean successful;

	@XmlElement(name="requestReceived")
	private Date requestReceived;
	
	@XmlElement(name="requestReceivedNanoTime")
	private long requestReceivedNanoTime;

	@XmlElement(name="responseSent")
	private Date responseSent;
	
	@XmlElement(name="responseSentNanoTime")
	private long responseSentNanoTime;

	@XmlElement(name="requestMsgSize")
	private long requestMsgSize;

	@XmlElement(name="responseMsgSize")
	private long responseMsgSize;

	public OperationInvocationData() {

	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public Date getRequestReceived() {
		return requestReceived;
	}

	public void setRequestReceived(Date requestReceived) {
		this.requestReceived = requestReceived;
	}

	public Date getResponseSent() {
		return responseSent;
	}

	public void setResponseSent(Date responseSent) {
		this.responseSent = responseSent;
	}
	
	public long getRequestReceivedNanoTime() {
		return requestReceivedNanoTime;
	}

	public void setRequestReceivedNanoTime(long requestReceivedNanoTime) {
		this.requestReceivedNanoTime = requestReceivedNanoTime;
	}

	public long getResponseSentNanoTime() {
		return responseSentNanoTime;
	}

	public void setResponseSentNanoTime(long responseSentNanoTime) {
		this.responseSentNanoTime = responseSentNanoTime;
	}

	public long getRequestMsgSize() {
		return requestMsgSize;
	}

	public void setRequestMsgSize(long requestMsgSize) {
		this.requestMsgSize = requestMsgSize;
	}

	public long getResponseMsgSize() {
		return responseMsgSize;
	}

	public void setResponseMsgSize(long responseMsgSize) {
		this.responseMsgSize = responseMsgSize;
	}

	@Override
	public boolean equals(Object opInvocationDataObje) {
		if (opInvocationDataObje == null) {
			return false;
		}
		if (this == opInvocationDataObje) {
			return true;
		}
		if (!(opInvocationDataObje instanceof OperationInvocationData)) {
			return false;
		}
		OperationInvocationData opInvocationData = (OperationInvocationData) opInvocationDataObje;
		boolean requestDatesEqual = areObjectsEqual(this.requestReceived,
				opInvocationData.requestReceived);
		boolean responseDatesEqual = areObjectsEqual(this.responseSent,
				opInvocationData.responseSent);
		return ((this.successful == opInvocationData.successful)
				&& (this.requestMsgSize == opInvocationData.requestMsgSize)
				&& (this.responseMsgSize == opInvocationData.responseMsgSize)
				&& requestDatesEqual && responseDatesEqual);
	}

	@Override
	public int hashCode() {
		int successfulCode = (successful) ? 1 : 0;
		int requestReceivedCode = (requestReceived != null) ? ((int) requestReceived
				.getTime()) : 1;
		int responseSentCode = (responseSent != null) ? ((int) responseSent
				.getTime()) : 2;
		int datesCode = (requestReceivedCode & responseSentCode);
		return (successfulCode ^ datesCode
				^ ((int) requestMsgSize) ^ ((int) responseMsgSize));
	}

	private boolean areObjectsEqual(Object date, Object otherDate) {
		boolean dateEquality = false;
		if (date != null && otherDate != null) {
			dateEquality = date.equals(otherDate);
		} else if (date == null && otherDate == null) {
			dateEquality = true;
		} else {
			dateEquality = false;
		}
		return dateEquality;
	}

}
