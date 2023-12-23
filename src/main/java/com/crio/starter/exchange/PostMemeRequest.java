package com.crio.starter.exchange;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.AllArgsConstructor;

/* This class should be able to deserialize the data send by post request

curl --location --request POST 'http://<Server_URL>/memes/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "ashok kumar",
    "url": "https://images.pexels.com/photos/3573382/pexels-photo-3573382.jpeg",
    "caption": "This is a meme"
} */

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PostMemeRequest {

    @NonNull
    private String name;

    @NonNull
    private String caption;

    @NonNull
    private String url;
    
}