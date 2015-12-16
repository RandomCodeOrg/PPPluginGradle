package com.github.randomcodeorg.ppplugin.data.gradle;

import java.util.List;

import org.gradle.api.tasks.SourceSetContainer;

import com.github.randomcodeorg.ppplugin.data.DependencyResolutionException;
import com.github.randomcodeorg.ppplugin.data.ProjectData;

public class GradleProjectData implements ProjectData {

	private final SourceSetContainer container;
	
	public GradleProjectData(SourceSetContainer container) {
		
	}

	@Override
	public List<String> getTestClasspathElements() throws DependencyResolutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getRuntimeClasspathElements() throws DependencyResolutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCompileClasspathElements() throws DependencyResolutionException {
		// TODO Auto-generated method stub
		return null;
	}

}
