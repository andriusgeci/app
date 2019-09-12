package com.emploc.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@Document(collection = "persons")
@JsonInclude(NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "pclockCardNo",
        "pname",
        "psurname",
        "pcompany",
        "pfloor",
        "pdepartment",
        "pseatNo"
})
public class Person<T> implements Serializable {
    private static final long serialVersionUID = -4377695081859768454L;


    @Id
    private String pClockCardNo;
    private T personroot;
   /* private String pName;
    private String pSurname;
    private String pCompany;
    private FloorNumber pFloor;
    private String pDepartment;
    private int pSeatNo;*/

}
