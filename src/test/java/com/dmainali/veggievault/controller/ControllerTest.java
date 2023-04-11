package com.dmainali.veggievault.controller;

import com.dmainali.veggievault.dto.VegetableDTO;
import com.dmainali.veggievault.entity.Category;
import com.dmainali.veggievault.entity.ResultWrapper;
import com.dmainali.veggievault.entity.Vegetable;
import com.dmainali.veggievault.service.VeggieService;
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
public class ControllerTest {

    @Mock
    private VeggieService veggieService;

    @TestSubject
    VeggieController controller;

    /**
     * Test case to verify that VeggieController.findCategoryByID() method returns a category
     * object with the correct name when a valid category id is passed as input.
     */

    @Test
    public void findCategoryByID(){
        Category category = new Category();
        category.setId(101L);
        category.setName("Vegetables");

        EasyMock.expect(veggieService.findById(101L)).andReturn(category);
        EasyMock.replay(veggieService);
        Category result = controller.findCategoryByID(101L);
        EasyMock.verify(veggieService);
        assertEquals(101L, result.getId());
        assertEquals("Vegetables", result.getName());
    }

    /**
     *Test case to verify that VeggieController.findAllVeggies() method returns a list
     * of vegetable objects with the correct details.
     */
    @Test
    public void getAllVeggies(){
        Category category = new Category("Vegetables");

        List<Vegetable> vegetables = List.of(new Vegetable("Tomato", category),
                new Vegetable("Carrot", category),
                new Vegetable("Spinach", category));

        EasyMock.expect(veggieService.findAllVegetables()).andReturn(vegetables);
        EasyMock.replay(veggieService);

        List<Vegetable> result = controller.getAllVeggies();
        EasyMock.verify(veggieService);

        assertEquals(3, result.size());
        assertEquals("Carrot", result.get(1).getName());
        assertEquals(category, result.get(0).getCategory());
    }

    /**
     * Test case to verify that VeggieController.getRandomVegetable() method returns a wrapper object
     * containing a random list of vegetable objects. This method first calls the VeggieService.getRandom()} method to
     * get a list of VegetableDTO objects. Then, it calls the VeggieService.saveAll(Set) method to save the list of
     * Vegetable objects as in the database and then returns the random list of vegetables.
     */
    @Test
    public void getRandomVegetable(){
        ResultWrapper wrapper = new ResultWrapper();
        List<VegetableDTO> vegetableDTOList = List.of(new VegetableDTO("Carrot","Veggies"),
                new VegetableDTO("Spinach","Veggies"));

        List<Vegetable> vList = new ArrayList<>();
        vList.add(new Vegetable("Carrot"));
        vList.add(new Vegetable("Spinach"));

        wrapper.setVegetables(vList);

        EasyMock.expect(veggieService.getRandom()).andReturn(vegetableDTOList);
        EasyMock.expect(veggieService.saveAll(vegetableDTOList)).andReturn(vList);
        EasyMock.replay(veggieService);

        ResultWrapper rWrapper = controller.getRandomVegetable();

        EasyMock.verify(veggieService);

        assertEquals(wrapper.getVegetables().size(), rWrapper.getVegetables().size());
        assertEquals(wrapper.getVegetables().get(0).getName(), rWrapper.getVegetables().get(0).getName());
        assertEquals(wrapper.getVegetables().get(1).getName(), rWrapper.getVegetables().get(1).getName());
    }
}
