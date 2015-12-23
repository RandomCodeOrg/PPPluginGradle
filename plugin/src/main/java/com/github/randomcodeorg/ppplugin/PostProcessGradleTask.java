package com.github.randomcodeorg.ppplugin;

import java.io.IOException;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.tasks.TaskAction;

import com.github.randomcodeorg.ppplugin.data.BuildLog;
import com.github.randomcodeorg.ppplugin.data.DependencyResolutionException;
import com.github.randomcodeorg.ppplugin.data.ProjectData;
import com.github.randomcodeorg.ppplugin.data.gradle.GradleBuildDataSource;
import com.github.randomcodeorg.ppplugin.data.gradle.GradleBuildLog;
import com.github.randomcodeorg.ppplugin.data.gradle.GradleProjectData;
import com.github.randomcodeorg.ppplugin.internals.InternalInvoker;

/**
 * The postprocess task for gradle projects using the 'java' plugin.
 * 
 * @author Marcel Singer
 *
 */
public class PostProcessGradleTask extends DefaultTask {

	public static final String COMPILATION_TASK_PROPERTY = "compilationTask";
	public static final String COMPILED_CLASES_DIR_PROPERTY = "compiledClassesDir";

	public Task compilationTask = null;
	public String compiledClassesDir = null;

	public PostProcessGradleTask() {

	}

	@TaskAction
	public void postProcess() {
		if (compilationTask != null && !compilationTask.getDidWork()) {
			setDidWork(false);
			return;
		}
		setDidWork(true);
		Project project = getProject();
		BuildLog log = getLog(this);
		ProjectData projectData = new GradleProjectData(project);
		GradleBuildDataSource data = new GradleBuildDataSource(this, compilationTask, compiledClassesDir, log,
				projectData);
		try {
			new InternalInvoker(data).invoke();
		} catch (ClassNotFoundException | IOException | DependencyResolutionException e) {
			throw new RuntimeException(e);
		}

	}

	protected BuildLog getLog(Task task) {
		return new GradleBuildLog(task.getLogger());
	}

}
