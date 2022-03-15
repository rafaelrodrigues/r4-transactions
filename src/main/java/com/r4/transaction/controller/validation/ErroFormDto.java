package com.r4.transaction.controller.validation;

public class ErroFormDto {
	
	private String field;
	private String erro;
	
	public ErroFormDto(String field, String erro) {
		this.field = field;
		this.erro = erro;
	}

	public String getCampo() {
		return field;
	}

	public String getErro() {
		return erro;
	}
	
	

}
