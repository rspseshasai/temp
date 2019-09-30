package sample1.jsonparser;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonParser {
	
    @SuppressWarnings("unchecked")
	public void parse() {
	JSONParser parser = new JSONParser();
	 
    try {

        Object obj = parser.parse(new FileReader(
                "C:/Users/rspse/Documents/workspace-sts-3.9.9.RELEASE/jsonparser/src/main/java/sample1/jsonparser/json_file.json"));

        JSONObject jsonObject = (JSONObject) obj;

        String name = (String) jsonObject.get("Name");
        String author = (String) jsonObject.get("Author");
        JSONArray companyList = (JSONArray) jsonObject.get("Company List");

        System.out.println("Name: " + name);
        System.out.println("Author: " + author);
        System.out.println("\nCompany List:");
        Iterator<String> iterator = companyList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
	}
}
