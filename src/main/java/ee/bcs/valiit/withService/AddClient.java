package ee.bcs.valiit.withService;

import java.math.BigDecimal;
import java.math.BigInteger;

public class AddClient {
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private BigInteger id;
    private String firstName;
    private String lastName;


}
