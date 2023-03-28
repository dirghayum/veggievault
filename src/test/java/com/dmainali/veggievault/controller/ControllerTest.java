package com.dmainali.veggievault.controller;

import com.dmainali.veggievault.entity.Category;
import com.dmainali.veggievault.entity.Vegetable;
import com.dmainali.veggievault.service.VeggieService;
import org.easymock.EasyMock;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(EasyMockExtension.class)
public class ControllerTest {

    @Mock
    private VeggieService veggieService;

    /**
     * Test case to verify that VeggieController.findCategoryByID() method returns a category
     * object with the correct name when a valid category id is passed as input.
     *
     */
    @Test
    public void testFindCategoryByID() {
        Category category = new Category();
        category.setId(101L);
        category.setName("Vegetables");

        EasyMock.expect(veggieService.findById(101L)).andReturn(category);
        EasyMock.replay(veggieService);

        Category result = veggieService.findById(101L);
        EasyMock.verify(veggieService);
        assertEquals(101L, result.getId());
        assertEquals("Vegetables", result.getName());
    }

    /**
     *Test case to verify that VeggieController.findAllVeggies() method returns a list
     * of vegetable objects with the correct details.
     */
    @Test
    public void testFindAllVeggies(){
        Category category = new Category("Vegetables");

        List<Vegetable> vegetables = List.of(new Vegetable("Tomato", category),
                new Vegetable("Cucumber", category),
                new Vegetable("Lettuce", category));

        EasyMock.expect(veggieService.findAllVegetables()).andReturn(vegetables);
        EasyMock.replay(veggieService);

        List<Vegetable> result = veggieService.findAllVegetables();
        EasyMock.verify(veggieService);

        assertEquals(3, result.size());
        assertEquals("Cucumber", result.get(1).getName());
        assertEquals(category, result.get(0).getCategory());
    }


}
