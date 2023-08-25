package br.com.neo.token.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestError {
    
    @JsonProperty("errorCode")
    private String errorCode;
    @JsonProperty("message")
    private String message;
}
