package com.petstore.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Pet {
	@Id
	@SequenceGenerator(name = "pet_sequence",allocationSize = 1,sequenceName = "pet_sequence")
	@GeneratedValue(generator = "pet_sequence",strategy = GenerationType.SEQUENCE)
	private Long petId;
	private String petName;

	private String imageUrl;
	private String petDetail;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "cat_pet_key")
	private Category category;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "tag_pet_key")
	private Tag tag;
	private String petStatus;
	
	public Pet() {}

	public Long getPetId() {
		return petId;
	}

	public void setPetId(Long petId) {
		this.petId = petId;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPetDetail() {
		return petDetail;
	}

	public void setPetDetail(String petDetail) {
		this.petDetail = petDetail;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public String getPetStatus() {
		return petStatus;
	}

	public void setPetStatus(String petStatus) {
		this.petStatus = petStatus;
	}

	public Pet(Long petId, String petName, String imageUrl, String petDetail, Category category, Tag tag,
			String petStatus) {
		super();
		this.petId = petId;
		this.petName = petName;
		this.imageUrl = imageUrl;
		this.petDetail = petDetail;
		this.category = category;
		this.tag = tag;
		this.petStatus = petStatus;
	}
	
	
	
}
