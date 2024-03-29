
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("br.com.apigetway")
public class ApplicationServer {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationServer.class, args);

        }

    }



