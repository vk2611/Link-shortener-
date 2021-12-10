package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Url;

@RestController
public class UrlShortnerController {

	@PostMapping("/getShortUrl")
	public ResponseEntity<Url> getShortUrl(@RequestBody Url url) {
		return null;

	}

	@GetMapping("/getFullUrl")
	public ResponseEntity<Url> getFullUrl(@RequestBody Url url) {
		return null;

	}

	@GetMapping("/redirectUrl")
	public void redirectUrl() {

	}
}
