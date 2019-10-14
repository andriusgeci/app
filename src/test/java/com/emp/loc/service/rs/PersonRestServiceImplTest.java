package com.emp.loc.service.rs;

import com.emploc.model.Person;
import com.emploc.model.person.Personroot;
import com.emploc.service.PersonService;
import com.emploc.service.rs.PersonRestService;
import com.emploc.service.rs.PersonRestServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import javax.validation.Validator;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonRestServiceImplTest {

    @Mock
    private PersonService personService = mock(PersonService.class);
    private Person person;
    private String pClockCardNo;
    private PersonRestService personRestService;
    @Mock
    private Validator validator = mock(Validator.class);

    @Before
    public void setup() {
        person = new Person();
        pClockCardNo = "123";
        person.setPClockCardNo("123");
        person.setpName("test");
        person.setpSurname("test");
        person.setpFloor(Personroot.PFloor.GROUNDFLOOR);
        person.setpSeatNo(2);
        person.setpTitle("test");
        person.setpDepartment("test");
        person.setpCompany("test");

        when(personService.savePerson(any(Person.class))).thenAnswer((Answer<Person>) invocation -> {
            final Person obj = invocation.getArgument(0);
            return obj;
        });

        when(personService.saveOld(any(Person.class))).thenAnswer((Answer<Person>) invocation -> {
            final Person obj = invocation.getArgument(0);
            return obj;
        });

        personRestService = new PersonRestServiceImpl(personService, validator);
    }

    @Test
    public void personGetTest() {
        when(personService.getPersonById(pClockCardNo)).thenReturn(person);
        final Response response = personRestService.getPerson(pClockCardNo);

        assertThat(response.getStatus()).isEqualTo(Response.ok().build().getStatus());
        assertThat(response.getEntity()).isNotNull();
        final Person person = (Person) response.getEntity();
        assertThat(person).isNotNull();
        assertThat(person.getPClockCardNo()).isNotNull();
    }

    @Test
    public void personCreateTest() {
        final Response response = personRestService.createPerson(person);
        assertThat(response.getStatus()).isEqualTo(Response.ok().build().getStatus());
        assertThat(response.getEntity()).isEqualTo(person);

        final Person person = (Person) response.getEntity();
        assertThat(person).isNotNull();
        assertThat(person.getPClockCardNo()).isNotNull();
    }

    @Test
    public void personUpdateTest() {
        final Response response = personRestService.updatePerson(pClockCardNo, person);
        assertThat(response.getStatus()).isEqualTo(Response.ok().build().getStatus());
        assertThat(response.getEntity()).isNotNull();

        final Person person = (Person) response.getEntity();
        assertThat(person).isNotNull();
        assertThat(person.getPClockCardNo()).isNotNull();
    }

    @Test
    public void personDeleteTest() {
        when(personService.getPersonById(pClockCardNo)).thenReturn(person);
        final Response response = personRestService.deletePerson(pClockCardNo);
        assertThat(response.getStatus()).isEqualTo(Response.ok().build().getStatus());
        assertThat(response.getEntity()).isNotNull();
    }
}
