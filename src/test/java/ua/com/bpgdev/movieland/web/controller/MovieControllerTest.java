package ua.com.bpgdev.movieland.web.controller;

import com.google.gson.Gson;
import org.junit.Before;
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
import com.google.gson.reflect.TypeToken;
import ua.com.bpgdev.movieland.dao.jdbc.JdbcMovieDao;
import ua.com.bpgdev.movieland.entity.Movie;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:property/root-context-test.xml", "classpath:property/api-context-test.xml"})
@WebAppConfiguration
public class MovieControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private JdbcMovieDao jdbcMovieDao;

    private MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAll() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/movie"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        String actualJson = mvcResult.getResponse().getContentAsString();

        Type listType = new TypeToken<ArrayList<Movie>>() {
        }.getType();
        List<Movie> actualMovies = new Gson().fromJson(actualJson, listType);
        List<Movie> expectedMovies = jdbcMovieDao.getAll();

        assertEquals(expectedMovies, actualMovies);
    }

    @Test
    public void testGetThreeRandom() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/movie/random"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        String actualJson = mvcResult.getResponse().getContentAsString();

        Type listType = new TypeToken<ArrayList<Movie>>() {
        }.getType();
        List<Movie> actualMovies = new Gson().fromJson(actualJson, listType);

        assertEquals(3, actualMovies.size());
    }
}