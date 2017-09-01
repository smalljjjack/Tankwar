import java.io.IOException;
import java.util.Properties;

public class PropertiesMag {
	static Properties pro = new Properties();
	
	static{
		try {
			pro.load(PropertiesMag.class.getClassLoader().getResourceAsStream("config/TankWar.Properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static String getProperties(String key){
		return pro.getProperty(key);
	}
	
}
