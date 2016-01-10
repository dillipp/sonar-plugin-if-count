package org.fehrmann.plugin;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

public class IfMetrics implements Metrics {
	public static final Metric<Integer> IFMETRIC = new Metric.Builder("ifmetric", "Number of If's", Metric.ValueType.INT)
			.setDescription("Hier steht ein if").setDirection(Metric.DIRECTION_WORST)
			.setQualitative(false).setDomain(CoreMetrics.DOMAIN_GENERAL).create();

	@SuppressWarnings("rawtypes")
	public List<Metric> getMetrics() {
		return Arrays.<Metric>asList(IFMETRIC);
	}

}
