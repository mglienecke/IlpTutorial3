package uk.ac.ed.inf.ilptutorial3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import uk.ac.ed.inf.ilptutorial3.controller.CoreRestController;
import uk.ac.ed.inf.ilptutorial3.data.Restaurant;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(
		// if with RANDOM_PORT, then the MVC environment / service is loaded / started automatically as well
		webEnvironment = WebEnvironment.RANDOM_PORT //,

		// webEnvironment = WebEnvironment.DEFINED_PORT,
		// properties = {
		//	"server.port=8080"
		// }
)
@AutoConfigureMockMvc
class IlpTutorial3RestServiceApplicationTests {
	@Autowired
	private CoreRestController coreRestController;

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Test
	void contextLoads() throws Exception {
		assertThat(coreRestController).isNotNull();
	}

	@Test
	void isAliveShouldReturnTrue() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/isAlive",
				String.class)).contains("true");
	}

	@Test
	void restaurantRequest() throws Exception {
		ResponseEntity<Restaurant[]> result = this.restTemplate.getForEntity("http://localhost:" + port + "/restaurants",
				Restaurant[].class);
		assertThat(result.getStatusCode() == HttpStatus.OK).isTrue();
		assertThat(result.getBody().length > 0).isTrue();
	}
}
