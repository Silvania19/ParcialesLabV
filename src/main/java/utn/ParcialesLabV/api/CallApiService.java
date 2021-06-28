package utn.ParcialesLabV.api;
import com.google.gson.JsonParser;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class CallApiService {

    @CircuitBreaker(name = "dolarsi", fallbackMethod = "fallback")
    public Double getDolar() throws IOException, InterruptedException {


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=dolar"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        /**Me aseguro que lo que se trae de la app tenga contenido antes de comenzar a trabajar con el json**/

        if (!response.body().isEmpty()){
            String valor = JsonParser.parseString(response.body()).getAsJsonArray().get(0)
                    .getAsJsonObject().get("casa")
                    .getAsJsonObject().get("compra").getAsString();
            return Double.valueOf(valor.replace(",", "."));
        }
        else {

            String valor="0";
            return Double.valueOf(valor.replace(",", "."));

        }
    }

@CircuitBreaker(name = "dolarsi", fallbackMethod = "fallback")

public Double getEuro() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=genedolar&opc=ta"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        /**Me aseguro que lo que se trae de la app tenga contenido antes de comenzar a trabajar con el json**/

        if (!response.body().isEmpty()){
            String valor = JsonParser.parseString(response.body()).getAsJsonArray().get(0)
                    .getAsJsonObject().get("dolar")
                    .getAsJsonObject().get("compra").getAsString();
            return Double.valueOf(valor.replace(",", "."));
        }
        else {

            String valor="0";
            return Double.valueOf(valor.replace(",", "."));

        }
    }
    public Double fallback(final Throwable t) {
        log.info("Fallback cause, {}", t.toString());
        return 0.0;
    }

/*[
    {
        "dolar": {
        "nombre": "Euro",
                "compra": "108,61",
                "venta": "118,30"
    }
    }
]
*/
   /*
[
  {
    "casa": {
      "nombre": "Oficial",
      "compra": "94,990",
      "venta": "100,990",
      "agencia": "344",
      "observaciones": {},
      "geolocalizacion": {
        "latitud": {},
        "longitud": {}
      },
      "telefono": "0810-666-4444",
      "direccion": {},
      "decimales": "3"
    }
  },
  {
    "casa": {
      "nombre": "Blue",
      "compra": "171,000",
      "venta": "174,000",
      "agencia": "380",
      "observaciones": {},
      "geolocalizacion": {
        "latitud": {},
        "longitud": {}
      },
      "telefono": {},
      "direccion": {},
      "decimales": "3"
    }
  },
  {
    "casa": {
      "nombre": "Mayorista Bancos",
      "compra": "95,400",
      "venta": "95,600",
      "agencia": "44",
      "geolocalizacion": {
        "latitud": "-34.6033922",
        "longitud": "-58.439710"
      },
      "telefono": "4556-8995",
      "direccion": "Uruguay 4532",
      "observaciones": {},
      "decimales": "3"
    }
  },
  {
    "casa": {
      "nombre": "BCRA de Referencia",
      "compra": "94,657",
      "venta": "100,989",
      "agencia": "49",
      "observaciones": {},
      "decimales": "3"
    }
  },
  {
    "casa": {
      "nombre": "Banco Nación Billete",
      "compra": "94,500",
      "venta": "100,500",
      "agencia": "47",
      "observaciones": {},
      "decimales": "3"
    }
  },
  {
    "casa": {
      "nombre": "Banco Nación Público",
      "compra": "94,500",
      "venta": "100,500",
      "agencia": "210",
      "observaciones": {},
      "decimales": "3"
    }
  }
]
*/

}
