package com.emploc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class PersonFilter implements Serializable {

    private static final long serialVersionUID = -989907408616727126L;

    @Size(max = 255)
    @Pattern(regexp = "^[\\p{L} .'-]+$")
    private String key;
    @Size(max = 255)
    @Pattern(regexp = "^[\\p{L} .'-]+$")
    private String value;
}
