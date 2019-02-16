package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class SocialProfile extends DomainEntity {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private String nick;
	private String nameSocialNetwork;
	private String linkSocialNetwork;

	@NotBlank
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@NotBlank
	public String getNameSocialNetwork() {
		return nameSocialNetwork;
	}

	public void setNameSocialNetwork(String nameSocialNetwork) {
		this.nameSocialNetwork = nameSocialNetwork;
	}

	@NotBlank
	@URL
	public String getLinkSocialNetwork() {
		return linkSocialNetwork;
	}

	public void setLinkSocialNetwork(String linkSocialNetwork) {
		this.linkSocialNetwork = linkSocialNetwork;
	}

	// Relationships ---------------------------------------------------------
	private Actor actor;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

}
