package me.suhyuk.jdbc.config.clickhouse;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Validated
@ConfigurationProperties(prefix = "spring.clickhouse")
@Data
public class ClickhouseConfiguration implements InitializingBean {

    @NotEmpty
    private String driverClassName;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    private String password;

    @NotNull
    @NotEmpty
    private String jdbcUrl;

    @NotNull
    @NotEmpty
    @Value("${spring.clickhouse.hibernate.dialect}")
    private String hibernateDialect;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(username, "아이디는 널 혹은 빈 값일 수 없습니다");
    }
}
