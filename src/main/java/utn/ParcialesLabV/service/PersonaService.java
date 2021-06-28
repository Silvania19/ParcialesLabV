package utn.ParcialesLabV.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import utn.ParcialesLabV.model.*;
import utn.ParcialesLabV.repository.CumpleanitosRepository;
import utn.ParcialesLabV.repository.PersonaRepository;

import java.util.List;
@Service
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    CumpleanitosRepository cumpleanitosRepository;

    public Persona getByID(Integer id) {
        return  personaRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public List<Persona> getAll() {
        return personaRepository.findAll();
    }

    public Persona add(Persona persona) {
        return  personaRepository.save(persona);

    }

    public void deleteById(Integer id) {
        personaRepository.deleteById(id);
    }

    public Representante addJugadorToRepresentante(Integer id, Integer idJugador) {
        Persona pr=getByID(id);
        Persona pj=getByID(idJugador);
        Representante prn;
        if(pr instanceof Representante && pj instanceof Jugador){
            ((Representante) pr).getJugadorList().add((Jugador) pj);
         prn=(Representante) personaRepository.save(pr);
        }
        else {
            throw new IllegalArgumentException("datos no validos");
        }

        return prn;
    }

    public Representante addAmigoToRepresentante(Integer id, Integer idAmigo) {
        Persona pr=getByID(id);
        Persona pa=getByID(idAmigo);
        Representante pwr;
        if(pr instanceof Representante && pa instanceof Amigo){
            ((Representante) pr).getAmigos().add((Amigo) pa);
         pwr= (Representante) personaRepository.save(pr);
        }
        else {
            throw new IllegalArgumentException("datos no validos");
        }

        return pwr;
    }

    public Persona addCumpleanitoToPersona(Integer id, Integer idCumpleanito) {
        Persona persona = getByID(id);
        Cumpleanito cumple = cumpleanitosRepository.findById(idCumpleanito).
                orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        ((Persona) persona).getCumpleanitos().add(cumple);
        return personaRepository.save(persona);

    }

   /*public void addAmigoToListInvitados(Integer idAmigo, Integer idCumpleanito) {
        Persona pA= getByID(idAmigo);
        CumpleanitosController cumpleanitosController= new CumpleanitosController();
        Cumpleanitos cumpli= cumpleanitosController.getByID(idCumpleanito);
        if(pA instanceof Amigo)
        {
            if(cumpli.getInvitados().stream().count()<10){
                personaRepository.save(pA);
            }
            else {
                throw new limiteInvitadoException("no hay espacio para otro invitado");

            }
            throw new IllegalArgumentException("el id no es de un amigo");
        }
    }*/
}
