package com.dmainali.veggievault.api;

import com.dmainali.veggievault.dto.VegetableDTO;
import com.dmainali.veggievault.dto.VegetableFinderDTO;
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

    public List<VegetableDTO> getRandomVegetable() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("", header);
        ResponseEntity<String> response = restTemplate.exchange(VEGETABLE_FINDER_BASEURL, HttpMethod.GET, request, String.class);
        String responseBody = response.getBody();

        try {
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.get("vegetable").fields();
            List<VegetableDTO> vegetableList = new ArrayList<>();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                String vegetableName = entry.getKey();
                String scientificName = entry.getValue().asText();
                VegetableDTO vegetableDTO = new VegetableDTO(vegetableName, scientificName);
                vegetableList.add(vegetableDTO);
            }
            return vegetableList;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
