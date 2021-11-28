package com.bbm.countries.exception.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ErrorObject {

	private String status;
	private OffsetDateTime time;
	private String title;
	private List<Campo> campos;

	public static class Campo {

		private String nomeCampo;
		private String message;

		public Campo(String nomeCampo, String message) {
			super();
			this.nomeCampo = nomeCampo;
			this.message = message;
		}

		public String getNomeCampo() {
			return nomeCampo;
		}

		public void setNomeCampo(String nomeCampo) {
			this.nomeCampo = nomeCampo;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OffsetDateTime getTime() {
		return time;
	}

	public void setTime(OffsetDateTime time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Campo> getCampos() {
		return campos;
	}

	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}

}
