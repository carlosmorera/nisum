package nisum.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class ChallengeApplication implements CommandLineRunner {
	@Autowired JdbcTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		template.execute("DROP TABLE USER IF EXISTS");
		template.execute("DROP TABLE PHONE IF EXISTS");
		template.execute("CREATE TABLE USER ( id VARCHAR(255) PRIMARY KEY, name VARCHAR(255), email VARCHAR(255), password VARCHAR(255), created DATE, modified DATE, last_login DATE, active BOOLEAN )");
		template.execute("CREATE TABLE PHONE ( number VARCHAR(255) PRIMARY KEY, cityCode VARCHAR(255), countryCode VARCHAR(255), userId VARCHAR(255) )");
	}
}
