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
        pOne.setPClockCardNo("6");
        pOne.setpName("test");
        pOne.setpSurname("test");
        pOne.setpCompany("test");
        pOne.setpDepartment("test");
        pOne.setpTitle("test");
        pOne.setpSeatNo(1);
        pOne.setpFloor(GROUNDFLOOR);
        Person pTwo = new Person();
        pTwo.setPClockCardNo("7");
        pTwo.setpName("test");
        pTwo.setpSurname("test");
        pTwo.setpCompany("test");
        pTwo.setpDepartment("test");
        pTwo.setpTitle("test");
        pTwo.setpSeatNo(2);
        pTwo.setpFloor(GROUNDFLOOR);

        created.items(Arrays.asList(pOne, pTwo));

        final ListableResponse read = objectMapper.readValue(
                new InputStreamReader(getClass().getResourceAsStream("/dummyjson/personListResponse.json")), new TypeReference<ListableResponse<Person>>() {
                });

        assertThat(created).isEqualTo(read);
    }
}
