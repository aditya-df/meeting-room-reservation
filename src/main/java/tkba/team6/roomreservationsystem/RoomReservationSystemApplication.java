package tkba.team6.roomreservationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RoomReservationSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(RoomReservationSystemApplication.class, args);
	}
}
