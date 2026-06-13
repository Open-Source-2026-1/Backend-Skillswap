package pe.edu.upc.skillswap.platform.skillswap_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SkillswapPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillswapPlatformApplication.class, args);
	}

}
