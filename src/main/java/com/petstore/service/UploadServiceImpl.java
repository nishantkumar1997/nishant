package com.petstore.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.petstore.entity.Pet;
import com.petstore.repository.PetRepository;
@Service
public class UploadServiceImpl {

	@Autowired
	private PetRepository petRepository;
	
	private final String UPLOAD_PATH = "D:\\work-space\\petstore\\src\\main\\resources\\static\\images";

	 	  public boolean uploadImage(MultipartFile file,Long petId) {
	 		  Pet pet = petRepository.findById(petId).get();
		  
		  boolean isUploaded = false;
		  
		  try {
			Files.copy(file.getInputStream(), Paths.get(UPLOAD_PATH+File.separator+file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			pet.setImageUrl(UPLOAD_PATH+File.separator+file.getOriginalFilename());
			petRepository.save(pet);
			isUploaded = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return isUploaded;
	  }
}
