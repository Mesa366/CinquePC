package com.cinque.pc.Entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

@Entity
public class Image {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String mime;
	private String name;

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
