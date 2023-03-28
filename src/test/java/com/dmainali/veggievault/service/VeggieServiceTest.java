package com.dmainali.veggievault.service;

import com.dmainali.veggievault.dao.CategoryDao;
import com.dmainali.veggievault.dao.VegetableDao;
import com.dmainali.veggievault.entity.Category;
import com.dmainali.veggievault.entity.Vegetable;
import org.easymock.EasyMock;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(EasyMockExtension.class)
public class VeggieServiceTest {

    @Mock
    private CategoryDao categoryDao;

    @Mock
    private VegetableDao vegetableDao;

    @TestSubject
    private VeggieService veggieService;

    /**
     * Test case to verify that VeggieService.findById() method returns a category
     * object with the correct name when a valid category id is passed as input.
     */
    @Test
    public void testFindById() {
        // Create a category object with the given ID and name
        Category category = new Category();
        category.setId(101L);
        category.setName("Vegetables");

        // Mock the categoryDao's findById() method to return the above category object
        EasyMock.expect(categoryDao.findById(101L)).andReturn(category);
        EasyMock.replay(categoryDao);

        // Call the findById() method of VeggieService and verify the output
        Category result = veggieService.findById(101L);
        EasyMock.verify(categoryDao);
        assertEquals("Vegetables", result.getName());
    }

    /**
     * Test case to verify that VeggieService.findAllVegetables() method returns a list
     * of vegetable objects with the correct details.
     */
    @Test
    public void testFindAllVegetables() {
        Category category = new Category("Vegetables");

        // Create a list of vegetable objects with the given name and category
        List<Vegetable> vegetables = new ArrayList<>();
        vegetables.add(new Vegetable("Tomato", category));
        vegetables.add(new Vegetable("Cucumber", category));
        vegetables.add(new Vegetable("Lettuce", category));

        // Mock the vegetableDao's findAll() method to return the above list of vegetables
        EasyMock.expect(vegetableDao.findAll()).andReturn(vegetables);
        EasyMock.replay(vegetableDao);

        // Call the findAllVegetables() method of VeggieService and verify the output
        List<Vegetable> result = veggieService.findAllVegetables();
        EasyMock.verify(vegetableDao);

        assertEquals(3, result.size());
        assertEquals("Tomato", result.get(0).getName());
        assertEquals(category, result.get(0).getCategory());
    }

}
