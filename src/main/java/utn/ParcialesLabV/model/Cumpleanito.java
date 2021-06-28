package utn.ParcialesLabV.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.util.Set;
@Entity
@Data
@NoArgsConstructor
@Table(name="cumpleanitos")
public class Cumpleanito {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @FutureOrPresent(message = "el cumpleanito no puede ser en el pasado")
        private LocalDate fecha;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "id")
        private Persona cumpleaniero;

        @OneToMany(fetch = FetchType.LAZY)
        private Set<Persona> invitados;


        public void setInvitados(Set<Persona> invitados) {
            this.invitados = invitados;
        }
}
