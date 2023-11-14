package com.UserActivities01;


public class Result {
	

	private boolean flag; // Two values: true means success, false means not success

	private Integer code; // Status code. e.g., 200

	private String message; // Response message

	private Object data; // The response payload

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Result(boolean flag, Integer code, String message, Object data) {
		super();
		this.flag = flag;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Result() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Result [flag=" + flag + ", code=" + code + ", message=" + message + ", data=" + data + "]";
	}

}