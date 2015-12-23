package com.github.randomcodeorg.ppplugin.data.gradle;

public interface SourceSetProvider {

	public SourceSet getByName(String name);
	public Iterable<String> getNames();
	public int size();
	
}
