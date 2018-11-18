package ua.com.bpgdev.movieland.web.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:property/api-context-test.xml", "classpath:property/root-context-test.xml"})
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
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        String actualJson = mvcResult.getResponse().getContentAsString();

        Type listType = new TypeToken<ArrayList<Genre>>() {
        }.getType();
        List<Genre> actualGenres = new Gson().fromJson(actualJson, listType);
        List<Genre> expectedGenres = jdbcGenreDao.getAll();

        assertEquals(actualGenres, expectedGenres);
    }
}