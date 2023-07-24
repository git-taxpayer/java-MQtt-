package com.example.subjson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class SubjsonApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testConnection() {
        try {
            // 执行一个简单的查询语句
            String sql = "SELECT 1";
            jdbcTemplate.queryForObject(sql, Integer.class);

            // 如果没有抛出异常，则连接成功
            System.out.println("MySQL connection is successful.");
        } catch (Exception e) {
            // 连接失败，打印错误信息
            System.err.println("Failed to connect to MySQL: " + e.getMessage());
        }
    }
}
