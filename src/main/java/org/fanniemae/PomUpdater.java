package org.fanniemae;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Exclusion;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PomUpdater {
	public static void test(String[] args) {
        String pomFilePath = "C:\\test/pom.xml";
        String artifactId = "maven-model";
        String newVersion = "3.25.0";
        String jsonExclusions = "{\"exclusions\": [\"exclusion1\", \"exclusion2\"]}";

        try {
            // Load the pom.xml file
            File pomFile = new File(pomFilePath);
            FileReader fileReader = new FileReader(pomFile);
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(fileReader);
            fileReader.close();

            // Find the artifact in dependencies
            List<Dependency> dependencies = model.getDependencies();
            for (Dependency dependency : dependencies) {
                if (dependency.getArtifactId().equals(artifactId)) {
                    // Update the version
                    dependency.setVersion(newVersion);

                    // Check for exclusions in JSON input
					
					  if (jsonExclusions != null && !jsonExclusions.isEmpty()) { 
						  // Parse JSON and add exclusions 
						  List<String> exclusions = parseJsonExclusions(jsonExclusions);
					  for (String exclusion : exclusions) { Exclusion newExclusion = new
					  Exclusion(); newExclusion.setGroupId(dependency.getGroupId());
					  newExclusion.setArtifactId(exclusion); dependency.addExclusion(newExclusion);
					  } }
					 

                    break; // No need to continue searching
                }
            }

            // Save the updated pom.xml
            MavenXpp3Writer writer = new MavenXpp3Writer();
            FileWriter fileWriter = new FileWriter(pomFile);
            writer.write(fileWriter, model);
            fileWriter.close();

            System.out.println("pom.xml successfully updated!");
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }

	private static List<String> parseJsonExclusions(String jsonExclusions) {
		// Parse the JSON and return a list of exclusions
		// You can use any JSON parsing library of your choice here

		// Dummy implementation
		return List.of("exclusion1", "exclusion2");
	}
}
