package com.app.springreactive.model;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Product {
	private String id;
	private String description;
	private Long quantity;
	private double price;
}