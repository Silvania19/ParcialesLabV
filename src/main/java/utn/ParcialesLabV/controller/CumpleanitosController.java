package utn.ParcialesLabV.controller;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.ParcialesLabV.model.Cumpleanito;
import utn.ParcialesLabV.model.Dto.PagoInvitadosDto;
import utn.ParcialesLabV.service.CumpleanitosService;

import java.util.List;
@NoArgsConstructor
@RestController
@RequestMapping("/cumpleanitos")
public class CumpleanitosController {
    /*Nuestro amigo Guillermo quiere realizar una fiesta para festejar su cumpleaños,
pero tiene tantos amigos que necesita dividir su fiesta ya que solo puede invitar un maximo de 10 personas por evento,
Agregar lo necesario para poder dar de alta Amigos y poder vincularlos a la lista de invitados al cumpleanitos.*/
    CumpleanitosService cumpleanitosService;

    @Autowired
    public CumpleanitosController(CumpleanitosService cumpleanitosService){
        this.cumpleanitosService=cumpleanitosService;
    }
    @PostMapping
    public void addPerson(@RequestBody Cumpleanito cumpleanitos ) {
        cumpleanitosService.add(cumpleanitos);
    }

    @GetMapping("/{id}")
    public Cumpleanito getByID(@PathVariable Integer id) {
        return cumpleanitosService.getByID(id);
    }

    @GetMapping("/")
    public List<Cumpleanito> getAll(){
        return  cumpleanitosService.getAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id){
        cumpleanitosService.deleteById(id);
    }

    @PutMapping("/{idCumpleanitos}/personas/{idInvitado}")
    public void addPersonaToListInvitados(@PathVariable Integer IdCumpleanitos, @PathVariable Integer idInvitado){
        cumpleanitosService.addPersonaToListInvitados(IdCumpleanitos, idInvitado);
    }

    @GetMapping("listInvited")
    public ResponseEntity<List<PagoInvitadosDto>> getAllInvited(Pageable pageable){
        Page pageofInvitados= cumpleanitosService.getAllInvitedos(pageable);
        return response(pageofInvitados);
    }

    private ResponseEntity response(Page page) {
        HttpStatus httpStatus = page.getContent().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.
                status(httpStatus).
                header("X-Total-Count", Long.toString(page.getTotalElements())).
                header("X-Total-Pages", Long.toString(page.getTotalPages())).
                body(page.getContent());
    }

}

