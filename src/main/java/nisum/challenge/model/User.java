package nisum.challenge.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Calendar;
import java.util.UUID;

@Entity
@Table(name="user")

public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    private UUID id;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created")
    private Calendar created;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified")
    private Calendar modified;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_login")
    private Calendar lastLogin;
    @Column(name="active")
    private Boolean isActive;

    public User() {
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User( String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Calendar getCreated() {
        return created;
    }

    public Calendar getModified() {
        return modified;
    }

    public Calendar getLastLogin() {
        return lastLogin;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public void setModified(Calendar modified) {
        this.modified = modified;
    }

    public void setLastLogin(Calendar lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
