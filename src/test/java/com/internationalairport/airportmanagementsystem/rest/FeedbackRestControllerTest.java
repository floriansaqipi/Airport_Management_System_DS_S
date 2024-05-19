package com.internationalairport.airportmanagementsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internationalairport.airportmanagementsystem.dtos.post.PostFeedbackDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutFeedbackDto;
import com.internationalairport.airportmanagementsystem.entities.Feedback;
import com.internationalairport.airportmanagementsystem.service.interfaces.FeedbackService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class FeedbackRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FeedbackService feedbackService;

    @InjectMocks
    private FeedbackRestController feedbackRestController;

    private Feedback feedback;
    private PostFeedbackDto postFeedbackDto;
    private PutFeedbackDto putFeedbackDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(feedbackRestController).build();

        feedback = new Feedback("Great service!", "Approved");
        feedback.setFeedbackId(1);
        postFeedbackDto = new PostFeedbackDto("Great service!", "Approved", 1, 1);
        putFeedbackDto = new PutFeedbackDto(1, "Great service!", "Approved", 1, 1);
    }

    @Test
    public void testFindAll() throws Exception {
        List<Feedback> feedbackList = Arrays.asList(feedback);
        when(feedbackService.findAll()).thenReturn(feedbackList);

        mockMvc.perform(get("/api/public/feedbacks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].feedbackId", CoreMatchers.is(feedback.getFeedbackId())))
                .andExpect(jsonPath("$[0].content", CoreMatchers.is(feedback.getContent())))
                .andExpect(jsonPath("$[0].status", CoreMatchers.is(feedback.getStatus())));
    }

    @Test
    public void testGetFeedback() throws Exception {
        when(feedbackService.findById(1)).thenReturn(feedback);

        mockMvc.perform(get("/api/public/feedbacks/{feedbackId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.feedbackId", CoreMatchers.is(feedback.getFeedbackId())))
                .andExpect(jsonPath("$.content", CoreMatchers.is(feedback.getContent())))
                .andExpect(jsonPath("$.status", CoreMatchers.is(feedback.getStatus())));
    }

    @Test
    public void testAddFeedback() throws Exception {
        when(feedbackService.save(ArgumentMatchers.any(PostFeedbackDto.class))).thenReturn(feedback);

        mockMvc.perform(post("/api/private/feedbacks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(postFeedbackDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.feedbackId", CoreMatchers.is(feedback.getFeedbackId())))
                .andExpect(jsonPath("$.content", CoreMatchers.is(feedback.getContent())))
                .andExpect(jsonPath("$.status", CoreMatchers.is(feedback.getStatus())));
    }

    @Test
    public void testUpdateFeedback() throws Exception {
        when(feedbackService.save(ArgumentMatchers.any(PutFeedbackDto.class))).thenReturn(feedback);

        mockMvc.perform(put("/api/private/feedbacks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(putFeedbackDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.feedbackId", CoreMatchers.is(feedback.getFeedbackId())))
                .andExpect(jsonPath("$.content", CoreMatchers.is(feedback.getContent())))
                .andExpect(jsonPath("$.status", CoreMatchers.is(feedback.getStatus())));
    }

    @Test
    public void testDeleteFeedback() throws Exception {
        int feedbackId = 1;
        when(feedbackService.findById(feedbackId)).thenReturn(feedback);
        doNothing().when(feedbackService).deleteById(feedbackId);

        ResultActions response = mockMvc.perform(delete("/api/private/feedbacks/{id}", feedbackId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted feedback id - " + feedbackId));

    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
