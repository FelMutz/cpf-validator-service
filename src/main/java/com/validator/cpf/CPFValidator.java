package com.validator.cpf;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFValidator implements ConstraintValidator<CPF, String> {

    private boolean acceptFormatted;

    @Override
    public void initialize(CPF constraintAnnotation) {
        this.acceptFormatted = constraintAnnotation.acceptFormatted();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true; // deixe o @NotNull/@NotBlank cuidar se for obrigatório
        }

        String cpf = value;
        if (acceptFormatted) {
            cpf = cpf.replaceAll("[^0-9]", "");
        }

        if (cpf.length() != 11) return false;

        // rejeita sequências com todos os mesmos dígitos (ex: 11111111111)
        boolean allEqual = true;
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                allEqual = false;
                break;
            }
        }
        if (allEqual) return false;

        try {
            int[] digits = new int[11];
            for (int i = 0; i < 11; i++) digits[i] = Character.getNumericValue(cpf.charAt(i));

            // calcular primeiro dígito verificador
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += digits[i] * (10 - i);
            }
            int remainder = sum % 11;
            int firstCheck = (remainder < 2) ? 0 : 11 - remainder;

            if (digits[9] != firstCheck) return false;

            // calcular segundo dígito verificador
            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += digits[i] * (11 - i);
            }
            remainder = sum % 11;
            int secondCheck = (remainder < 2) ? 0 : 11 - remainder;

            return digits[10] == secondCheck;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}