package com.dmainali.veggievault.api;

import com.dmainali.veggievault.service.VeggieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.web.client.RestTemplate;


@ExtendWith(EasyMockExtension.class)
public class VegetableFinderClientTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @TestSubject
    VeggieService veggieService;

    @Test
    public void testGetRandomVegetable(){

    }

}
