package com.github.randomcodeorg.ppplugin;

import org.gradle.api.DefaultTask;
import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.tasks.TaskAction;

public class ShowPathsTask extends DefaultTask {

	public ShowPathsTask() {
		
	}
	
	@TaskAction
	public void showPaths(){
		ConfigurationContainer configs = getProject().getConfigurations();
		for(String name : configs.getNames()){
			System.out.println(String.format("Configurations: %s", name));
			System.out.println(configs.getByName(name).getAsPath());
			System.out.println();
		}
	}

}
