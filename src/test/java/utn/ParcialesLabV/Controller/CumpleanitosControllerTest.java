package utn.ParcialesLabV.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utn.ParcialesLabV.controller.CumpleanitosController;
import utn.ParcialesLabV.model.Dto.PagoInvitadosDto;
import utn.ParcialesLabV.service.CumpleanitosService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static utn.ParcialesLabV.Util.UtilTest.aPagoInvitadoDto;

public class CumpleanitosControllerTest {
    CumpleanitosController cumpleanitosController;
    CumpleanitosService cumpleanitosService;


    @BeforeEach
    public void setUp(){
        this.cumpleanitosService= mock(CumpleanitosService.class);
        cumpleanitosController= new CumpleanitosController(cumpleanitosService);
    }

    @Test
    public  void getAllInvitadosHappyPath(){
        //given
        Pageable pageable = PageRequest.of(1, 10);
        Page<PagoInvitadosDto> mockedPage = mock(Page.class);

        List<PagoInvitadosDto> listaInvitados = List.of(aPagoInvitadoDto());
        when(mockedPage.getTotalElements()).thenReturn(10L);
        when(mockedPage.getTotalPages()).thenReturn(1);
        when(mockedPage.getContent()).thenReturn(listaInvitados);
        when(cumpleanitosService.getAllInvitedos(pageable)).thenReturn(mockedPage);

        //when

        ResponseEntity<List<PagoInvitadosDto>> response = cumpleanitosController.getAllInvited(pageable);

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(10L, Long.parseLong(response.getHeaders().get("X-Total-Count").get(0)));
        assertEquals(1, Integer.parseInt(response.getHeaders().get("X-Total-Pages").get(0)));
        assertEquals(listaInvitados, response.getBody());
    }
    @Test
    public  void getAllInvitadosSadPath(){
        //given
        Pageable pageable = PageRequest.of(1, 10);
        Page<PagoInvitadosDto> mockedPage = mock(Page.class);

        List<PagoInvitadosDto> listaInvitados = List.of(aPagoInvitadoDto());
        when(mockedPage.getTotalElements()).thenReturn(10L);
        when(mockedPage.getTotalPages()).thenReturn(1);
        when(mockedPage.getContent()).thenReturn(listaInvitados);
        when(cumpleanitosService.getAllInvitedos(pageable)).thenReturn(mockedPage);

        //when

        ResponseEntity<List<PagoInvitadosDto>> response = cumpleanitosController.getAllInvited(pageable);

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(listaInvitados, response.getBody());
    }
}
