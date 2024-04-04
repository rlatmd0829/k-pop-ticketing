package com.kpop.ticketing.presentation.show.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/concerts")
public class ShowController {

	@GetMapping("/{concertId}/shows")
	public String getConcerts() {
		return """
			[
			  {
			    "showId" : "1",
			    "date" : "2024-04-10"
			  },
			  {
			    "showId" : "2",
			    "date" : "2024-04-14"
			  }
			]
			""";
	}
}
