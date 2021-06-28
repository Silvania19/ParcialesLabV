package utn.ParcialesLabV.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import utn.ParcialesLabV.api.CallApiService;
import utn.ParcialesLabV.exception.LimiteInvitadoException;
import utn.ParcialesLabV.model.Cumpleanito;
import utn.ParcialesLabV.model.Dto.PagoInvitadosDto;
import utn.ParcialesLabV.model.Jugador;
import utn.ParcialesLabV.model.Persona;
import utn.ParcialesLabV.model.TypeCurrency;
import utn.ParcialesLabV.repository.CumpleanitosRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class CumpleanitosService {

    private CumpleanitosRepository cumpleanitosRepository;
    private PersonaService personaService;
    private CallApiService callApiService;
    @Autowired
    public CumpleanitosService(CumpleanitosRepository cumpleanitosRepository, PersonaService personaService, CallApiService callApiService){
        this.cumpleanitosRepository=cumpleanitosRepository;
        this.personaService=personaService;
        this.callApiService= callApiService;
    }

    public Cumpleanito getByID(Integer id) {
        return  cumpleanitosRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }
    public List<Cumpleanito> getAll() {
        return cumpleanitosRepository.findAll();
    }

    public void add(Cumpleanito cumpleanitos) {
        cumpleanitosRepository.save(cumpleanitos);

    }

    public void deleteById(Integer id) {
        cumpleanitosRepository.deleteById(id);

    }

   public void addPersonaToListInvitados (Integer IdCumpleanitos, Integer idInvitado)
    {
        Cumpleanito cumpli= getByID(IdCumpleanitos);
        Persona invitado= personaService.getByID(idInvitado);

        if(cumpli.getInvitados().stream().count() < 10){
            cumpli.getInvitados().add(invitado);
            cumpleanitosRepository.save(cumpli);
        }
        else
        {
            throw  new LimiteInvitadoException("No hay espacio para otro invitado, estamos en pandemia");
        }
    }

   @SneakyThrows
   public Page getAllInvitedos(Pageable pageable) {

        Double valueDolar= callApiService.getDolar();
        Double valueEuro= callApiService.getEuro();
        List<PagoInvitadosDto> pagoInvitadosDtos= new ArrayList<>();
        Page invitados= cumpleanitosRepository.getAllInvitados(pageable);
        for (Object object : invitados){
            Persona invitado= (Persona) object;
            PagoInvitadosDto pagoInvitadosDto= PagoInvitadosDto.builder().nombre(invitado.getName())
                    .build();
            if (invitado instanceof Jugador){
                pagoInvitadosDto.setCurrency(((Jugador) invitado).getCurrency().toString());
                pagoInvitadosDto.setAmount((pagoInvitadosDto.getCurrency().equals(TypeCurrency.DOLAR)) ? 25000 / valueDolar:25000 / valueEuro);
            }
            pagoInvitadosDtos.add(pagoInvitadosDto);
        }
        return new PageImpl<>(pagoInvitadosDtos);
    }
}
