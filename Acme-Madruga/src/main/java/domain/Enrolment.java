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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Enrolment extends Actor {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private Date momentEnrol;
	private String position;
	private Date momentDropOut;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	public Date getMomentEnrol() {
		return momentEnrol;
	}

	public void setMomentEnrol(Date momentEnrol) {
		this.momentEnrol = momentEnrol;
	}

	@NotBlank
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	public Date getMomentDropOut() {
		return momentDropOut;
	}

	public void setMomentDropOut(Date momentDropOut) {
		this.momentDropOut = momentDropOut;
	}

	// Relationships ---------------------------------------------------------
	private Brotherhood brotherhood;
	private Member member;

	@Valid
	@ManyToOne(optional = false)
	public Brotherhood getBrotherhood() {
		return brotherhood;
	}

	public void setBrotherhood(Brotherhood brotherhood) {
		this.brotherhood = brotherhood;
	}

	@Valid
	@ManyToOne(optional = false)
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
