package br.com.neo.token.response;


import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.neo.token.request.RequestError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApi {
    
    @JsonProperty("id")
    private String id;
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("errors")
    private ArrayList<RequestError> errors;
}
