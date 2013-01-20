package bg.unisofia.fmi.dwsc.qosmonitoring;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.description.AxisDescription;
import org.apache.axis2.description.AxisModule;
import org.apache.axis2.modules.Module;
import org.apache.neethi.Assertion;
import org.apache.neethi.Policy;

public class QoSMontirongModule implements Module {

	@Override
	public void init(ConfigurationContext configContext, AxisModule module)
			throws AxisFault {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void engageNotify(AxisDescription axisDescription) throws AxisFault {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canSupportAssertion(Assertion assertion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void applyPolicy(Policy policy, AxisDescription axisDescription)
			throws AxisFault {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdown(ConfigurationContext configurationContext)
			throws AxisFault {
		// TODO Auto-generated method stub
		
	}

}
