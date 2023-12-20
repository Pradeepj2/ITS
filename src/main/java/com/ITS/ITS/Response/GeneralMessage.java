package com.ITS.ITS.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.SuperBuilder;

//@Data
//@SuperBuilder
//@NoArgsConstructor
//@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralMessage<T> {

	@JsonProperty("msq")
	private String msg;

	@JsonProperty("data")
	private T data;

	@JsonProperty("flag")
	private int flag;

	@JsonProperty("status_code")
	private int status_code;

	public GeneralMessage(String msg, int flag) {
		this.msg = msg;
		this.flag = flag;
	}

	public GeneralMessage(String msg, int flag, int status_code) {

		this.msg = msg;
		this.flag = flag;
		this.status_code = status_code;
	}

	public GeneralMessage(String msg, T data, int flag, int status_code) {
		this.msg = msg;
		this.data = data;
		this.flag = flag;
		this.status_code = status_code;
	}

}
