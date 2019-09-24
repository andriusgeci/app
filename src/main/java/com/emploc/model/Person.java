package com.emploc.model;

import com.emploc.model.person.Personroot;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Document(collection = "persons")
@JsonInclude(NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "pclockCardNo",
        "pname",
        "psurname",
        "ptitle",
        "pcompany",
        "pfloor",
        "pdepartment",
        "pseatNo"
})
public class Person extends Personroot implements Serializable {

    private static final long serialVersionUID = -4377695081859768454L;

    @Id
    @Pattern(regexp = "^[0-9]+$")
    @Max(255)
    @Min(0)
    private String pClockCardNo;
}