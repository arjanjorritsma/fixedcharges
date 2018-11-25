package nl.arjan.fixedcosts.view.resource;

import nl.arjan.fixedcosts.view.dto.CategoryDTO;
import nl.arjan.fixedcosts.view.dto.CategoryDTOTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Unittests for the {@link CategoryResource} class.
 *
 * @author arjan
 * @since 23-11-18
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CategoryResource.class)
public class CategoryResourceTest {
    private static final String URL = "/categories";
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryResource categoryResource;

    @Test
    public void getAll() throws Exception {
        CategoryDTO dto = CategoryDTOTest.init();
        List<CategoryDTO> allCategories = singletonList(dto);

        given(categoryResource.getAll()).willReturn(allCategories);

        mvc.perform(get(URL) //
                .contentType(APPLICATION_JSON)) //
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$", hasSize(1))) //
                .andExpect(jsonPath("$[0].id", is(dto.getId().intValue())))
                .andExpect(jsonPath("$[0].description", is(dto.getDescription())))
                .andExpect(jsonPath("$[0].dayOfDebit", is(dto.getDayOfDebit())));

    }

    @Test
    public void query() throws Exception {
        CategoryDTO dto = CategoryDTOTest.init();

        given(categoryResource.query(dto.getDescription())).willReturn(Arrays.asList(dto));

        mvc.perform(get(URL + "/query?description={description}", dto.getDescription()) //
                .contentType(APPLICATION_JSON)) //
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$", hasSize(1))) //
                .andExpect(jsonPath("$[0].id", is(dto.getId()))) //
                .andExpect(jsonPath("$[0].description", is(dto.getDescription()))) //
                .andExpect(jsonPath("$[0].dayOfDebit", is(dto.getDayOfDebit())));
    }

    @Test
    public void getById() throws Exception {
        CategoryDTO dto = CategoryDTOTest.init();

        given(categoryResource.getById(dto.getId())).willReturn(ResponseEntity.ok(dto));

        mvc.perform(get(URL + "/{id}", dto.getId()) //
                .contentType(APPLICATION_JSON)) //
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("id", is(dto.getId()))) //
                .andExpect(jsonPath("description", is(dto.getDescription()))) //
                .andExpect(jsonPath("dayOfDebit", is(dto.getDayOfDebit())));
    }

    @Test
    public void create() throws Exception {
        CategoryDTO dto = CategoryDTOTest.init();

        given(categoryResource.create(dto)).willReturn(dto);

        mvc.perform(post(URL) //
                .contentType(APPLICATION_JSON)) //
                .andExpect(status().isCreated()) //
                .andExpect(jsonPath("id", is(dto.getId()))) //
                .andExpect(jsonPath("description", is(dto.getDescription()))) //
                .andExpect(jsonPath("dayOfDebit", is(dto.getDayOfDebit())));
    }

    @Test
    public void update() throws Exception {
        CategoryDTO dto = CategoryDTOTest.init();

        given(categoryResource.update(dto, dto.getId())).willReturn(ResponseEntity.ok(dto));

        MockHttpServletRequestBuilder put = put(URL + "/{id}", dto.getId());

        mvc.perform(put //
                .contentType(APPLICATION_JSON)) //
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("id", is(dto.getId()))) //
                .andExpect(jsonPath("description", is(dto.getDescription()))) //
                .andExpect(jsonPath("dayOfDebit", is(dto.getDayOfDebit())));
    }
}