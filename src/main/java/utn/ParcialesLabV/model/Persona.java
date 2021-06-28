package utn.ParcialesLabV.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "typePerson", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Jugador.class, name = "JUGADOR"),
        @JsonSubTypes.Type(value = Representante.class, name = "REPRESENTANTE"),
        @JsonSubTypes.Type(value = Representante.class, name = "AMIGO")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="id")

public abstract class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull(message = "el campo no puede ser vacio")
    private String name;
    @NotNull(message = "el campo no puede ser vacio")
    private String lastName;


    @OneToOne (mappedBy="cumpleaniero")
    Cumpleanito cumpleaniero;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCumpleaniero")
    Set<Cumpleanito> cumpleanitos;


    @AccessType(AccessType.Type.PROPERTY)//de esta manera y junto con el jsonsubtypes, hariA una especie de herencia
    public abstract TypePerson typePerson();

}
