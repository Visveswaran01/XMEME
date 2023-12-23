package com.crio.starter.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Meme Already exists")
public class DuplicatePostException extends RuntimeException{
    
}