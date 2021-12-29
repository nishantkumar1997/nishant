package com.petstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petstore.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

	public List<Pet> findByPetStatus(String petStatus);
	
}
