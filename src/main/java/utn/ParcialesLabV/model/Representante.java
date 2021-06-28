package utn.ParcialesLabV.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
@Data

@NoArgsConstructor
@Entity //sera una clase de la cual se creara una persistencia
public class Representante extends  Persona{
    @PositiveOrZero
    private double pesoBoveda;
    @PositiveOrZero
    private Double montoTotal;


    @OneToMany /*(,fetch = FetchType.LAZY mappedBy = "representante")*/
    @JoinColumn(name="id_jugador")
    private List<Jugador> jugadorList;

    @OneToMany
    @JoinColumn(name="id_amigo")
    private List<Amigo> amigos;

    @Override
    public TypePerson typePerson() {

        return TypePerson.REPRESENTANTE;
    }

    public double getMontoTotal()
    {
        Double monto=0.0;

        for(Jugador jugador : jugadorList){
            monto +=jugador.getCurrency().getMonto() * jugador.getCurrency().getTypeCurrency().getValor();
        }
        return  monto;
    }
    public double getPesoBoveda(){
        double monto=0.0;
        monto=getMontoTotal();
        return  monto/100; //suponiendo que los billetes pesan un gramo y son de 100
    }
}
