package utn.ParcialesLabV.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.ParcialesLabV.Utils.EntityURLBuilder;
import utn.ParcialesLabV.model.Persona;
import utn.ParcialesLabV.model.Representante;
import utn.ParcialesLabV.service.PersonaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaService personaService;

    @GetMapping("/{id}")
    public Persona getByID(@PathVariable Integer id) {

        return personaService.getByID(id);
    }
    @GetMapping("/")
    public List<Persona> getAll(){

        return  personaService.getAll();
    }

    @PostMapping
    public ResponseEntity addPersona(@RequestBody Persona persona) {

       Persona newPerson= personaService.add(persona);
        URI location = EntityURLBuilder.buildURL("fee", newPerson.getId());
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Integer id){

        personaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}/jugadores/{idJugador}")
    public ResponseEntity addJugadorToRepresentante(@PathVariable Integer id, @PathVariable Integer idJugador){
       Representante persona = personaService.addJugadorToRepresentante(id, idJugador);
        URI location= EntityURLBuilder.buildURL("persona", persona.getId());
        return ResponseEntity.ok(location);
    }

    @PutMapping("/{id}/Amigos/{idAmigo}")
    public ResponseEntity addAmigoToRepresentante(@PathVariable Integer id, @PathVariable Integer idAmigo){
        Representante persona = personaService.addAmigoToRepresentante(id, idAmigo);
        URI location= EntityURLBuilder.buildURL("persona", persona.getId());
        return ResponseEntity.ok(location);
    }

    @PutMapping("/{id}/Cumpleanitos/{idCumpleanito}")
    public ResponseEntity addCumpleanitoToPerson(@PathVariable Integer id, @PathVariable Integer idCumpleanito){
        Persona persona = personaService.addCumpleanitoToPersona(id, idCumpleanito);
        URI location= EntityURLBuilder.buildURL("persona", persona.getId());
        return ResponseEntity.ok(location);
    }

}

