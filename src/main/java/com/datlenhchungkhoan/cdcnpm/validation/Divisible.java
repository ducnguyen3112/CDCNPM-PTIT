package com.datlenhchungkhoan.cdcnpm.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = DivisibleContraintValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Divisible {
	public float value() default 10f;
	public String message() default "Số lượng phải là bội của 10!";
	public Class<?>[] groups() default {};
	public Class<? extends Payload>[] payload() default {};
}
