package ua.com.bpgdev.movieland.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import ua.com.bpgdev.movieland.dao.jdbc.JdbcMovieDao;
import ua.com.bpgdev.movieland.entity.Movie;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:property/applicationContext-test.xml", "classpath:property/apiContext-test.xml"})
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
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();
        String actualJson = mvcResult.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        TypeReference listMovies = new TypeReference<List<Movie>>() {
        };
        List<Movie> actualMovies = mapper.readValue(actualJson, listMovies);

        List<Movie> expectedMovies = jdbcMovieDao.getAll();

        assertEquals(expectedMovies, actualMovies);
    }

    @Test
    public void testGetThreeRandom() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/movie/random"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        String actualJson = mvcResult.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        TypeReference listMovies = new TypeReference<List<Movie>>() {
        };
        List<Movie> actualMovies = mapper.readValue(actualJson, listMovies);

        assertEquals(3, actualMovies.size());
    }

    @Test
    public void testGetByGenre() throws Exception {
        int genreId = 2;
        MvcResult mvcResult = mockMvc
                .perform(get("/movie/genre/" + genreId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();
        String actualJson = mvcResult.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        TypeReference listMovies = new TypeReference<List<Movie>>() {
        };
        List<Movie> actualMovies = mapper.readValue(actualJson, listMovies);
        List<Movie> expectedMovies = jdbcMovieDao.getByGenreId(genreId);
        assertEquals(7, actualMovies.size());
        assertEquals(expectedMovies, actualMovies);
    }
}