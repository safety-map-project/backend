package main;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import model.LocationLatLog;

public class Test {

	public static void main(String[] args) {
		String filePath = "C:\\pub2504\\eclipse_workspace\\project\\location.json";

		try (FileReader reader = new FileReader(filePath)) {
			Gson gson = new Gson();
			Type listType = new TypeToken<List<LocationLatLog>>() {
			}.getType();

			List<LocationLatLog> locations = gson.fromJson(reader, listType);

			for (LocationLatLog loc : locations) {
				System.out.println(loc);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
