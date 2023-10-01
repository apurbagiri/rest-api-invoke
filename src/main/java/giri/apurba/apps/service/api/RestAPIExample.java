package giri.apurba.apps.service.api;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import giri.apurba.apps.model.HttpErrorResponse;
import giri.apurba.apps.service.core.APIService;
import giri.apurba.apps.util.JSONUtil;

public class RestAPIExample {

	private static String BASE_SERVICE_URL = "https://dummy.restapiexample.com/api/v1/{apiResourceName}";

	public static String getEmployees() {
		APIService apiService = new APIService();
		var response = apiService.invokeAPI(BASE_SERVICE_URL.replace("{apiResourceName}", "employees"));
		return response.body();
	}

	public static String getEmployeeById(String id) {
		if (id == null || id.trim().isEmpty()) {
			return new HttpErrorResponse("Employee id required").body();
		}
		APIService apiService = new APIService();
		var response = apiService.invokeAPI(BASE_SERVICE_URL.replace("{apiResourceName}", "employees"));
		JSONObject responseObj = JSONUtil.getJSONObject(response.body());

		ArrayList<JSONObject> filteredObj = (ArrayList<JSONObject>) (((JSONArray) responseObj.get("data")).stream()
				.filter(emp -> ((JSONObject) emp).get("id") == Long.valueOf(id)).collect(Collectors.toList()));
		if (filteredObj.size() > 0) {
			return filteredObj.get(0).toJSONString();
		} else {
			return new HttpErrorResponse(404, "Employee id not found").body();
		}

	}
}
