package com.emp.loc.model;

import com.emploc.model.ListableResponse;
import com.emploc.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static com.emploc.model.person.Personroot.PFloor.GROUNDFLOOR;
import static org.assertj.core.api.Assertions.assertThat;

public class ListableResponseTest {

    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void personListCreate() throws IOException {
        final ListableResponse<Person> created = new ListableResponse<>();

        Person pOne = new Person();
        pOne.setPClockCardNo("1");
        pOne.setpName("nameOne");
        pOne.setpSurname("surnameOne");
        pOne.setpCompany("companyOne");
        pOne.setpDepartment("departmentOne");
        pOne.setpTitle("developerOne");
        pOne.setpSeatNo(1);
        pOne.setpFloor(GROUNDFLOOR);
        Person pTwo = new Person();
        pTwo.setPClockCardNo("2");
        pTwo.setpName("nameTwo");
        pTwo.setpSurname("surnameTwo");
        pTwo.setpCompany("companyTwo");
        pTwo.setpDepartment("departmentTwo");
        pTwo.setpTitle("developerTwo");
        pTwo.setpSeatNo(2);
        pTwo.setpFloor(GROUNDFLOOR);

        created.items(Arrays.asList(pOne, pTwo));

        final ListableResponse read = objectMapper.readValue(
                new InputStreamReader(getClass().getResourceAsStream("/dummyjson/personListResponse.json")), new TypeReference<ListableResponse<Person>>() {
                });

        assertThat(created).isEqualTo(read);
    }
}
