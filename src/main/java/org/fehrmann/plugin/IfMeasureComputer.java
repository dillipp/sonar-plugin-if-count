package org.fehrmann.plugin;

import org.sonar.api.ce.measure.Component;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

public class IfMeasureComputer implements MeasureComputer {

	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {
		return defContext.newDefinitionBuilder().setOutputMetrics(IfMetrics.IFMETRIC.getKey()).build();
	}

	public void compute(MeasureComputerContext context) {
		// This method is executed on the whole tree of components.
		// Bottom-up traversal : files -> directories -> modules -> project

		int value = 0;
		if (context.getComponent().getType() != Component.Type.FILE) {
			// directory, module or project: sum values of children
						value = 0;
						for (Measure childMeasure : context.getChildrenMeasures(IfMetrics.IFMETRIC.getKey())) {
							value += childMeasure.getIntValue();
						}
						context.addMeasure(IfMetrics.IFMETRIC.getKey(), value);
		} else {
			// Measures should already existst for files
		}
		

	}

}
