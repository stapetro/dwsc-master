package bg.unisofia.fmi.dwsc.yani.model.qos;

import java.sql.Time;

public class RawQos {

	private boolean successful;
	private Time requestReceived;
	private Time requestSent;
	private long requestMessageSize;
	private long requestResponseSize;

	public RawQos(boolean successful, Time requestReceived, Time requestSent,
			long requestMessageSize, long requestResponseSize) {
		super();
		this.successful = successful;
		this.requestReceived = requestReceived;
		this.requestSent = requestSent;
		this.requestMessageSize = requestMessageSize;
		this.requestResponseSize = requestResponseSize;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public Time getRequestReceived() {
		return requestReceived;
	}

	public void setRequestReceived(Time requestReceived) {
		this.requestReceived = requestReceived;
	}

	public Time getRequestSent() {
		return requestSent;
	}

	public void setRequestSent(Time requestSent) {
		this.requestSent = requestSent;
	}

	public long getRequestMessageSize() {
		return requestMessageSize;
	}

	public void setRequestMessageSize(long requestMessageSize) {
		this.requestMessageSize = requestMessageSize;
	}

	public long getRequestResponseSize() {
		return requestResponseSize;
	}

	public void setRequestResponseSize(long requestResponseSize) {
		this.requestResponseSize = requestResponseSize;
	}
}
