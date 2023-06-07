package org.fanniemae.util;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.fanniemae.vo.DependecyManager;
import org.fanniemae.vo.POMDepenedncy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONParser {

	public static final String createJson(DependecyManager dependencyManager) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dependencyManager);
	}

	public static final DependecyManager createDependencyManager(String jsonFilePath) throws StreamReadException, DatabindException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		File inputFile = new File(jsonFilePath);
		DependecyManager dependencyManager = mapper.readValue(inputFile, DependecyManager.class);
		System.out.println(dependencyManager);
		return dependencyManager;
	}
	/**
	public static void main(String[] args) {
		DependecyManager manager = new DependecyManager();
		
		Set<POMDepenedncy> dependencies = new HashSet<>();
		
		POMDepenedncy depenedncy =new POMDepenedncy();
		depenedncy.setArtifactId("maven-model");
		depenedncy.setGroupId("org.apache.maven");
		depenedncy.setFixVersion("3.29.0");
		
		Set<POMDepenedncy> exclusions = new HashSet<>();
		POMDepenedncy exclusion =new POMDepenedncy();
		exclusion.setArtifactId("commons-lang3");
		exclusion.setGroupId("org.apache.logging.log4j");
		exclusion.setFixVersion("2.20.0");
		exclusions.add(exclusion);
		depenedncy.setExclusions(exclusions);
		
		POMDepenedncy depenedncy1 =new POMDepenedncy();
		depenedncy1.setArtifactId("org.apache.commons");
		depenedncy1.setGroupId("commons-lang3");
		depenedncy1.setFixVersion("3.12.0");
		
		
		dependencies.add(depenedncy);
		dependencies.add(depenedncy1);
		manager.setDependencies(dependencies);
		manager.setSourcePomFileLocation("C:\\test\\pom.xml");
		manager.setDestinationPomFileLocation("C:\\test\\newpom.xml");
		try {
			System.out.println(createJson(manager));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} **/
}
