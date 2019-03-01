
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
public class Enrolment extends DomainEntity {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private Date	momentEnrol;
	private String	positionEN;
	private String	positionES;
	private Date	momentDropOut;
	private boolean	accepted;


	@NotNull
	public boolean isAccepted() {
		return this.accepted;
	}

	public void setAccepted(final boolean accepted) {
		this.accepted = accepted;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	public Date getMomentEnrol() {
		return this.momentEnrol;
	}

	public void setMomentEnrol(final Date momentEnrol) {
		this.momentEnrol = momentEnrol;
	}

	@NotBlank
	public String getPositionEN() {
		return this.positionEN;
	}

	public void setPositionEN(final String position) {
		this.positionEN = position;
	}

	@NotBlank
	public String getPositionES() {
		return this.positionES;
	}

	public void setPositionES(final String position) {
		this.positionES = position;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	public Date getMomentDropOut() {
		return this.momentDropOut;
	}

	public void setMomentDropOut(final Date momentDropOut) {
		this.momentDropOut = momentDropOut;
	}


	// Relationships ---------------------------------------------------------
	private Brotherhood	brotherhood;


	@Valid
	@ManyToOne(optional = false)
	public Brotherhood getBrotherhood() {
		return this.brotherhood;
	}

	public void setBrotherhood(final Brotherhood brotherhood) {
		this.brotherhood = brotherhood;
	}
}
