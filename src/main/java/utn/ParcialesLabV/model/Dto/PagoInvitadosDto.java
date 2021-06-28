package utn.ParcialesLabV.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagoInvitadosDto {
    private String nombre;
    private String currency;
    private Double amount;
}
