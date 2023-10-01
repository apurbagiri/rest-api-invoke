package giri.apurba.apps.util;

import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jayway.jsonpath.JsonPath;

/**
 * Utility class to do common JSON operations
 * 
 * @author AGIRI6
 *
 */
public class JSONUtil {

	/**
	 * Method to retrieve a JSON property value based on provided path. Example of
	 * path: $.data.attributes.organizationType.code
	 * 
	 * @param jsonString
	 * @param path
	 * @return {@link Object}
	 */
	public static Object getPropertyValue(String jsonString, String path) {
		try {
			return JsonPath.parse(jsonString).read(path);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Method to retrieve a JSON property value based on provided path. Example of
	 * path: $.data.attributes.organizationType.code
	 * 
	 * @param jsonObject
	 * @param path
	 * @return {@link Object}
	 */
	public static Object getPropertyValue(JSONObject jsonObject, String path) {
		try {
			return JsonPath.parse(jsonObject.toJSONString()).read(path);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Add or update a JSON property value. Returns new JSON string with updated
	 * property.
	 * 
	 * @param jsonObject
	 * @param path
	 * @param key
	 * @param value
	 * @return {@link String}
	 */
	public static String addPropertyValue(JSONObject jsonObject, String path, String key, String value) {
		try {
			return JsonPath.parse(jsonObject.toJSONString()).put(path, key, value).jsonString();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Add or update a JSON property value. Returns new JSON string with updated
	 * property.
	 * 
	 * @param jsonString
	 * @param path
	 * @param key
	 * @param value
	 * @return {@link String}
	 */
	public static String addPropertyValue(String jsonString, String path, String key, String value) {
		try {
			return JsonPath.parse(jsonString).put(path, key, value).jsonString();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Returns {@link JSONObject} for a given jsonString
	 * 
	 * @param jsonString
	 * @return {@link JSONObject}
	 */
	public static JSONObject getJSONObject(String jsonString) {
		JSONObject jsonObject = null;
		try {
			JSONParser parser = new JSONParser();
			jsonObject = (JSONObject) parser.parse(jsonString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * Returns {@link JSONObject} from input character streams (e.g. FileReader)
	 * 
	 * @param input
	 * @return {@link JSONObject}
	 */
	public static JSONObject getJSONObject(Reader input) {
		JSONObject jsonObject = null;
		try {
			JSONParser parser = new JSONParser();
			jsonObject = (JSONObject) parser.parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

}