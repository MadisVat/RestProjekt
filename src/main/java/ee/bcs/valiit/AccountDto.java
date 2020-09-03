package ee.bcs.valiit;

import java.math.BigDecimal;
import java.math.BigInteger;

public class AccountDto {
    private String accountNo;
    private BigInteger id;
    private BigDecimal balance;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
/*
package ee.bcs.valiit;

        import java.math.BigInteger;

public class AccountDto {
    private String accountNo;
    private BigInteger id;
    private Integer balance;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}*/
