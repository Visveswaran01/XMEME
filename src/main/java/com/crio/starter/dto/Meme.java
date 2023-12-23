package com.crio.starter.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* Class that produces following like JSON during serialization for response
  {
    
    "id": "1",        
    "name": "MS Dhoni",
    "caption": "Meme for my place"
    "url": "https://images.pexels.com/photos/3573382/pexels-photo-3573382.jpeg",
    
  }
*/

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Meme {
    
    private String memeid;
    @NotNull
    private String personName;
    @NotNull
    private String imageUrl;
    @NotNull
    private String memeCaption;
    
    
}