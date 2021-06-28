package utn.ParcialesLabV.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity  //sera una clase de la cual se creara una persistencia
//@DiscriminatorValue("true")

public class Jugador extends  Persona{
    @NotNull(message = "el peso tiene que ser positivo")
    private String peso;
    @NotNull(message = "La altura tiene que ser positiva")
    private String altura;
    @PositiveOrZero(message = "El valor solo puede ser positivo o cero")
    private Integer goles;
    @PositiveOrZero(message = "El valor solo puede ser positivo o cero")
    private Double minutosJugados;
    @Past(message = "la fecha de nacimiento tiene que ser pasada")
    private Date fechaNacimiento;

    @OneToOne(cascade = CascadeType.ALL)
    private Currency currency;

    @Override

    public TypePerson typePerson() {
        return TypePerson.JUGADOR;
    }
}
