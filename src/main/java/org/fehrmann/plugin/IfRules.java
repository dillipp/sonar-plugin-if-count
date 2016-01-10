package org.fehrmann.plugin;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.rule.Severity;

public class IfRules implements RulesDefinition {

	public void define(Context context) {
		NewRepository repo = context.createRepository("ifRules", "java").setName("Rules for Ifs");

		NewRule rule = repo.createRule("IfError")
				.setName("Error on If")
				.setHtmlDescription("Creates an issue on every if.")
				.setSeverity(Severity.BLOCKER);
		rule.setDebtRemediationFunction(rule.debtRemediationFunctions().linearWithOffset("10min", "15min"));
		rule.setDebtSubCharacteristic(RulesDefinition.SubCharacteristics.READABILITY);
		rule.setTags("style");
		repo.done();
	}

}
