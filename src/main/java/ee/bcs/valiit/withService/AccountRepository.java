package ee.bcs.valiit.withService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccountRepository {


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public BigDecimal getBalance(String fromAccount) {
        String sql = "SELECT balance FROM bank WHERE account_no = :account_no";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("account_no", fromAccount);
        return jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
    }

    public void updateBalance(String fromAccount, BigDecimal amount) {
        String sql = "UPDATE bank SET balance = :balance WHERE account_no = :account_no";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_no", fromAccount);
        paramMap.put("balance", amount);
        jdbcTemplate.update(sql, paramMap);
    }

    public void createAccount(AddAccount addAccount) {
        String sql = "INSERT INTO bank (account_no, balance, id) VALUES (:account_no, :balance, :id)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_no", addAccount.getAccountNumber());
        paramMap.put("balance", addAccount.getBalance());
        paramMap.put("id", addAccount.getId());
        jdbcTemplate.update(sql, paramMap);
    }

}
