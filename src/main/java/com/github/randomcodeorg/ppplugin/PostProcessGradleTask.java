package com.github.randomcodeorg.ppplugin;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.artifacts.dsl.DependencyHandler;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.api.tasks.TaskAction;

import com.github.randomcodeorg.ppplugin.data.gradle.GradleBuildDataSource;

public class PostProcessGradleTask extends DefaultTask {

	public PostProcessGradleTask() {

	}

	@TaskAction
	public void postProcess() {
		Project project = getProject();
		final SourceSetContainer sourceSets =
                (SourceSetContainer) project.getProperties().get("sourceSets");
        sourceSets.getByName("main").getResources().srcDir(outputDir);

        final Task t = tasks.getByName(JavaPlugin.PROCESS_RESOURCES_TASK_NAME);
        t.dependsOn(task);
	}

}
