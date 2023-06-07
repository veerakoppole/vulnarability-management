package org.fanniemae;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Exclusion;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.fanniemae.vo.DependecyManager;
import org.fanniemae.vo.POMDepenedncy;

public class POMUpdate {

	public void updatePOM(DependecyManager dependecyManager) throws IOException, XmlPullParserException {
		Set<POMDepenedncy> newDepenDencies = buildNewDependencies(dependecyManager);
		Map<String, POMDepenedncy> dependenciesMap = buildDependencyMap(dependecyManager);
		File pomFile = new File(dependecyManager.getSourcePomFileLocation());
		FileReader fileReader = new FileReader(pomFile);
		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model = reader.read(fileReader);
		fileReader.close();

		// Find the artifact in dependencies
		List<Dependency> dependencies = model.getDependencies();
		for (Dependency dependency : dependencies) {
			if (dependenciesMap.containsKey(dependency.getArtifactId())) {
				// Update the version
				POMDepenedncy customDependency = dependenciesMap.get(dependency.getArtifactId());
				dependency.setVersion(customDependency.getFixVersion());
				if (customDependency.getExclusions() != null) {
					customDependency.getExclusions().forEach(exclusion -> {
						Exclusion newExclusion = new Exclusion();
						newExclusion.setGroupId(exclusion.getGroupId());
						newExclusion.setArtifactId(exclusion.getArtifactId());
						dependency.addExclusion(newExclusion);
					});
				}
			}
		}
		if(newDepenDencies!=null && newDepenDencies.size()>0) {
			newDepenDencies.forEach(
					exludedDependency ->{
						Dependency newDependency = new Dependency();
			            newDependency.setGroupId(exludedDependency.getGroupId());
			            newDependency.setArtifactId(exludedDependency.getArtifactId());
			            newDependency.setVersion(exludedDependency.getFixVersion());
			            model.addDependency(newDependency);
					});
		}
		
		 MavenXpp3Writer writer = new MavenXpp3Writer();
         FileWriter fileWriter = new FileWriter(new File(dependecyManager.getDestinationPomFileLocation()));
         writer.write(fileWriter, model);
         fileWriter.close();

         System.out.println("pom.xml successfully updated!");
	}

	private Set<POMDepenedncy> buildNewDependencies(DependecyManager dependecyManager) {
		Set<POMDepenedncy> newDepenDencies = new HashSet<>();
		dependecyManager.getDependencies().forEach(dependency -> {
			if (dependency.getExclusions() != null) {
				newDepenDencies.addAll(dependency.getExclusions());
			}
		});
		return newDepenDencies;
	}

	private Map<String, POMDepenedncy> buildDependencyMap(DependecyManager dependecyManager) {
		Map<String, POMDepenedncy> dependenciesMap = new HashMap<>();
		dependecyManager.getDependencies().forEach(dependency -> {
			dependenciesMap.put(dependency.getArtifactId(), dependency);
		});
		return dependenciesMap;
	}

}
