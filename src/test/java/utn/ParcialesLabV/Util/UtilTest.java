package utn.ParcialesLabV.Util;

import org.springframework.data.domain.*;
import utn.ParcialesLabV.model.Dto.PagoInvitadosDto;
import utn.ParcialesLabV.model.Jugador;
import utn.ParcialesLabV.model.Persona;

import java.util.List;

public class UtilTest {
    public static PagoInvitadosDto aPagoInvitadoDto(){
        PagoInvitadosDto pagoInvitadosDto= PagoInvitadosDto.builder()
                .nombre("lucuas")
                .amount(100.0)
                .build();

        return pagoInvitadosDto;
    }

    public static Page<PagoInvitadosDto> aPagePago(){
        return new PageImpl<>(List.of(aPagoInvitadoDto()));
    }

    public static Persona aPersona(){
        Persona persona= new Jugador();
        persona.setId(1);
        persona.setName("lucas");
        return persona;
    }
    public static Pageable aPageable(){
            Pageable pageable = PageRequest.of(1,10);
            return pageable;
        }
    public static Page<Persona> aPagePersona(){
        return new PageImpl<>(List.of(aPersona()));
    }
}
