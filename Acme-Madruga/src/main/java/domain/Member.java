package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Member extends Actor {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS

	// Relationships ---------------------------------------------------------
	private Finder finder;

	public Finder getFinder() {
		return finder;
	}
	
	@Valid
	@OneToOne(optional=true)
	public void setFinder(Finder finder) {
		this.finder = finder;
	}

}
