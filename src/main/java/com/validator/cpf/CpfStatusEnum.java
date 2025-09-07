package com.validator.cpf;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CpfStatusEnum {
    ABLE_TO_VOTE,
    UNABLE_TO_VOTE;
}

