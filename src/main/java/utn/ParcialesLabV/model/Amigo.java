package utn.ParcialesLabV.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
@Entity
@NoArgsConstructor
@Data
public class Amigo extends Persona{

    @NotNull(message = "el campo no puede ser null")
    private  String profesion;
    @NotNull(message = "el campo no puede ser null")
    private String statusSocial;


    @Override
    public TypePerson typePerson() {
        return TypePerson.AMIGO;
    }
}
