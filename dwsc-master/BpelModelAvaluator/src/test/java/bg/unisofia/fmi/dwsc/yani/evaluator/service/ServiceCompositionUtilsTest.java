package bg.unisofia.fmi.dwsc.yani.evaluator.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import bg.unisofia.fmi.dwsc.yani.evaluator.CompositionAnalyser;
import bg.unisofia.fmi.dwsc.yani.evaluator.ServiceCompositionUtils;
import bg.unisofia.fmi.dwsc.yani.evaluator.service.util.MockGenerator;
import bg.unisofia.fmi.dwsc.yani.model.PartnerLinkDefinition;
import bg.unisofia.fmi.dwsc.yani.model.WebService;
import bg.unisofia.fmi.dwsc.yani.model.qos.IQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.QualityAttributeEnum;

public class ServiceCompositionUtilsTest {

	// private static final String EXTECTED_ENDPOINTS_IN_GENERATED_MARIX = new
	// String{{"additionEndpoint1", "additionEndpoint1"}}

	private static MockGenerator mock;

	@BeforeClass
	public static void init() {
		mock = new MockGenerator();
	}

	@Test
	public void testGetMatrixSize() {
		ServiceCompositionUtils analyser = new ServiceCompositionUtils();
		List<PartnerLinkDefinition> plDefinitionList = getPlDefinitionList();

		int[] matrixSize = analyser.getMatrixSize(plDefinitionList);

		Assert.assertEquals(2, matrixSize.length);
		Assert.assertEquals(4, matrixSize[0]);
		Assert.assertEquals(2, matrixSize[1]);

	}

	@Test
	public void testUpdateMatrixFirstColumnRepeatInterval6() {
		WebService ws1 = new WebService("endpoint1", "addition", null, null,
				null);
		WebService ws2 = new WebService("endpoint2", "addition", null, null,
				null);
		List<WebService> wsList = new LinkedList<WebService>();
		wsList.add(ws1);
		wsList.add(ws2);

		int columnNumber = 0;
		int repeatInterval = 6;

		WebService[][] wsMatrix = new WebService[12][3];
		ServiceCompositionUtils analyser = new ServiceCompositionUtils();
		analyser.updateMatrix(wsList, wsMatrix, columnNumber, repeatInterval);

		for (int i = 0; i < 6; i++) {
			String endpoint = wsMatrix[i][columnNumber].getEndPoint();
			Assert.assertEquals("endpoint1", endpoint);
		}

		for (int i = 6; i < 11; i++) {
			String endpoint = wsMatrix[i][columnNumber].getEndPoint();
			Assert.assertEquals("endpoint2", endpoint);
		}
	}

	@Test
	public void testGetServiceCompositionMatrixSize() {
		List<PartnerLinkDefinition> plDefinitionList = getPlDefinitionList();
		ServiceCompositionUtils analyser = new ServiceCompositionUtils();
		WebService[][] webServiceCompositionMatrix = analyser
				.getServiceCompositionMatrix(plDefinitionList);

		Assert.assertEquals(webServiceCompositionMatrix.length, 4);
		Assert.assertEquals(webServiceCompositionMatrix[0].length, 2);

		// for (int i = 0; i < webServiceCompositionMatrix.length; i++) {
		// for (int j = 0; j < webServiceCompositionMatrix[i].length; j++) {
		// System.out.print(webServiceCompositionMatrix[i][j]
		// .getEndPoint());
		// System.out.print("\t");
		// }
		// System.out.println();
		// }
	}

	@Test
	public void testGetServiceCompositionMatrixContentByEndpoints() {
		List<PartnerLinkDefinition> plDefinitionList = mock
				.getPlDefinitionList();
		ServiceCompositionUtils analyser = new ServiceCompositionUtils();
		WebService[][] webServiceCompositionMatrix = analyser
				.getServiceCompositionMatrix(plDefinitionList);

		Assert.assertEquals("addition_endpoint_1",
				webServiceCompositionMatrix[0][0].getEndPoint());

		Assert.assertEquals("pow_endpoint_1",
				webServiceCompositionMatrix[0][1].getEndPoint());

		Assert.assertEquals("addition_endpoint_1",
				webServiceCompositionMatrix[1][0].getEndPoint());

		Assert.assertEquals("pow_endpoint_2",
				webServiceCompositionMatrix[1][1].getEndPoint());

		Assert.assertEquals("addition_endpoint_2",
				webServiceCompositionMatrix[2][0].getEndPoint());

		Assert.assertEquals("pow_endpoint_1",
				webServiceCompositionMatrix[2][1].getEndPoint());

		Assert.assertEquals("addition_endpoint_2",
				webServiceCompositionMatrix[3][0].getEndPoint());

		Assert.assertEquals("pow_endpoint_2",
				webServiceCompositionMatrix[3][1].getEndPoint());
	}

	@Test
	public void testGetServiceAggregatedQuality() {
		ServiceCompositionUtils serviceUtils = new ServiceCompositionUtils();
		List<PartnerLinkDefinition> plDefinitionList = mock
				.getPlDefinitionList();

		WebService[][] serviceMatirx = serviceUtils
				.getServiceCompositionMatrix(plDefinitionList);

		List<Map<QualityAttributeEnum, IQualityAttribute>> aggregatedQos = serviceUtils
				.getServiceAggregatedQuality(serviceMatirx);

		Assert.assertEquals(1.0,
				aggregatedQos.get(0).get(QualityAttributeEnum.AVAILABILITY)
						.getQosAsDouble());
		Assert.assertEquals(0.3,
				aggregatedQos.get(0).get(QualityAttributeEnum.EXECUTION_TIME)
						.getQosAsDouble());
		Assert.assertEquals(110.0,
				aggregatedQos.get(0).get(QualityAttributeEnum.THROUGHPUT)
						.getQosAsDouble());

		Assert.assertEquals(0.9,
				aggregatedQos.get(1).get(QualityAttributeEnum.AVAILABILITY)
						.getQosAsDouble());
		Assert.assertEquals(1.1,
				aggregatedQos.get(1).get(QualityAttributeEnum.EXECUTION_TIME)
						.getQosAsDouble());
		Assert.assertEquals(40.0,
				aggregatedQos.get(1).get(QualityAttributeEnum.THROUGHPUT)
						.getQosAsDouble());

		Assert.assertEquals(0.9,
				aggregatedQos.get(2).get(QualityAttributeEnum.AVAILABILITY)
						.getQosAsDouble());
		Assert.assertEquals(1.0,
				aggregatedQos.get(2).get(QualityAttributeEnum.EXECUTION_TIME)
						.getQosAsDouble());
		Assert.assertEquals(20.0,
				aggregatedQos.get(2).get(QualityAttributeEnum.THROUGHPUT)
						.getQosAsDouble());

		Assert.assertEquals(0.81,
				aggregatedQos.get(3).get(QualityAttributeEnum.AVAILABILITY)
						.getQosAsDouble());
		Assert.assertEquals(1.8,
				aggregatedQos.get(3).get(QualityAttributeEnum.EXECUTION_TIME)
						.getQosAsDouble());
		Assert.assertEquals(20.0,
				aggregatedQos.get(3).get(QualityAttributeEnum.THROUGHPUT)
						.getQosAsDouble());
	}

	@Test
	public void testGetServiceAggregatedWorstQuality() {
		ServiceCompositionUtils serviceUtils = new ServiceCompositionUtils();
		List<PartnerLinkDefinition> plDefinitionList = mock
				.getPlDefinitionList();

		WebService[][] serviceMatirx = serviceUtils
				.getServiceCompositionMatrix(plDefinitionList);

		List<Map<QualityAttributeEnum, IQualityAttribute>> aggregatedQos = serviceUtils
				.getServiceAggregatedQualityForWorstValues(serviceMatirx);

		Assert.assertEquals(0.3,
				aggregatedQos.get(0).get(QualityAttributeEnum.AVAILABILITY)
						.getQosAsDouble());
		Assert.assertEquals(1.4,
				aggregatedQos.get(0).get(QualityAttributeEnum.EXECUTION_TIME)
						.getQosAsDouble());
		Assert.assertEquals(44.0,
				aggregatedQos.get(0).get(QualityAttributeEnum.THROUGHPUT)
						.getQosAsDouble());

		Assert.assertEquals(0.18,
				aggregatedQos.get(1).get(QualityAttributeEnum.AVAILABILITY)
						.getQosAsDouble());
		Assert.assertEquals(2.4,
				aggregatedQos.get(1).get(QualityAttributeEnum.EXECUTION_TIME)
						.getQosAsDouble());
		Assert.assertEquals(10.0,
				aggregatedQos.get(1).get(QualityAttributeEnum.THROUGHPUT)
						.getQosAsDouble());

		Assert.assertEquals(0.2,
				aggregatedQos.get(2).get(QualityAttributeEnum.AVAILABILITY)
						.getQosAsDouble());
		Assert.assertEquals(2.8,
				aggregatedQos.get(2).get(QualityAttributeEnum.EXECUTION_TIME)
						.getQosAsDouble());
		Assert.assertEquals(5.0,
				aggregatedQos.get(2).get(QualityAttributeEnum.THROUGHPUT)
						.getQosAsDouble());

		Assert.assertEquals(0.12,
				aggregatedQos.get(3).get(QualityAttributeEnum.AVAILABILITY)
						.getQosAsDouble());
		Assert.assertEquals(3.8,
				aggregatedQos.get(3).get(QualityAttributeEnum.EXECUTION_TIME)
						.getQosAsDouble());
		Assert.assertEquals(5.0,
				aggregatedQos.get(3).get(QualityAttributeEnum.THROUGHPUT)
						.getQosAsDouble());
	}

	private List<PartnerLinkDefinition> getPlDefinitionList() {
		List<PartnerLinkDefinition> plDefinitionList = new LinkedList<PartnerLinkDefinition>();

		PartnerLinkDefinition plDefinition1 = new PartnerLinkDefinition(
				"TestPL1", "addition", "endpoint1", null);

		WebService wsAddition1 = new WebService("additionEndpoint1",
				"addition", null, null, null);
		WebService wsAddition2 = new WebService("additionEndpoint2",
				"addition", null, null, null);
		List<WebService> additionWsList = new LinkedList<WebService>();
		additionWsList.add(wsAddition1);
		additionWsList.add(wsAddition2);
		plDefinition1.setAssiciatedServiceList(additionWsList);
		plDefinitionList.add(plDefinition1);

		// second partnerlink with services
		PartnerLinkDefinition plDefinition2 = new PartnerLinkDefinition(
				"TestPL2", "pow", "endpoint2", null);
		WebService wsPow1 = new WebService("powEndpoint1", "addition", null,
				null, null);
		WebService wsPow2 = new WebService("powEndpoint2", "addition", null,
				null, null);
		List<WebService> powWsList = new LinkedList<WebService>();
		powWsList.add(wsPow1);
		powWsList.add(wsPow2);
		plDefinition2.setAssiciatedServiceList(powWsList);
		plDefinitionList.add(plDefinition2);

		return plDefinitionList;
	}
}
