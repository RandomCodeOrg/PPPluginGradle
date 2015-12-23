package com.github.randomcodeorg.ppplugin.data.gradle;

import java.io.File;

public interface SourceSet {

	Iterable<File> getCompileClasspath();
	
}
