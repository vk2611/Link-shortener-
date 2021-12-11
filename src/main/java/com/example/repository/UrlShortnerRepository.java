package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Url;

@Repository
public interface UrlShortnerRepository extends JpaRepository<Url, Long> {

	Url findByShortUrl(String fullUrl);

	boolean existsByShortUrl(String shortUrl);

}
