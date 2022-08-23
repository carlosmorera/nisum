package nisum.challenge.DTO;

import nisum.challenge.model.Phone;
import nisum.challenge.model.User;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

public final class MapperUtility {

    public static PhoneDTO buildPhoneDtoFromModel(Phone phone) {
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setNumber(phone.getNumber());
        phoneDTO.setCityCode(phone.getCityCode());
        phoneDTO.setCountryCode(phone.getCountryCode());
        phoneDTO.setUserId(Optional.of(phone.getUserId()));
        return phoneDTO;
    }

    public static Phone buildPhoneFromDto(PhoneDTO phoneDTO, UUID uuid) {
        Phone phone = new Phone();
        phone.setNumber(phoneDTO.getNumber());
        phone.setCityCode(phoneDTO.getCityCode());
        phone.setCountryCode(phoneDTO.getCountryCode());
        phone.setUserId(uuid);
        return phone;
    }

    public static User buildUserFromDto(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail().orElseGet(() -> ""));
        user.setPassword(userDTO.getPassword().orElseGet(() -> ""));
        user.setCreated(Calendar.getInstance());
        user.setModified(Calendar.getInstance());
        user.setLastLogin(Calendar.getInstance());
        user.setIsActive(true);
        return user;
    }
    public static User updatedUserFromDto(UserDTO newUserDTO, UserDTO oldUserDTO) {
        User user = new User();
        user.setId(oldUserDTO.getId().get());
        user.setName(newUserDTO.getName());
        user.setEmail(oldUserDTO.getEmail().orElseGet(()->""));
        user.setPassword(oldUserDTO.getPassword().orElseGet(()->""));
        user.setCreated(Calendar.getInstance());
        user.setModified(Calendar.getInstance());
        user.setLastLogin(Calendar.getInstance());
        user.setIsActive(true);
        return user;
    }

    public static UserDTO buildUserDtoFromModel(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(Optional.of(user.getId()));
        userDTO.setCreated(Optional.of(user.getCreated().getTime().toString()));
        userDTO.setModified(Optional.of(user.getModified().getTime().toString()));
        userDTO.setLastLogin(Optional.of(user.getLastLogin().getTime().toString()));
        userDTO.setIsActive(Optional.of(user.getIsActive()));
        return userDTO;
    }

    public static UserDTO buildUserDtoFromModelToUpdate(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(Optional.of(user.getId()));
        userDTO.setEmail(Optional.of(user.getEmail()));
        userDTO.setName(user.getName());
        userDTO.setPassword(Optional.of(user.getPassword()));
        userDTO.setCreated(Optional.of(user.getCreated().getTime().toString()));
        userDTO.setModified(Optional.of(user.getModified().getTime().toString()));
        userDTO.setLastLogin(Optional.of(user.getLastLogin().getTime().toString()));
        userDTO.setIsActive(Optional.of(user.getIsActive()));
        return userDTO;
    }
}
