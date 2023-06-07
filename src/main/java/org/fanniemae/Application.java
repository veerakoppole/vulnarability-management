package org.fanniemae;

import java.io.IOException;

import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.fanniemae.util.JSONParser;
import org.fanniemae.vo.DependecyManager;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

public class Application {

	public static void main(String[] args) {
		if (args.length == 0) {
            System.out.println("No command-line arguments provided.");
        } else {
            System.out.println("Command-line arguments:");
            String inputFile = args[0];
            System.out.println("input json fie location "+args[0]);
            try {
            	DependecyManager depdencyManager = JSONParser.createDependencyManager(inputFile);
            	POMUpdate pomUpdate = new POMUpdate();
            	pomUpdate.updatePOM(depdencyManager);
			} catch (StreamReadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatabindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

}
