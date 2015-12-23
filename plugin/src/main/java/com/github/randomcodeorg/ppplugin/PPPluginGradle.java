package com.github.randomcodeorg.ppplugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

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
			Task javaCompilationTask = project.getTasks().getByName("compileJava");
			javaCompilationTask.finalizedBy(postProcess);
			postProcess.setProperty(PostProcessGradleTask.COMPILATION_TASK_PROPERTY, javaCompilationTask);
		}
		project.afterEvaluate(p -> {
			if (p.getPlugins().hasPlugin("com.android.application")) {
				Task postProcessDebug = p.getTasks().create("postProcessDebug", PostProcessGradleTask.class);
				Task postProcessRelease = p.getTasks().create("postProcessRelease", PostProcessGradleTask.class);

				Task compileDebugTask = p.getTasks().getByName("compileDebugJavaWithJavac");
				compileDebugTask.finalizedBy(postProcessDebug);
				postProcessDebug.setProperty(PostProcessGradleTask.COMPILATION_TASK_PROPERTY, compileDebugTask);

				Task compileReleaseTask = p.getTasks().getByName("compileReleaseJavaWithJavac");
				compileReleaseTask.finalizedBy(postProcessRelease);
				postProcessRelease.setProperty(PostProcessGradleTask.COMPILATION_TASK_PROPERTY, compileReleaseTask);
			}
		});

	}

}
