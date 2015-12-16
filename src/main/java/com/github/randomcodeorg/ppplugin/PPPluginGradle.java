package com.github.randomcodeorg.ppplugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class PPPluginGradle implements Plugin<Project> {

	public PPPluginGradle() {
		
	}

	@Override
	public void apply(Project project) {
		project.getTasks().create("postProcess", PostProcessGradleTask.class);
	}

}
