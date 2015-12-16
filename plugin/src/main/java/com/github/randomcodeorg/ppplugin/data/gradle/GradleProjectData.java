package com.github.randomcodeorg.ppplugin.data.gradle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.gradle.api.tasks.SourceSetContainer;

import com.github.randomcodeorg.ppplugin.data.BuildLog;
import com.github.randomcodeorg.ppplugin.data.DependencyResolutionException;
import com.github.randomcodeorg.ppplugin.data.ProjectData;

public class GradleProjectData implements ProjectData {

	private final List<String> runtimeClasspathElements = new ArrayList<>();
	private final List<String> testClasspathElements = new ArrayList<>();
	private final List<String> compileClasspathElements = new ArrayList<>();

	public GradleProjectData(BuildLog log, SourceSetContainer container) {
		for (File f : container.getByName("main").getCompileClasspath()) {
			log.info(String.format("Adding compilation classpath element: %s", f.getAbsolutePath()));
			compileClasspathElements.add(f.getAbsolutePath());
		}
	}

	@Override
	public List<String> getTestClasspathElements() throws DependencyResolutionException {
		return testClasspathElements;
	}

	@Override
	public List<String> getRuntimeClasspathElements() throws DependencyResolutionException {
		return runtimeClasspathElements;
	}

	@Override
	public List<String> getCompileClasspathElements() throws DependencyResolutionException {
		return compileClasspathElements;
	}

}
