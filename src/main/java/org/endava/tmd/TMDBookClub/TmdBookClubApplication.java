package org.endava.tmd.TMDBookClub;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "TMD Book Club API",
				version = "1.0.0",
				description = "An application to manage the books sharing"
		))
public class TmdBookClubApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmdBookClubApplication.class, args);
	}

}
