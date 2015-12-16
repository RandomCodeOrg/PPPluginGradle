package com.github.randomcodeorg.ppplugin.data.gradle;

import org.gradle.api.DefaultTask;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.tasks.SourceSetContainer;

import com.github.randomcodeorg.ppplugin.data.BuildDataSource;
import com.github.randomcodeorg.ppplugin.data.BuildLog;
import com.github.randomcodeorg.ppplugin.data.ProjectData;

public class GradleBuildDataSource implements BuildDataSource {

	private final BuildLog log;
	private final DefaultTask task;
	private final SourceSetContainer sourceSetContainer;

	public GradleBuildDataSource(DefaultTask task, SourceSetContainer container) {
		log = new GradleBuildLog(task.getLogger());
		this.task = task;
		this.sourceSetContainer = container;
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
		return null; // Build default path
	}

	@Override
	public ProjectData getProject() {
		
	}

}
