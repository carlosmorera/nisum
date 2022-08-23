package nisum.challenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="phone")
public class Phone {
    @Id
    @Column(name="number")
    private String number;
    @Column(name="cityCode")
    private String cityCode;
    @Column(name="countryCode")
    private String countryCode;
    @Column(name="userId")
    private UUID userId;

    public Phone() {
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public UUID getUserId() {
        return userId;
    }
}
