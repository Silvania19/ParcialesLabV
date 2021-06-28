package utn.ParcialesLabV.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.ParcialesLabV.model.Currency;
import utn.ParcialesLabV.service.CurrencyService;

import java.util.List;
@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;
    @GetMapping("/{id}")
    public Currency getByID(@PathVariable Integer id) {
        return currencyService.getByID(id);
    }
    @GetMapping("/")
    public List<Currency> getAll(){
        return  currencyService.getAll();
    }
    @PostMapping
    public void addCurrency(@RequestBody Currency currency )
    {
        currencyService.add(currency);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id){
        currencyService.deleteById(id);
    }

}

  /*  @OneToMany
    @JoinColumn(name = "player_id")
    private List<Player> players;



    @ManyToOne
    Manager birthdayPerson;

    @OneToMany
    Set<Person> guests;*/