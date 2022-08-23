package nisum.challenge.service;

import nisum.challenge.DAO.IUser;
import nisum.challenge.DTO.MapperUtility;
import nisum.challenge.DTO.UserDTO;
import nisum.challenge.model.User;
import nisum.challenge.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService implements IUserService {

    @Value("${password.regex}")
    private String regex;

    @Autowired
    private IUser Iuser;

    @Override
    public List<User> getUsers() {
        List<User> listPersons = Iuser.getUsers();
        return listPersons;
    }

    @Override
    public Optional<UserDTO> getUserByEmail(String email, String password) throws Exception{

        Optional<User> userFound = Iuser.gerUserByEmail(email);
        Boolean validPassword = userFound.map(user -> password.equals(user.getPassword())).orElseGet(() -> false);
        if (validPassword) {
            return userFound.map(MapperUtility::buildUserDtoFromModel);
        } else {
            throw new Exception("email or password are not valid");
        }
    }
    @Override
    public Optional<UserDTO> getUserByEmailToUpdate(String email, String password) throws Exception{

        Optional<User> userFound = Iuser.gerUserByEmail(email);
        Boolean validPassword = userFound.map(user -> password.equals(user.getPassword())).orElseGet(() -> false);
        if (validPassword) {
            return userFound.map(MapperUtility::buildUserDtoFromModelToUpdate);
        } else {
            throw new Exception("email or password are not valid");
        }
    }

    @Override
    public Optional<UserDTO>  add(UserDTO userDTO) throws Exception {
        User user = MapperUtility.buildUserFromDto(userDTO);
        boolean mailValidated = Utility.validateEmailStructure(user.getEmail());
        boolean passwordValidated = Utility.validatePasswordStructure(user.getPassword(), regex);
        if (mailValidated && passwordValidated) {
           return Iuser.add(user).map(MapperUtility::buildUserDtoFromModel);
        } else {
            throw new Exception("email or password are not valid");
        }
    }

    @Override
    public Optional<UserDTO> edit(UserDTO userDTO, UserDTO oldUserDTO) {
        User user = MapperUtility.updatedUserFromDto(userDTO, oldUserDTO);
        return Iuser.update(user).map(MapperUtility::buildUserDtoFromModel);
    }

    @Override
    public Optional<Boolean>  delete(UUID id) {
        return Iuser.delete(id);
    }
}
