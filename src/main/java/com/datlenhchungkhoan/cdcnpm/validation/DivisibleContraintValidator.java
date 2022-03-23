package com.datlenhchungkhoan.cdcnpm.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DivisibleContraintValidator implements ConstraintValidator<Divisible, String>{
	
	private float num;
	@Override
	public void initialize(Divisible constraintAnnotation) {
		num=constraintAnnotation.value();
	}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		float divisor=Float.parseFloat(value);
		if (divisor%num==0) {
			return true;
		}
		
		return false;
	}

}
