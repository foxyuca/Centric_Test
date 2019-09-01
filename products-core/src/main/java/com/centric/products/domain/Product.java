package com.centric.products.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
public class Product {

	
	@Id
	@GeneratedValue(generator = "UUID")
	@JsonIgnore
	@Column(name = "id")
	private UUID id;
	
	@Column(length = 64, name = "name", nullable = false)
	private String name;
	
	@Column(length = 64, name = "description", nullable = false)
	private String description;
	
	@Column(length = 64, name = "category", nullable = false)
	private String category;
	
	@JsonIgnore
	@Column(length = 250, name = "tags", nullable = false)
	private String tagsList;
	
	@Column(length = 64, name = "brand", nullable = false)
	private String brand;
	
	@Transient
	private List<String> tags = new ArrayList<String>();
	
	@JsonIgnore
	@CreationTimestamp
	@Column(name = "created_at", 
			  nullable = false, 
			  updatable = false, 
			  insertable = false, 
			  columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getTags() {
		String[] tagFromDb = this.tagsList.split(",");
		this.tags.addAll(Arrays.asList(tagFromDb));
		return tags;
	}

	public void setTags(List<String> tags) {
		StringBuilder stringTags = new StringBuilder();
		tags.stream().forEach(tag -> stringTags.append(tag).append(","));
		this.tagsList = stringTags.toString();
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getTagsList() {
		return tagsList;
	}

	public void setTagsList(String tagsList) {
		this.tagsList = tagsList;
		getTags();
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	

}
