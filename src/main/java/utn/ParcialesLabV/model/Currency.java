package utn.ParcialesLabV.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
@Data
@Entity
@Table(name="currency")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer idCurrency;
    @PositiveOrZero(message = "el valor no puede ser negativo")
    private  Double monto;

    private TypeCurrency typeCurrency;

}
