package org.fehrmann.plugin;

import org.sonar.api.ce.measure.Component;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.ce.measure.MeasureComputer;
import org.sonar.api.resources.Project;
import org.sonar.api.ce.measure.Measure;

public class IfSensor implements Sensor {
	private FileSystem fs;

	/**
	 * Use of IoC to get FileSystem
	 */
	public IfSensor(FileSystem fs) {
		this.fs = fs;
	}

	public boolean shouldExecuteOnProject(Project arg0) {
		return true;
	}

	public void analyse(Project project, SensorContext sensorContext) {
		for (InputFile inputFile : fs.inputFiles(fs.predicates().all())) {
			IfMeasurer.countIf(inputFile, sensorContext);
		}
	}

}
