package org.impelsys.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
	@GetMapping("/empServiceFallback")
	public String empServiceFallback() {
		return "Employee service is taking long time than expected.Please retry after some time ";
	}
	@GetMapping("/deptServiceFallback")
	public String deptServiceFallback() {
		return "Department service is taking longer than expected.Please retry after some time";
	}
}
