package com.github.randomcodeorg.ppplugin.data.gradle;

import java.io.File;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.SourceSetContainer;

import com.github.randomcodeorg.ppplugin.data.BuildDataSource;
import com.github.randomcodeorg.ppplugin.data.BuildLog;
import com.github.randomcodeorg.ppplugin.data.ProjectData;

public class GradleBuildDataSource implements BuildDataSource {

	private final BuildLog log;
	private final DefaultTask task;
	private final ProjectData projectData;

	public GradleBuildDataSource(DefaultTask task, SourceSetContainer container) {
		log = new GradleBuildLog(task.getLogger());
		log.info("Hello World!");
		this.task = task;
		this.projectData = new GradleProjectData(log, container);
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
		String path = task.getProject().getBuildDir().getAbsolutePath();
		if (!path.endsWith(File.separator)) {
			return String.format("%s%sclasses%smain", path, File.separator, File.separator);
		} else {
			return String.format("%sclasses%smain", path, File.separator);
		}
	}

	@Override
	public ProjectData getProject() {
		return projectData;
	}

}
