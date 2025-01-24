package save;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.TreeMap;

public class SaveMapToJson {
  private static final Logger LOGGER = Logger.getLogger(SaveMapToJson.class.getName());

  public static void main(String[] args) {
    Gson gson = new Gson();
    TreeMap<String, Object> myMap = new TreeMap<>();
    myMap.put("name", "John Doe");
    myMap.put("age", 25);
    myMap.put("city", "New York");

    String json = gson.toJson(myMap);
    try(FileWriter file = new FileWriter("myMap.json")) {
      file.write(json);
      System.out.println("Successfully Copied JSON Object to File...");
//      System.out.println("\nJSON Object: " + json);
    } catch (IOException e) {
//      e.printStackTrace();
      LOGGER.log(Level.SEVERE, "An error occurred.", e);
    }



    // JSON에서 Map 불러오기
    try (FileReader reader = new FileReader("myMap.json")) {
      // Read JSON file
      Map<?, ?> map = gson.fromJson(reader, Map.class);
//      System.out.println(map);

      // Iterate over the map and print the key-value pairs
      for (Map.Entry<?, ?> entry : map.entrySet()) {
        System.out.println(entry.getKey() + "=" + entry.getValue());
      }
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "An error occurred.", e);
    }

  }
}
