package nisum.challenge.DTO;

import java.io.Serializable;
import java.util.Optional ;
import java.util.UUID;

public class PhoneDTO implements Serializable {
    private String number;
    private String cityCode;
    private String countryCode;
    private Optional<UUID> userId;

    public String getNumber() {
        return number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Optional<UUID> getUserId() {
        return userId;
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

    public void setUserId(Optional<UUID> userId) {
        this.userId = userId;
    }
}
