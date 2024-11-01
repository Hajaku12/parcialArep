package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProxyController {

    @Value("${service1.url}")
    private String service1Url;

    @Value("${service2.url}")
    private String service2Url;

    private int currentServiceIndex = 0;

    @GetMapping("/proxy/linearsearch")
    public ResponseEntity<String> proxyLinearSearch(@RequestParam String list, @RequestParam String value) {
        try {
            String url = getNextServiceUrl() + "/linearsearch?list=" + list + "&value=" + value;
            return new RestTemplate().getForEntity(url, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/proxy/binarysearch")
    public ResponseEntity<String> proxyBinarySearch(@RequestParam String list, @RequestParam String value) {
        try {
            String url = getNextServiceUrl() + "/binarysearch?list=" + list + "&value=" + value;
            return new RestTemplate().getForEntity(url, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

    private String getNextServiceUrl() {
        String[] services = {service1Url, service2Url};
        String serviceUrl = services[currentServiceIndex];
        currentServiceIndex = (currentServiceIndex + 1) % services.length;
        return serviceUrl;
    }
}
