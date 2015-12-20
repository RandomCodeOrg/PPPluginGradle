package com.github.randomcodeorg.ppplugin;

import java.io.IOException;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.api.tasks.TaskAction;

import com.github.randomcodeorg.ppplugin.data.DependencyResolutionException;
import com.github.randomcodeorg.ppplugin.data.gradle.GradleBuildDataSource;
import com.github.randomcodeorg.ppplugin.internals.InternalInvoker;
/**
 * The postprocess task for gradle projects using the 'java' plugin.
 * @author Marcel Singer
 *
 */
public class PostProcessGradleTask extends DefaultTask {

	public PostProcessGradleTask() {

	}

	@TaskAction
	public void postProcess() {
		Project project = getProject();
		final SourceSetContainer sourceSets = (SourceSetContainer) project.getProperties().get("sourceSets");
		GradleBuildDataSource data = new GradleBuildDataSource(this, sourceSets);
		try {
			new InternalInvoker(data).invoke();
		} catch (ClassNotFoundException | IOException | DependencyResolutionException e) {
			throw new RuntimeException(e);
		}

	}

	
	@Override
	public boolean getDidWork() {
		return true;
	}
	

}
