package tkba.team6.roomreservationsystem.db.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.Entity;

@Entity
@Data
public class Feedbacks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long reservation_id;
    private String attendances;
    private String meeting_notes;
}
    