package com.petstore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

	@Id
	@SequenceGenerator(name = "tag_sequence",allocationSize = 1,sequenceName = "tag_sequence")
	@GeneratedValue(generator = "tag_sequence",strategy = GenerationType.SEQUENCE)
	private Long tagId;
	private String tagName;
	
	public Tag() {}
	public Tag(Long tagId, String tagName) {
		super();
		this.tagId = tagId;
		this.tagName = tagName;
	}
	public Long getTagId() {
		return tagId;
	}
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	
	
}
