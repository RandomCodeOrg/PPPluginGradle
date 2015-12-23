package com.github.randomcodeorg.ppplugin.data.gradle;

import java.util.ArrayList;
import java.util.List;

import org.gradle.api.Project;
import org.gradle.api.artifacts.ConfigurationContainer;

import com.github.randomcodeorg.ppplugin.data.DependencyResolutionException;
import com.github.randomcodeorg.ppplugin.data.ProjectData;

public class GradleProjectData implements ProjectData {

	private final List<String> runtimeClasspathElements = new ArrayList<>();
	private final List<String> testClasspathElements = new ArrayList<>();
	private final List<String> compileClasspathElements = new ArrayList<>();

	public GradleProjectData(Project project) {
		ConfigurationContainer configurations = project.getConfigurations();
		if (configurations.getNames().contains("compile")) {
			buildClasspathElements(compileClasspathElements, configurations.getByName("compile").getAsPath());
		}
		if (configurations.getNames().contains("testCompile")) {
			buildClasspathElements(testClasspathElements, configurations.getByName("testCompile").getAsPath());
		}
		if (configurations.getNames().contains("provided")) {
			buildClasspathElements(runtimeClasspathElements, configurations.getByName("provided").getAsPath());
		}
	}

	protected void buildClasspathElements(List<String> target, String path) {
		if (path == null || path.isEmpty())
			return;
		if (!path.contains(";")) {
			target.add(path);
			return;
		}
		String[] elements = path.split(";");
		for (String e : elements)
			target.add(e.trim());
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
