package com.crio.starter.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Data is not present in request")
public class NullPostException  extends RuntimeException{
    
}