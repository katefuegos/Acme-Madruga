package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Request extends Actor {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private String status;
	private Integer roow;
	private Integer coluumn;
	private String reasonReject;

	@NotBlank
	@Pattern(regexp = "^(PENDING|APPROVED|REJECTED)$")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getRoow() {
		return roow;
	}

	public void setRoow(Integer roow) {
		this.roow = roow;
	}

	public Integer getColuumn() {
		return coluumn;
	}

	public void setColuumn(Integer coluumn) {
		this.coluumn = coluumn;
	}

	public String getReasonReject() {
		return reasonReject;
	}

	public void setReasonReject(String reasonReject) {
		this.reasonReject = reasonReject;
	}

	// Relationships ---------------------------------------------------------
	private Member member;
	private Procession procession;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Procession getProcession() {
		return procession;
	}

	public void setProcession(Procession procession) {
		this.procession = procession;
	}
}
