package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Url;

@Service
public interface UrlShortnerService {

	Url createShortUrl(Url url);

	Url getUrl(String url);

	List<Url> getAllUrl();

}
