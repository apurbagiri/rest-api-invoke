package giri.apurba.apps.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import giri.apurba.apps.entities.Greeting;
import giri.apurba.apps.model.HttpErrorResponse;
import giri.apurba.apps.service.api.RestAPIExample;

@RestController
public class APIController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping(value = "/hello", produces = "application/json")
	public Greeting hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping(value = "/employees", produces = "application/json")
	public String employees() {
		return RestAPIExample.getEmployees();
	}
	
	@GetMapping(value = "/employee", produces = "application/json")
	public String employee(@RequestParam(value = "id") String id) {
		return RestAPIExample.getEmployeeById(id);
	}
	
}
