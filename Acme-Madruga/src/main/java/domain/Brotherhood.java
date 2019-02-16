package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Brotherhood extends Actor {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private String title;
	private Date establishementDate;
	private String pictures;

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	public Date getEstablishementDate() {
		return establishementDate;
	}

	public void setEstablishementDate(Date establishementDate) {
		this.establishementDate = establishementDate;
	}

	@URL
	@NotBlank
	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	// Relationships ---------------------------------------------------------
	private Area area;

	@Valid
	@ManyToOne(optional = true)
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

}
