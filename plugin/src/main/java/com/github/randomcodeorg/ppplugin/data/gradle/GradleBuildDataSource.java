package com.github.randomcodeorg.ppplugin.data.gradle;

import java.io.File;

import org.gradle.api.DefaultTask;
import org.gradle.api.Task;

import com.github.randomcodeorg.ppplugin.data.BuildDataSource;
import com.github.randomcodeorg.ppplugin.data.BuildLog;
import com.github.randomcodeorg.ppplugin.data.ProjectData;

public class GradleBuildDataSource implements BuildDataSource {

	private final BuildLog log;
	private final DefaultTask task;
	private final ProjectData projectData;
	private final Task compilationTask;
	private final String compiledClassesDir;

	public GradleBuildDataSource(DefaultTask task, Task compilationTask, String compiledClassesDir, BuildLog log,
			ProjectData projectData) {
		this.log = log;
		log.info("Hello World!");
		this.task = task;
		this.projectData = projectData;
		this.compilationTask = compilationTask;
		this.compiledClassesDir = compiledClassesDir;
	}

	@Override
	public BuildLog getLog() {
		return log;
	}

	@Override
	public String getProjectBuildDir() {
		return task.getProject().getBuildDir().getAbsolutePath();
	}

	@Override
	public String getCompiledClassesDir() {
		if (compiledClassesDir != null && !compiledClassesDir.isEmpty())
			return compiledClassesDir;
		if (compilationTask != null && !compilationTask.getOutputs().getFiles().isEmpty()) {
			return compilationTask.getOutputs().getFiles().getFiles().iterator().next().getAbsolutePath();
		}
		String path = task.getProject().getBuildDir().getAbsolutePath();
		if (!path.endsWith(File.separator)) {
			return String.format("%%sclasses%smain", path, File.separator, File.separator);
		} else {
			return String.format("%sclasses%smain", path, File.separator);
		}
	}

	@Override
	public ProjectData getProject() {
		return projectData;
	}

}
