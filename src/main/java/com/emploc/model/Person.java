package com.emploc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "pId",
        "pSeatNo",
        "pName",
        "pSurename",
        "pDepartment",
        "pFloor",
        "pLiveSupportNo"
})
public class Person implements Serializable {

    private int pId;
    private int pSeatNo;
    private String pName;
    private String pSurename;
    private String pDepartment;
    private String pFloor;
    private String pLiveSupportNo;
}
