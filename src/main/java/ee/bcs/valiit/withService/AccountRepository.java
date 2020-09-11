package ee.bcs.valiit.withService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
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
        String sql = "INSERT INTO bank (account_no, balance, client_id) VALUES (:account_no, :balance, :client_id)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_no", addAccount.getAccountNumber());
        paramMap.put("balance", addAccount.getBalance());
        paramMap.put("client_id", addAccount.getClientId());
        jdbcTemplate.update(sql, paramMap);
    }

    public Long createClient(AddClient addClient) {
        String sql = "INSERT INTO clients (first_name, last_name) VALUES (:first_name, :last_name)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", addClient.getFirstName());
        paramMap.put("last_name", addClient.getLastName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        return (Long) keyHolder.getKeys().get("id");
    }

    public Long getAccountIdByAccountNumber(String account) {
        String sql = "SELECT id FROM bank WHERE account_no = :account_no";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("account_no", account);
        return jdbcTemplate.queryForObject(sql, paramMap, Long.class);
    }

    public void logDeposit(Long toAccountId, BigDecimal amount) {
        String sql = "INSERT INTO transaction_history (to_account, sum) VALUES (:to_account, :sum)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("to_account", toAccountId);
        paramMap.put("sum", amount);
        jdbcTemplate.update(sql, paramMap);
    }

    public void logWithdraw(Long fromAccountId, BigDecimal amount) {
        String sql = "INSERT INTO transaction_history (from_account, sum) VALUES (:from_account, :sum)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("from_account", fromAccountId);
        paramMap.put("sum", amount);
        jdbcTemplate.update(sql, paramMap);
    }

    public void logTransfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        String sql = "INSERT INTO transaction_history (from_account, to_account, sum) VALUES (:from_account, :to_account, :sum)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("from_account", fromAccountId);
        paramMap.put("to_account", toAccountId);
        paramMap.put("sum", amount);
        jdbcTemplate.update(sql, paramMap);
    }

    // TODO delete account? client?
    // TODO print out account list(tabeli formaadis - NP kasuta loopi)
}
