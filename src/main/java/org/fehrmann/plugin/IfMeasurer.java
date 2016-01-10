package org.fehrmann.plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.internal.DefaultTextPointer;
import org.sonar.api.batch.fs.internal.DefaultTextRange;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.rule.RuleKey;

public class IfMeasurer {

	public IfMeasurer() {
		// nothing, only static methods
	}

	public static void countIf(InputFile inputFile, SensorContext sensorContext) {
		String[] cont = getFileAsArray(inputFile.file());
		String search = "if";

		int totalCount = 0;

		for (int i = 0; i < cont.length; i++) {
			int count = getCount(cont[i], search);
			if (count > 0) {
				NewIssue issue = sensorContext.newIssue().forRule(RuleKey.of("ifRules", "IfError"))
						.effortToFix((double) count);
				issue.at(issue.newLocation().on(inputFile)
						.at(new DefaultTextRange(new DefaultTextPointer(i + 1, 0),
								new DefaultTextPointer(i + 1, cont[i].length() - 1)))
						.message("Found " + count + " If's in this line."));
				issue.save();
			}

			totalCount += count;
		}
		sensorContext.saveMeasure(inputFile, IfMetrics.IFMETRIC, (double) totalCount);
	}

	private static int getCount(String haystack, String needle) {
		int count = 0;
		int pos = 0;

		while (pos < haystack.length() - needle.length() && count < 100) {
			pos = haystack.indexOf(needle, pos);

			if (pos < 0) {
				break;
			} else {
				pos += needle.length();
				count++;
			}
		}

		return count;
	}

	private static String[] getFileAsArray(File file) {
		String[] ret = null;

		FileReader fr = null;
		BufferedReader bf = null;

		try {
			fr = new FileReader(file);
			bf = new BufferedReader(fr);
			String fileCont = bf.readLine();
			while (bf.ready()) {
				fileCont += "\n" + bf.readLine();
			}
			bf.close();
			fr.close();

			ret = fileCont.split("\n");
		} catch (Exception e) {
			System.out.println("Error parsing File " + file.getAbsolutePath());
			e.printStackTrace();
		} finally {
			try {
				bf.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return ret;
	}
}
