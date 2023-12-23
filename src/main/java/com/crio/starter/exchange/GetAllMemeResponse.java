package com.crio.starter.exchange;

import com.crio.starter.dto.Meme;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonIgnoreType
@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetAllMemeResponse {
    private String id;
    private String name;
    private String url;
    private String caption;
}