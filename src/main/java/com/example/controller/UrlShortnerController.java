package com.example.controller;

import java.util.List;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Url;
import com.example.service.UrlShortnerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/url")
@Slf4j
public class UrlShortnerController {

	@Autowired
	private UrlShortnerService urlShortnerService;

	@PostMapping("/createShortUrl")
	public ResponseEntity getShortUrl(@RequestBody Url url) {
		UrlValidator validator = new UrlValidator(new String[] { "http", "https" });
		if (!validator.isValid(url.getFullUrl())) {
			return ResponseEntity.badRequest().body("Bad Url");
		}
		Url urlResponse = urlShortnerService.createShortUrl(url);
		return ResponseEntity.ok().body(urlResponse);

	}

	@GetMapping("/fullUrl/{id}")
	public ResponseEntity getFullUrl(@PathVariable String id) {
		Url url = urlShortnerService.getUrl(id);
		if (ObjectUtils.isEmpty(url)) {
			return ResponseEntity.badRequest().body("No Url found with this id:-" + id);
		}
		return ResponseEntity.ok().body(url.getFullUrl());
	}

	@GetMapping("/getAllUrl")
	public List<Url> getAllUrl() {
		return urlShortnerService.getAllUrl();
	}
}
