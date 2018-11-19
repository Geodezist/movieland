package ua.com.bpgdev.movieland.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.bpgdev.movieland.dao.GenreDao;
import ua.com.bpgdev.movieland.entity.Genre;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:property/apiContext-test.xml", "classpath:property/applicationContext-test.xml"})
@WebAppConfiguration
public class GenreControllerTest {
    @Autowired
    private GenreDao jdbcGenreDao;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void getAll() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult mvcResult = mockMvc
                .perform(get("/genre"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        String actualJson = mvcResult.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        TypeReference listGenres = new TypeReference<List<Genre>>() {
        };
        List<Genre> actualGenres = mapper.readValue(actualJson, listGenres);

        List<Genre> expectedGenres = jdbcGenreDao.getAll();

        assertEquals(actualGenres, expectedGenres);
    }
}