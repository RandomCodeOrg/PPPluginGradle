package com.github.randomcodeorg.ppplugin.data.gradle;

import java.io.File;

import org.gradle.api.tasks.SourceSetContainer;

public class JavaSourceSetProvider implements SourceSetProvider {

	private final SourceSetContainer container;
	
	public JavaSourceSetProvider(SourceSetContainer container) {
		this.container = container;
	}

	@Override
	public SourceSet getByName(String name) {
		org.gradle.api.tasks.SourceSet set = container.getByName(name);
		return new SourceSet() {
			
			@Override
			public Iterable<File> getCompileClasspath() {
				return set.getCompileClasspath();
			}
		};
	}

	@Override
	public Iterable<String> getNames() {
		return container.getNames();
	}

	@Override
	public int size() {
		return container.size();
	}

}
