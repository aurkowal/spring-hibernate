package pl.coderslab.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Publisher;

import java.util.List;

@Repository
@Transactional
public class PublisherDao {
    @PersistenceContext

    private EntityManager entityManager;

    public Publisher findById(long id) {
        return entityManager.find(Publisher.class, id);
    }

    public void save(Publisher publisher) {
        entityManager.persist(publisher);
    }

    public void update(Publisher publisher) {
        entityManager.merge(publisher);
    }

    public void delete(Publisher publisher) {
        entityManager.remove(entityManager.contains(publisher) ? publisher : entityManager.merge(publisher));
    }

    public List<Publisher> findAll() {
        Query query = entityManager.createQuery("select p from Publisher p");
        List<Publisher> resultList = query.getResultList();
        return resultList;
    }
}
