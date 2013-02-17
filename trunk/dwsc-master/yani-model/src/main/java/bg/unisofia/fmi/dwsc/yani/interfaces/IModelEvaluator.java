package bg.unisofia.fmi.dwsc.yani.interfaces;

import java.util.List;

import bg.unisofia.fmi.dwsc.yani.model.PartnerLinkDefinition;
import bg.unisofia.fmi.dwsc.yani.model.QualityProfile;

/**
 * Interface that any modules implementing dynamic web service composition
 * should implement in order to be recognized by yani-bpel-extension
 * 
 * @author krasimir.baylov
 * 
 */
public interface IModelEvaluator {

	public void updateModelForComposition(
			List<PartnerLinkDefinition> plDefinitionList,
			QualityProfile qualityProfile) throws Exception;
}
