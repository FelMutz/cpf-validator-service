package com.validator.cpf;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.constraints.NotBlank;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping()
@Validated
public class CpfValidatorController {

    @GetMapping("{cpf}")
    public CpfValidatorResponse validateCpf(@PathVariable
                                            @NotBlank(message = "CPF é obrigatório")
                                            @CPF(acceptFormatted = true) String cpf) {
        CpfStatusEnum status = ThreadLocalRandom.current()
                .nextBoolean() ? CpfStatusEnum.ABLE_TO_VOTE : CpfStatusEnum.UNABLE_TO_VOTE;

        return new CpfValidatorResponse(status);
    }
}
