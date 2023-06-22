package org.fanniemae;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ModifyGradle {
    public static void main(String[] args) {
        String buildFilePath = "C:\\GitHub\\vulnarability-management\\src\\test\\resources\\build.gradle"; // Path to the build.gradle file

        try {
            File buildFile = new File(buildFilePath);
            String content = FileUtils.readFileToString(buildFile, StandardCharsets.UTF_8);

            // Modify the content of the build file as needed
            content = content.replace("old-dependency", "new-dependency");
            content = content.replace("implementation 'org.example:dependency'", "implementation('org.example:dependency') { exclude group: 'excluded.group', module: 'excluded-module' }");

            FileUtils.writeStringToFile(buildFile, content, StandardCharsets.UTF_8);

            System.out.println("build.gradle file modified successfully.");
        } catch (IOException e) {
            System.out.println("Error modifying build.gradle file: " + e.getMessage());
        }
    }
}

