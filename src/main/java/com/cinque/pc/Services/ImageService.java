package com.cinque.pc.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cinque.pc.Entities.Image;
import com.cinque.pc.Repositories.ImageRepository;

@Service
public class ImageService {
	
	//cargar Image, editar, eliminar
	
	@Autowired
	private ImageRepository imageRepository;
	
	public Image saveImage (MultipartFile file) throws Exception{
		if(file != null) { //En caso de que la Image no sea nula
			try {
				Image image = new Image(); //Genero un nuevo objeto (id única)
				//Setteo sus atributos
				image.setMime(file.getContentType());
				image.setName(file.getName());
				image.setContent(file.getBytes());
				
				return imageRepository.save(image);//Devuelvo la imágen que guardo
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return null; //Si es nula retorna nulo
	}
	
	
	
	public Image editImage(MultipartFile file, String id) {
		
		if(file != null) { //En caso de que la Image no sea nula
			try {
				Image image = new Image(); //Genero un nuevo objeto (id única)
				
				if(id != null) { //Si la id no es nula
					Optional<Image> respuesta = imageRepository.findById(id); //Obtengo un objeto buscado por id desde el repositorio, si no, devuelve null
					if(respuesta.isPresent()) { //Si la respuesta no es nula
						image = respuesta.get(); //El objeto Image ahora es igual a la Image encontrada por id
					}
				}
				
				//Setteo sus atributos
				image.setMime(file.getContentType());
				image.setName(file.getName());
				image.setContent(file.getBytes());
				
				return imageRepository.save(image);//Devuelvo la imágen que guardo
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return null; //Si es nula retorna nulo
	}
	
	
	public void deleteImage(String id) {
		Image image = imageRepository.getById(id);

		imageRepository.delete(image);
	}
	
}
