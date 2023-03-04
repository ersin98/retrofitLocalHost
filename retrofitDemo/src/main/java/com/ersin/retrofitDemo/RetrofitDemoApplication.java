package com.ersin.retrofitDemo;

import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ersin.retrofitDemo.business.core.utilities.exceptions.BusinessException;
import com.ersin.retrofitDemo.business.core.utilities.exceptions.ProblemDetails;
import com.ersin.retrofitDemo.business.core.utilities.exceptions.ValidationProblemDetails;

@SpringBootApplication
public class RetrofitDemoApplication {
//lombok kurulacak,doğru spring eklemeleri kullnaılacak,pgadmin kurulacak,application.properties yazılacak
	// etitie kısmındaki import lar persistence üzerinden olacak
	// veritabanını otomatik oluşturmaz kendin aç sonra otomatik içi dolsun
	public static void main(String[] args) {
		SpringApplication.run(RetrofitDemoApplication.class, args);
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBusinessException(BusinessException businessException) {
		ProblemDetails problemDetails = new ProblemDetails();
		problemDetails.setMessage(businessException.getMessage());
		return problemDetails;

	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
		ValidationProblemDetails validationproblemDetails = new ValidationProblemDetails();
		validationproblemDetails.setMessage(methodArgumentNotValidException.getMessage());

		validationproblemDetails.setValidationErrors(new HashMap<String, String>());// HashMap<String, String>()
		for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
			validationproblemDetails.getValidationErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return validationproblemDetails;

	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
