package nisum.challenge.DAO;

import nisum.challenge.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserDAO implements IUser {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> theQuery = currentSession.createQuery(" FROM User ", User.class);
        List<User> users = theQuery.getResultList();
        return users;
    }

    @Override
    public Optional<User> gerUserByEmail(String email) {
       return this.getUsers().stream()
               .filter(customer -> email.equals(customer.getEmail()))
               .findAny();
    }

    @Override
    public Optional<User> add(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(user);
        return Optional.of(user);
    }

    @Override
    public Optional<Boolean> delete(UUID id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> theQuery = currentSession.createQuery("delete User where id=:id");
        theQuery.setParameter("id", id);
        int responseDb = theQuery.executeUpdate();
        boolean deleted = (responseDb != 0);
        return Optional.of(deleted);
    }

    @Override
    public Optional<User> update(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(user);
        return Optional.of(user);
    }
}
