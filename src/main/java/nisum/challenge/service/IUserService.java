package nisum.challenge.service;

import nisum.challenge.DTO.UserDTO;
import nisum.challenge.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    public List<User> getUsers();

    public Optional<UserDTO> getUserByEmail(String email, String password) throws Exception;

    public Optional<UserDTO> getUserByEmailToUpdate(String email, String password) throws Exception;

    public  Optional<UserDTO>   add(UserDTO user) throws Exception;

    public Optional<UserDTO>  edit(UserDTO user, UserDTO oldUserDTO) ;

    public Optional<Boolean> delete(UUID id);
}
