package com.validator.cpf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpfValidatorResponse {
    private CpfStatusEnum status;
}
