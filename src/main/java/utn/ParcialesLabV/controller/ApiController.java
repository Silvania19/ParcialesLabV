package utn.ParcialesLabV.controller;

import lombok.SneakyThrows;
import org.aspectj.weaver.ast.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.ParcialesLabV.api.CallApiService;
@RestController
@RequestMapping("/apicontroller")
public class ApiController {

    CallApiService callApiService;

    @Autowired
    public ApiController(CallApiService callApiService){
    this.callApiService=callApiService;
    }

    /**probando si la app me trae lo que busco**/
   @SneakyThrows
   @GetMapping
    public double callApi(){
         Double valor= callApiService.getEuro();
        return valor;
    }
}
