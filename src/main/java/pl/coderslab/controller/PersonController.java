package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.PersonDao;
import pl.coderslab.dao.PersonDetailsDao;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Person;
import pl.coderslab.entity.PersonDetails;

@Controller
public class PersonController {
    private final PersonDao personDao;
    private final PersonDetailsDao personDetailsDao;

    public PersonController(PersonDao personDao, PersonDetailsDao personDetailsDao) {
        this.personDao = personDao;
        this.personDetailsDao = personDetailsDao;
    }

    @GetMapping("/person/add/{login}/{password}/{email}")
    @ResponseBody
    public String add(@PathVariable("login") String login, @PathVariable("password") String password, @PathVariable("email") String email) {
        Person person = new Person();
        PersonDetails details = new PersonDetails();
        person.setLogin(login);
        person.setEmail(email);
        person.setPassword(password);
        details.setFirstName("Kasia");
        details.setCity("Warszawa");
        details.setLastName("Nowak");
        details.setStreetNumber(11);
        details.setStreet("Kwiatowa");
        personDetailsDao.save(details);
        person.setDetails(details);
        personDao.save(person);
        return "Dodano osobe o id: " + person.getId();
    }
    @RequestMapping("/person/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable("id") long id) {
        Person person = personDao.findById(id);
        return person.toString();
    }

    @RequestMapping("/person/update/{id}/{email}/{password}")
    @ResponseBody
    public String updateBook(@PathVariable("id") long id, @PathVariable("email") String email, @PathVariable("password") String pass) {
        Person person = personDao.findById(id);
        person.setEmail(email);
        person.setPassword(pass);
        personDao.update(person);
        return person.toString();

    }

    @RequestMapping("/person/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable("id") long id) {
        Person person = personDao.findById(id);
        personDao.delete(person);
        return "usunieto";
    }
}
