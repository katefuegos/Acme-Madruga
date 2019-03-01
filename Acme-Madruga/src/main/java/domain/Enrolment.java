
package domain;

import java.util.Date;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Enrolment extends DomainEntity {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private Date	momentEnrol;
	private Map<String,String> position;
	private Date	momentDropOut;
	private boolean accepted;

	@NotNull
	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
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

	@NotEmpty
	@ElementCollection
	public Map<String, String> getPosition() {
		return position;
	}

	public void setPosition(Map<String, String> position) {
		this.position = position;
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
