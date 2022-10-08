package com.fazz.fazzconnect.gateway;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AnPostUser {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}
