package com.dmainali.veggievault.api;

import com.dmainali.veggievault.dto.VegetableDTO;
import com.dmainali.veggievault.exception.VegetableNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class VegetableFinderClient {

    @Autowired
    private RestTemplate restTemplate;

    private ObjectMapper objectMapper;

    private static final String VEGETABLE_FINDER_BASEURL = "http://localhost:8011/getVegetables";

    /**
     * Retrieves a list of 50 random vegetables from an external API and returns it as a list of VegetableDTO objects.
     * @return a list of 50 VegetableDTO objects representing random vegetables.
     * @throws VegetableNotFoundException  custom exception if an error occurs while processing the response from the external API.
     */
    public List<VegetableDTO> getRandomVegetable() {
        List<VegetableDTO> vegetableList = new ArrayList<>();
        String responseBody;


        for (int i = 0; i < 100; i++) {

            //Add Exception if Connection not found.
            //Add Exception if exception encountered in VegetableFinder API

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>("", headers);
            ResponseEntity<String> response = restTemplate.exchange(VEGETABLE_FINDER_BASEURL, HttpMethod.GET, request, String.class);
            responseBody = response.getBody();
            try {
                JsonNode jsonNode = objectMapper.readTree(responseBody); //response is converted into JSON tree
                Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.get("vegetable").fields();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> entry = fields.next();
                    String vegetableName = entry.getKey();
                    String scientificName = entry.getValue().asText();
                    VegetableDTO vegetableDTO = new VegetableDTO(vegetableName, scientificName);
                    vegetableList.add(vegetableDTO);
                }
            } catch (JsonProcessingException e) {
                throw new VegetableNotFoundException("Error while processing vegetable data", e);
            }
        }
        return vegetableList;
    }
}
