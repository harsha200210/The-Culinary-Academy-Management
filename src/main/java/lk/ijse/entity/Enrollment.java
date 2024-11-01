package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollId;
    private double firstInstallment;

    @ManyToOne
    @JoinColumn(name = "student_id",foreignKey = @ForeignKey(name = "FK_STUDENT"))
    private Student student;

    @ManyToOne
    @JoinColumn(name = "program_id",foreignKey = @ForeignKey(name = "FK_PROGRAM"))
    private CulinaryProgram program;
}
