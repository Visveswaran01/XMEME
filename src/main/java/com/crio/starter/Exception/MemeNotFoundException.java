package com.crio.starter.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Meme doesn't exist for the id")
public class MemeNotFoundException extends RuntimeException {
    
}