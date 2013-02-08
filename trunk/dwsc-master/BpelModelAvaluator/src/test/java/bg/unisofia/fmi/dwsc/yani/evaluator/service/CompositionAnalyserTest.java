package bg.unisofia.fmi.dwsc.yani.evaluator.service;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import bg.unisofia.fmi.dwsc.yani.evaluator.CompositionAnalyser;
import bg.unisofia.fmi.dwsc.yani.evaluator.service.util.MockGenerator;
import bg.unisofia.fmi.dwsc.yani.model.PartnerLinkDefinition;
import bg.unisofia.fmi.dwsc.yani.model.QualityProfile;
import bg.unisofia.fmi.dwsc.yani.model.WebService;

public class CompositionAnalyserTest {

	@Test
	public void testGetBestCompositionByQuality() {
		MockGenerator mockGenerator = new MockGenerator();
		List<PartnerLinkDefinition> plDefinitionList = mockGenerator
				.getPlDefinitionList();
		QualityProfile profile = mockGenerator.getQualityProfile();

		CompositionAnalyser analyser = new CompositionAnalyser();
		List<WebService> wsList = analyser.getBestCompositionByQuality(
				plDefinitionList, profile);


		Assert.assertEquals("addition_endpoint_1", wsList.get(0).getEndPoint());
		Assert.assertEquals("pow_endpoint_1", wsList.get(1).getEndPoint());
	}
}
