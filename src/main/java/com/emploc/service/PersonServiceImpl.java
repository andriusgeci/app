package com.emploc.service;

import com.emploc.model.Person;
import com.emploc.repository.PersonRepository;
import com.emploc.utils.RsCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;

import static com.emploc.utils.AppConstants.NOT_FOUND_MSG;

@Service
public class PersonServiceImpl implements PersonService {


    private PersonRepository personRepository;
    private Validator validator;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, Validator validator) {

        this.personRepository = personRepository;
        this.validator = validator;
    }

    @Override
    public Person getPersonById(String pClockCardNo) {
        try {
            Optional<Person> opt = personRepository.findById(pClockCardNo);
            if (opt.isEmpty()) {
                throw new EntityNotFoundException("Entity with requested id: " + pClockCardNo + " not found");
            }
            return opt.get();
        } finally {
            System.out.println("finally PersonServiceImpl");
        }
    }

    @Override
    public Person savePerson(Person person) {
        personRepository.save(person);
        return person;
    }

    @Override
    public Person saveOld(Person person) {
        try {

            final Optional<Person> saved = personRepository.findById(person.getPClockCardNo());
            RsCheck.validate(person, validator, ".");
            if (saved.isEmpty()) {
                throw new EntityNotFoundException("Entity with requested id: " + person.getPClockCardNo() + " not found");
            }
            personRepository.save(person);
           /* final T saved = dao.findById(entity.getId());
        RsCheck.notNullBadRequest(saved, () -> "there is no record with such id");
        RsCheck.badRequest(!CmtStatus.PUBLISHED.equals(saved.getStatus()), () -> "attempt to edit published record, use draft copy please");
        RsCheck.badRequest(saved.getVersion().equals(entity.getVersion()), () -> "optimistic lock error: attempt to save changes based on old record");
        entity.setLastUpdated(new Date());
        entity.setLastUpdatedBy(xAegProfileId);
        entity.setStatus(entity.getStatus() == null ? CmtStatus.DRAFT : entity.getStatus());
        entity.setVersion(entity.getVersion() + 1);

        RsCheck.validate(entity, validator.getAnnotationValidator(), ".");
        return dao.update(entity);*/
            return person;
        }finally {

        }
        }

        @Override
        public List<Person> listPersonByName (String name){
            personRepository.findPersonByName(name);
            return null;
        }

    }
