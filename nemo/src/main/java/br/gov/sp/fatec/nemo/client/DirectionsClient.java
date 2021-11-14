package br.gov.sp.fatec.nemo.client;

import br.gov.sp.fatec.nemo.domains.entities.feingentities.response.DirectionsResponse;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@FeignClient(name = "${application.client.url.directions}", url = "${application.client.url.directions}")
public interface DirectionsClient {
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/directions/json?origin={origin}&destination={destination}&key{key}&mode={mode}"
    )
    ResponseEntity<DirectionsResponse> getDirections(
        @RequestParam("origin") String origin,
        @RequestParam("destination") String destination,
        @RequestParam("key") String key,
        @RequestParam("mode") String mode
    );
}
