package org.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProxyController {

    private String[] services = {
            "http://ec2-98-83-163-41.compute-1.amazonaws.com:8080/search",
            "http://ec2-54-163-96-40.compute-1.amazonaws.com:8080/search"
    };
    private int currentServiceIndex = 0;

    @GetMapping("/proxy/linearsearch")
    public ResponseEntity<String> proxyLinearSearch(@RequestParam String list, @RequestParam String value) {
        String url = getNextServiceUrl() + "/linearsearch?list=" + list + "&value=" + value;
        return new RestTemplate().getForEntity(url, String.class);
    }

    @GetMapping("/proxy/binarysearch")
    public ResponseEntity<String> proxyBinarySearch(@RequestParam String list, @RequestParam String value) {
        String url = getNextServiceUrl() + "/binarysearch?list=" + list + "&value=" + value;
        return new RestTemplate().getForEntity(url, String.class);
    }

    private String getNextServiceUrl() {
        String serviceUrl = services[currentServiceIndex];
        currentServiceIndex = (currentServiceIndex +1);
        return serviceUrl;
    }
}
