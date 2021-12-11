package com.example.service.Impl;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.model.Url;
import com.example.repository.UrlShortnerRepository;
import com.example.service.UrlShortnerService;
import com.example.util.UrlRedisCacheUtil;
import com.google.common.hash.Hashing;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UrlShortnerServiceImpl implements UrlShortnerService {

	@Autowired
	private UrlShortnerRepository urlShortnerRepository;

	@Autowired
	private UrlRedisCacheUtil urlRedisCacheUtil;

	@Autowired
	Environment enviroment;

	@Override
	public Url createShortUrl(Url url) {
		String shortUrl = Hashing.murmur3_32().hashString(url.getFullUrl(), Charset.defaultCharset()).toString();
		url.setShortUrl(shortUrl);
		urlShortnerRepository.save(url);
		return url;
	}

	@Override
	public Url getUrl(String shortUrl) {
		String redisCheck = enviroment.getProperty("redisCheck");
		Url url = null;
		if (Boolean.parseBoolean(redisCheck)) {
			url = urlRedisCacheUtil.findByShortUrl(shortUrl);
		} else {
			url = urlShortnerRepository.findByShortUrl(shortUrl);
		}
		return url;
	}

	@Override
	public List<Url> getAllUrl() {
		return urlShortnerRepository.findAll();
	}

}
