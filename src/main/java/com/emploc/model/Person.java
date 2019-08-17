package com.emploc.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person implements Serializable {

    private int pId;
    private int pSeatNo;
    private String pName;
    private String pSurename;
    private String pDepartment;
    private String pFloor;
    private String pLiveSupportNo;
}
