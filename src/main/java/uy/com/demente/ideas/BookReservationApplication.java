package uy.com.demente.ideas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// Indicamos que no queremos la clase por defecto de seguridad, 
// ya que utilizaremos nuestra propia implementacion
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BookReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookReservationApplication.class, args);
	}

}
