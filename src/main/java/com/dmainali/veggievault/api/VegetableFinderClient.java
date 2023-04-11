package com.dmainali.veggievault.api;

import com.dmainali.veggievault.dto.VegetableDTO;
import com.dmainali.veggievault.entity.VeggieWrapper;
import com.dmainali.veggievault.exception.VeggieException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class VegetableFinderClient {

    @Autowired
    private RestTemplate restTemplate;

    private ObjectMapper objectMapper;

    private static final String VEGETABLE_FINDER_BASEURL = "http://localhost:8011/getVegetables";

    /**
     * Retrieves a list of 50 random vegetables from an external API and returns it as a list of VegetableDTO objects.
     *
     * @return a list of 50 VegetableDTO objects representing random vegetables.
     * @throws VeggieException custom exception if an error occurs while processing the response from the external API.
     * @throws RestClientException exception if connection error occurs in external API.
     */
    public List<VegetableDTO> getRandomVegetable() {
        List<VegetableDTO> vegetableList = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("", headers);
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ints.add(i);
        }
        ints.parallelStream().forEach(i -> {
            try {
                ResponseEntity<String> response = restTemplate.exchange(VEGETABLE_FINDER_BASEURL, HttpMethod.GET, request, String.class);
                String responseBody = response.getBody();
                VeggieWrapper rWrapper = objectMapper.readValue(responseBody, VeggieWrapper.class);
                VegetableDTO vegetableDTO = new VegetableDTO(rWrapper.getVegetable().getName(), rWrapper.getVegetable().getScientificName());
                vegetableList.add(vegetableDTO);
            } catch (JsonProcessingException e) {
                throw new VeggieException("Error while processing vegetable data", e);
            } catch (RestClientException e) {
                log.warn("Could not connect to remote service: {} {}", e.getMessage(), e.getMostSpecificCause());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });
        return vegetableList;
    }
}
