package datagenerator;

import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

import bg.unisofia.fmi.dwsc.yani.model.qos.RawQos;

public class MockGenerator {
	
	public static List<RawQos> getListOfRawQosData() {
		List<RawQos> rawQosList = new LinkedList<RawQos>();

		RawQos rawQos1 = new RawQos(true, new Time(1000), new Time(2000), 20,
				30);
		rawQosList.add(rawQos1);

		RawQos rawQos2 = new RawQos(true, new Time(2000), new Time(3000), 30,
				40);
		rawQosList.add(rawQos2);

		RawQos rawQos3 = new RawQos(true, new Time(3500), new Time(4500), 40,
				50);
		rawQosList.add(rawQos3);

		return rawQosList;
	}
}
