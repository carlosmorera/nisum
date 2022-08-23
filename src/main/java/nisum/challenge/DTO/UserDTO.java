package nisum.challenge.DTO;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDTO implements Serializable {

    private Optional<UUID> id;
    private String name;
    private Optional<String> email;
    private Optional<String> password;
    private Optional<String> created;
    private Optional<String> modified;
    private Optional<String> lastLogin;
    private Optional<String> token;
    private Optional<Boolean> isActive;
    private List<PhoneDTO> phones;

    public Optional<UUID> getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Optional<String> getEmail() {
        return email;
    }

    public Optional<String> getPassword() {
        return password;
    }

    public Optional<String> getCreated() {
        return created;
    }

    public Optional<String> getModified() {
        return modified;
    }

    public Optional<String> getLastLogin() {
        return lastLogin;
    }

    public Optional<String> getToken() {
        return token;
    }

    public Optional<Boolean> getIsActive() {
        return isActive;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setId(Optional<UUID> id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(Optional<String> email) {
        this.email = email;
    }

    public void setPassword(Optional<String> password) {
        this.password = password;
    }

    public void setCreated(Optional<String> created) {
        this.created = created;
    }

    public void setModified(Optional<String> modified) {
        this.modified = modified;
    }

    public void setLastLogin(Optional<String> lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setToken(Optional<String> token) {
        this.token = token;
    }

    public void setIsActive(Optional<Boolean> isActive) {
        this.isActive = isActive;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }
}
