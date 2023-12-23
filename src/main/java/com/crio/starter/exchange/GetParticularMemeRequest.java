package com.crio.starter.exchange;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.AllArgsConstructor;

/* This class should be able to deserialize the data send by get request
    GET 'http://<Server_URL>/memes/<id>' */

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetParticularMemeRequest {
    @NonNull
    private String id;
}