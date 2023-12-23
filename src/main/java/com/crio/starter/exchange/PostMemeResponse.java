package com.crio.starter.exchange;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;

/* Class  produces the following like JSON as response during serialization 
    for the post request 
    {
        "id": "1"
    }
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonIgnoreType
@AllArgsConstructor
@Data
@NoArgsConstructor
public class PostMemeResponse {

    String id;

}