package com.UserActivities01;

import java.time.LocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Aspect
@Component
public class LoggingAspect {

	private final ObjectMapper objectMapper = new ObjectMapper();

	
	private final LogRepository logRepository;

	@Autowired
	public LoggingAspect(LogRepository logRepository) {
		this.logRepository = logRepository;
	}

	@Pointcut("execution(* com.UserActivities01.SampleController.*(..))")
	private void controllerMethods() {
	}

	@Around("controllerMethods()")
	public Object logControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			// Extracting request details
			HttpServletRequest request = getRequest();
			String controllerClassName = joinPoint.getTarget().getClass().getSimpleName();
			String methodName = joinPoint.getSignature().getName();
			String endpoint = request.getRequestURI();
			String httpMethod = request.getMethod();
			LocalDateTime requestTime = LocalDateTime.now();

			// Proceed with the actual method execution
			Object result = joinPoint.proceed();

			// Extracting response details
			HttpServletResponse response = getResponse();
			String responseStatusCode = (response != null) ? Integer.toString(response.getStatus()) : "N/A";

			// Save log to the database
			saveLogToDatabase(controllerClassName, endpoint, getRequestURL(request), httpMethod, requestTime,
					responseStatusCode);

			// Log status code and method exit
			System.out.println("Status Code: " + responseStatusCode);
			System.out.println("Exiting " + controllerClassName + "." + methodName + " with result: " + result);

			return result;
		} catch (Exception e) {
			// Log any exceptions that might occur during method execution
			System.out.println("Status Code: 500"); // Assuming an error occurred for demonstration
			System.out.println("Exception in " + joinPoint.getTarget().getClass().getSimpleName() + "."
					+ joinPoint.getSignature().getName() + ": " + e.getMessage());
			throw e; // Re-throw the exception after logging
		}
	}

	private HttpServletRequest getRequest() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return (attributes != null) ? attributes.getRequest() : null;
	}

	private HttpServletResponse getResponse() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return (attributes != null) ? attributes.getResponse() : null;
	}

	private String getRequestURL(HttpServletRequest request) {
		return request.getRequestURL().toString();
	}

	private String formatLocalDateTime(LocalDateTime localDateTime) {
		return localDateTime.toString();
	}

	private String convertObjectToJson(Object object) throws JsonProcessingException {
		return objectMapper.writeValueAsString(object);
	}

	private void saveLogToDatabase(String controllerClassName, String endpoint, String completeURL, String httpMethod,
			LocalDateTime recorded, String responseStatusCode) {
		LogEntity logEntity = new LogEntity(controllerClassName, endpoint, completeURL, httpMethod, recorded, responseStatusCode);

		logRepository.save(logEntity);
	}
}
