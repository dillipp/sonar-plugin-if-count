package org.fehrmann.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;


public class IfPlugin extends SonarPlugin {

	@SuppressWarnings("unchecked")
	@Override
	public List<Class<?>> getExtensions() {
		List<Class<?>> extensions = new ArrayList<Class<?>>();
		extensions.addAll(Arrays.asList(IfSensor.class, IfMetrics.class, IfRules.class, IfMeasureComputer.class));
	    return extensions;
	}

}
