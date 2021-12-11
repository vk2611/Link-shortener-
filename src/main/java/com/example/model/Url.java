package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Url")
public class Url {

	@Id
	private String fullUrl;

	private String shortUrl;

}
