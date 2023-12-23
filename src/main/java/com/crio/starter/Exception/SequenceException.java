package com.crio.starter.Exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Limit of sequence is exceeded.")
public class SequenceException extends RuntimeException {

    private static final long serialVersionUID = 3099952440277685669L;
    
}