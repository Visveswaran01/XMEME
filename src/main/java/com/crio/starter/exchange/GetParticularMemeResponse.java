package com.crio.starter.exchange;

import com.crio.starter.dto.Meme;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/* Class  produces the following like JSON as response during serialization 
    for the post request 
    {
        "id": "1",       
        "name": "MS Dhoni",
        "url": "https://images.pexels.com/photos/3573382/pexels-photo-3573382.jpeg",
        "caption": "Meme for my place"
    }
*/


@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetParticularMemeResponse {

    private String id;
    private String name;
    private String url;
    private String caption;
    
}