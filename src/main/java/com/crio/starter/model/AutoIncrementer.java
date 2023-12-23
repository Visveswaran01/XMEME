package com.crio.starter.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


//This class acts as Entity for  collection 'counter' which helps in generating sequenceNo 

@Data
@Document(collection = "counter")
@NoArgsConstructor
public class AutoIncrementer {
    
    @Id
    private String id;

    private long seqNo;
}