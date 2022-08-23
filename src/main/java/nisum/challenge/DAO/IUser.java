package nisum.challenge.DAO;

import nisum.challenge.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUser {
    public List<User> getUsers();
    public Optional<User> gerUserByEmail(String email);
    public Optional<User>  add(User user);
    public Optional<Boolean> delete(UUID id);
    public Optional<User> update(User user);
}
