package org.fanniemae.vo;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DependecyManager {

	private String sourcePomFileLocation;
	private String destinationPomFileLocation;
	private Set<POMDepenedncy> dependencies;


	public Set<POMDepenedncy> getDependencies() {
		return dependencies;
	}

	public void setDependencies(Set<POMDepenedncy> dependencies) {
		this.dependencies = dependencies;
	}

	public String getSourcePomFileLocation() {
		return sourcePomFileLocation;
	}

	public void setSourcePomFileLocation(String sourcePomFileLocation) {
		this.sourcePomFileLocation = sourcePomFileLocation;
	}

	public String getDestinationPomFileLocation() {
		return destinationPomFileLocation;
	}

	public void setDestinationPomFileLocation(String destinationPomFileLocation) {
		this.destinationPomFileLocation = destinationPomFileLocation;
	}

}
