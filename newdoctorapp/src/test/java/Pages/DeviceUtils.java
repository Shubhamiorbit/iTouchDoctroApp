package Pages;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class DeviceUtils {
	public static String getConnectedDevice() {
	 try {
	        Process process = Runtime.getRuntime().exec("adb devices");
	        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

	        String line;
	        while ((line = reader.readLine()) != null) {
	            if (line.endsWith("device") && !line.startsWith("List")) {
	                return line.split("\\s+")[0];
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    throw new RuntimeException("No device connected!");
}
}