package com.github.randomcodeorg.ppplugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.plugins.JavaPlugin;

/**
 * The plugin for Gradle.
 * 
 * @author Marcel Singer
 *
 */
public class PPPluginGradle implements Plugin<Project> {

	public PPPluginGradle() {

	}

	@Override
	public void apply(Project project) {
		if (project.getPlugins().hasPlugin("java")) {
			Task postProcess = project.getTasks().create("postProcess", PostProcessGradleTask.class);
			Task classTask = project.getTasks().getByName(JavaPlugin.CLASSES_TASK_NAME);
			classTask.dependsOn(postProcess);
		}
	}

}
