import groovy.lang.GroovyShell;
import groovy.lang.Script;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExcludeDependencyExample {
    public static void main(String[] args) {
        String filePath = "path/to/build.gradle"; // Path to the build.gradle file

        try {
            // Read the contents of the build.gradle file
            String buildScript = readFileContent(filePath);

            // Modify the build.gradle script
            String updatedScript = excludeDependency(buildScript, "com.example", "my-library");

            // Write the updated build.gradle script back to the file
            writeToFile(filePath, updatedScript);

            System.out.println("Dependency excluded successfully.");
        } catch (IOException e) {
            System.out.println("Error excluding dependency: " + e.getMessage());
        }
    }

    private static String readFileContent(String filePath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        return new String(bytes);
    }

    private static void writeToFile(String filePath, String content) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, content.getBytes());
    }

    private static String excludeDependency(String buildScript, String groupId, String artifactId) {
        GroovyShell shell = new GroovyShell();
        Script script = shell.parse(buildScript);

        // Find the dependency and exclude it
        script.getMethod("configurations").invoke(script).call().allDependencies.each {
            if (it.group == groupId && it.name == artifactId) {
                it.exclude(group: "*")
            }
        }

        // Return the updated build.gradle script as a string
        return script.toString();
    }
}
