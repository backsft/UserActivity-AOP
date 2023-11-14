package com.UserActivities01;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "api_logs")
public class LogEntity {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "controller_class_name")
	private String controllerClassName;

	@Column(name = "api_endpoint")
	private String apiEndpoint;

	@Column(name = "complete_url")
	private String completeURL;

	@Column(name = "http_method")
	private String httpMethod;

	@Column(name = "recorded")
	@CreationTimestamp // Automatically set by Hibernate
	private LocalDateTime recorded;

	@Column(name = "response_status_code")
	private String responseStatusCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getControllerClassName() {
		return controllerClassName;
	}

	public void setControllerClassName(String controllerClassName) {
		this.controllerClassName = controllerClassName;
	}

	public String getApiEndpoint() {
		return apiEndpoint;
	}

	public void setApiEndpoint(String apiEndpoint) {
		this.apiEndpoint = apiEndpoint;
	}

	public String getCompleteURL() {
		return completeURL;
	}

	public void setCompleteURL(String completeURL) {
		this.completeURL = completeURL;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public LocalDateTime getRecorded() {
		return recorded;
	}

	public void setRecorded(LocalDateTime recorded) {
		this.recorded = recorded;
	}

	public String getResponseStatusCode() {
		return responseStatusCode;
	}

	public void setResponseStatusCode(String responseStatusCode) {
		this.responseStatusCode = responseStatusCode;
	}


	
	

	public LogEntity(String controllerClassName, String apiEndpoint, String completeURL, String httpMethod,
			LocalDateTime recorded, String responseStatusCode) {
		super();
		this.controllerClassName = controllerClassName;
		this.apiEndpoint = apiEndpoint;
		this.completeURL = completeURL;
		this.httpMethod = httpMethod;
		this.recorded = recorded;
		this.responseStatusCode = responseStatusCode;
	}

	public LogEntity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "LogEntity [id=" + id + ", controllerClassName=" + controllerClassName + ", apiEndpoint=" + apiEndpoint
				+ ", completeURL=" + completeURL + ", httpMethod=" + httpMethod + ", recorded=" + recorded
				+ ", responseStatusCode=" + responseStatusCode + "]";
	}

}
