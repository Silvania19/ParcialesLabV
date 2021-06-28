package utn.ParcialesLabV.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import utn.ParcialesLabV.api.CallApiService;
import utn.ParcialesLabV.model.Dto.PagoInvitadosDto;
import utn.ParcialesLabV.model.TypeCurrency;
import utn.ParcialesLabV.repository.CumpleanitosRepository;
import utn.ParcialesLabV.service.CumpleanitosService;
import utn.ParcialesLabV.service.PersonaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static utn.ParcialesLabV.Util.UtilTest.*;

public class CumpleanitosServiceTest {
    CumpleanitosService cumpleanitosService;
    PersonaService personaService;
    CallApiService callApiService;
    CumpleanitosRepository cumpleanitosRepository;
    @BeforeEach
    public void setUp(){
        this.callApiService=mock(CallApiService.class);
        this.personaService=mock(PersonaService.class);
        this.cumpleanitosRepository=mock(CumpleanitosRepository.class);
        cumpleanitosService=new CumpleanitosService(cumpleanitosRepository, personaService, callApiService);
    }
    @Test
    public void getAllInvitadosOk(){

        Double valorDolar=100.0;
        Double valorEuro=110.0;
        try {
            when(callApiService.getDolar()).thenReturn(valorDolar);
            when(callApiService.getEuro()).thenReturn(valorEuro);
            when(cumpleanitosRepository.getAllInvitados(aPageable())).thenReturn(aPagePersona());

            Page<PagoInvitadosDto> pagePago= cumpleanitosService.getAllInvitedos(aPageable());

            assertEquals(1, pagePago.getTotalElements());
            assertEquals(TypeCurrency.DOLAR, pagePago.getContent().get(0).getCurrency());
            assertEquals(25000 / valorDolar, pagePago.getContent().get(0).getAmount());
        }catch (Exception e){

        }
    }


}
