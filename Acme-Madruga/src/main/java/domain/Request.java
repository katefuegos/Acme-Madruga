package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Request extends Actor {

	// Identification ---------------------------------------------------------
	// ATRIBUTOS
	private String status;
	private Integer row;
	private Integer column;
	private String reasonReject;

	@NotBlank
	@Pattern(regexp = "^(PENDING|APPROVED|REJECTED)$")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Range(min = 1)
	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	@Range(min = 1)
	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
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
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@NotNull
	@Valid
	public Procession getProcession() {
		return procession;
	}

	public void setProcession(Procession procession) {
		this.procession = procession;
	}
}
