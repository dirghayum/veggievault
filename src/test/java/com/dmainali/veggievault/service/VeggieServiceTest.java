package com.dmainali.veggievault.service;

import com.dmainali.veggievault.api.VegetableFinderClient;
import com.dmainali.veggievault.dao.CategoryDao;
import com.dmainali.veggievault.dao.VegetableDao;
import com.dmainali.veggievault.dto.VegetableDTO;
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

import static org.easymock.EasyMock.anyObject;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(EasyMockExtension.class)
public class VeggieServiceTest {

    @Mock
    private CategoryDao categoryDao;

    @Mock
    private VegetableDao vegetableDao;

    @Mock
    private VegetableFinderClient vegetableFinder;

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
        vegetables.add(new Vegetable("Carrot", category));
        vegetables.add(new Vegetable("Spinach", category));

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

    /**
     * Test to saves a list of vegetables by converting a list of VegetableDTO to a list of Vegetable entities.
     * If a duplicate vegetable is found, it is logged and not added to the list of saved vegetables.
     * The category of all vegetables is set to the category with id 1.
     */
    @Test
    public void testSaveAll(){
        Category category = new Category();
        category.setId(1L);
        category.setName("Vegetables");

        List<VegetableDTO> vegetableDTOList = List.of(new VegetableDTO("Carrot","Veggies"),
                new VegetableDTO("Spinach","Veggies"));

        Vegetable v1 = new Vegetable();
        v1.setId(200L);
        v1.setName("Carrot");
        v1.setCategory(category);

        Vegetable v2 = new Vegetable();
        v2.setId(300L);
        v2.setName("Spinach");
        v2.setCategory(category);

        EasyMock.expect(categoryDao.findById(1L)).andReturn(category);

        EasyMock.expect(vegetableDao.save(anyObject(Vegetable.class))).andReturn(v1).times(1);
        EasyMock.expect(vegetableDao.save(anyObject(Vegetable.class))).andReturn(v2).times(1);

        EasyMock.replay(categoryDao,vegetableDao);

        List<Vegetable> savedVegetables = veggieService.saveAll(vegetableDTOList);

        EasyMock.verify(categoryDao, vegetableDao);

        assertEquals(2, savedVegetables.size());
        assertEquals("Carrot", savedVegetables.get(0).getName());
        assertEquals("Spinach", savedVegetables.get(1).getName());

    }

    /**
     * Test to verify that VeggieService.getRandom retrieves a list of randomly selected VegetableDTO objects
     * from the VegetableFinder service.
     */
    @Test
    public void testGetRandom(){
        List<VegetableDTO> vegetableDTOList = List.of(new VegetableDTO("Carrot","Veggies"),
                new VegetableDTO("Spinach","Veggies"));

        EasyMock.expect(vegetableFinder.getRandomVegetable()).andReturn(vegetableDTOList);
        EasyMock.replay(vegetableFinder);

        List<VegetableDTO> result = veggieService.getRandom();
        EasyMock.verify(vegetableFinder);

        assertEquals(vegetableDTOList, result);
    }

}
