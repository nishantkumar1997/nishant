package com.petstore.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.petstore.entity.Category;
import com.petstore.entity.Pet;
import com.petstore.repository.CategoryRepository;
import com.petstore.repository.PetRepository;
import com.petstore.repository.TagRepository;
import com.petstore.service.UploadServiceImpl;

@RestController
@RequestMapping("pet")
public class PetController {
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private UploadServiceImpl serviceImpl;

	
	@GetMapping
	public ResponseEntity<?> getAllPets(){
		
	return ResponseEntity.status(HttpStatus.OK).body(petRepository.findAll());
	}
	
	@GetMapping("{petId}")
	public ResponseEntity<?> getaPet(@PathVariable Long petId){
		Pet pet = petRepository.findById(petId).get();
	return ResponseEntity.status(HttpStatus.OK).body(pet);
	}
	
	@PostMapping("")
	public ResponseEntity<?> addPet(@RequestBody Pet pet){
		petRepository.save(pet);
		//log.info("message");

	return ResponseEntity.status(HttpStatus.CREATED).body("Added successfully...");
	}
	
	@PutMapping("/{petId}")
	public ResponseEntity<?> updatePet(@RequestBody Pet pet, @PathVariable Long petId){
		Pet p = petRepository.findById(petId).get();
		if(p!=null) {
			p.setPetName(pet.getPetName());
			p.setPetDetail(pet.getPetDetail());
			p.setPetStatus(pet.getPetStatus());
			petRepository.save(p);
			return ResponseEntity.status(HttpStatus.OK).body(p);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found");
	
		}

	}
	
	@PostMapping("/{petId}/uploadimage")
	public ResponseEntity<?> addPet(@RequestBody MultipartFile file, @PathVariable("petId") Long petId){

		if(petRepository.existsById(petId)) {
		Boolean isUploaded	= serviceImpl.uploadImage(file,petId); 
		if(isUploaded) 
			return ResponseEntity.status(HttpStatus.OK).body("Image uploaded...");
		else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Error while upload...");
		}

		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found...");

		}
		
	}
	
	@GetMapping("/findByStatus")
	public ResponseEntity<?> getAllPetsByStatus(@PathParam(value = "petStatus") String petStatus){
		return ResponseEntity.status(HttpStatus.OK).body(petRepository.findByPetStatus(petStatus));

	}
}
