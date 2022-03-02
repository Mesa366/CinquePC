package com.cinque.pc.Entities;

/* TODO traducir al ingles
 * 	corregir detalles
 */

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;

/**
 * Entidad de Imagenes. Persistencia de imagenes para cada producto. Tiene 2
 * fines. Foto de perfil y foto de producto
 *
 * @author Martín Pavesio
 */

// TODO investigar si debemos usar implements Serializable
@Entity
public class Image {

	/**
	 * Estrategia seleccionada GeneratedValue por defecto. Si falla, usa
	 * GenericGenerator. Garantiza un ID único e irrepetible rápidamente.
	 * 
	 * param id   UUID autogenerada
	 * param mime es el tipo de archivo de imagen
	 * param name es el nombre del archivo
	 */
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
        
        
	private String mime;
	private String name;

	/**
	 * Large Object Annotation. Habilita uso de @Basic para especificar el tipo de
	 * Fetch. Basic especifica prioridad de carga. con Lazy prioriza carga de
	 * página, luego de imagenes.
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

}
