package configuration;

import java.io.InputStream;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigReader {
	
 public static RootConfig config;
 
 static{
	 try {
			ObjectMapper mapper = new ObjectMapper();
			InputStream stream = ConfigReader.class.getClassLoader().getResourceAsStream("config/environmentConfig.json");
			config = mapper.readValue(stream, RootConfig.class);
	 }
	 catch(Exception e)
	 {
		 System.out.println("Failed to read the json");
		e.printStackTrace(); 
	 }
 }
 
 public static EnvironmentConfig getActiveEnvironment() {
	 
	 String runtime = System.getProperty("env");
	 String selectEnv = (runtime != null) ? runtime : config.getDefaultEnv();
	 
	 //OR
//	 if(runtime != null) {
//		 selectEnv = runtime;
//	 }else {
//		 selectEnv = config.getDefaultEnv();
//	 }
	 
	 System.out.println("Selected environment is : " + selectEnv);
	 Map<String,EnvironmentConfig> envs = config.getEnvironment();
	 
	  return envs.get(selectEnv);
 }
 
 
 
 
 
 
 
 
 
 
 
 
 

}
