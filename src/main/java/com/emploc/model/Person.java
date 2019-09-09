package com.emploc.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "persons")
public class Person implements Serializable {

    private static final long serialVersionUID = -1023200559156535845L;

    @Id
    private String pClockCardNo;
    private int pSeatNo;
    private String pName;
    private String pSurename;
    private String pDepartment;
    private String pFloor;
    private String pCompany;
}
