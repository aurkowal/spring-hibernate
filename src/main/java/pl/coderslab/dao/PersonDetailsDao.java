package pl.coderslab.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.PersonDetails;
import pl.coderslab.entity.Publisher;

@Repository
@Transactional
public class PersonDetailsDao {
    @PersistenceContext

    private EntityManager entityManager;

    public PersonDetails findById(long id) {
        return entityManager.find(PersonDetails.class, id);
    }

    public void save(PersonDetails personDetails) {
        entityManager.persist(personDetails);
    }

    public void update(PersonDetails personDetails) {
        entityManager.merge(personDetails);
    }

    public void delete(PersonDetails personDetails) {
        entityManager.remove(entityManager.contains(personDetails) ? personDetails : entityManager.merge(personDetails));
    }
}
