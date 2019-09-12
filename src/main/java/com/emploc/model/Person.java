package com.emploc.model;


import com.emploc.model.person.Personroot;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/*@Getter
@Setter*/
/*@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)*/
/*@AllArgsConstructor
@NoArgsConstructor*/
@Document(collection = "persons")
/*@JsonInclude(NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "pclockCardNo",
        "pname",
        "psurname",
        "pcompany",
        "pfloor",
        "pdepartment",
        "pseatNo"
})*/
public class Person extends Personroot implements Serializable {

    private static final long serialVersionUID = -1023200559156535845L;

   /* @Id
    private String pClockCardNo;
    private String pName;
    private String pSurname;
    private String pCompany;
    private FloorNumber pFloor;
    private String pDepartment;
    private int pSeatNo;*/
}
