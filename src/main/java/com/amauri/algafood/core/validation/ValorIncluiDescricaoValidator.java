package com.amauri.algafood.core.validation;

import org.springframework.beans.BeanUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import java.math.BigDecimal;

public class ValorIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object> {

    private String valorField;
    private String descricaoField;
    private String descricaoObrigatoria;

    @Override
    public void initialize(ValorZeroIncluiDescricao constraintAnnotation) {
        this.valorField = constraintAnnotation.valorField();
        this.descricaoField = constraintAnnotation.descricaoField();
        this.descricaoObrigatoria = constraintAnnotation.descricaoObrigatoria();
    }

    @Override
    public boolean isValid(Object objetoValidacao, ConstraintValidatorContext constraintValidatorContext) {
        boolean valido = true;
        try {
            BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), valorField)
                    .getReadMethod().invoke(objetoValidacao);

            String descricao = (String) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), descricaoField)
                    .getReadMethod().invoke(objetoValidacao);

            if(valor != null && valor.compareTo(BigDecimal.ZERO) == 0 && descricao != null) {
                valido = descricao.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
            }
            return valido;
        } catch (Exception e) {
            throw new ValidationException(e);
        }

    }
}
