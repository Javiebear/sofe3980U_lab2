package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

   //this tests when the first operand is selected
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
    //this is testing after the user has focused on an operand1
	    @Test
    public void getParameterOperand1() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }


    //this tests the if the add function works properly with the user submitted values and prints out the correct output
	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }

    //this is testing that one the view is changed, the operand2 is also the same value
    @Test
    public void postParameter2() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1110"))
            .andExpect(model().attribute("operand2", "111"));
    }

        //this is testing that one the view is changed, the operand is also the same value
        @Test
        public void postParameter3() throws Exception {
            this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1110"))
                .andExpect(model().attribute("operator", "+"));
        }

    //this tests if all heading zeros are removed when adding
    @Test
    public void postParameterAdd() throws Exception {
        this.mvc.perform(post("/").param("operand1","11").param("operator","+").param("operand2","000000"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "11"))
            .andExpect(model().attribute("operand1", "11"));
    }


    // New Operation tests
    //takes the submitted parameters
    //makes sure they are registered to the right operand 
    //makes sure the value for result is correct
    //this tests the if the or function works properly with the user submitted values and prints out the correct output
	@Test
	    public void postParameterOr() throws Exception {
        this.mvc.perform(post("/").param("operand1","1001").param("operator","|").param("operand2","1111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1111"))
			.andExpect(model().attribute("operand1", "1001"));
    }

    //testing if or works with 0's
	@Test
	    public void postParameterOr2() throws Exception {
        this.mvc.perform(post("/").param("operand1","0").param("operator","|").param("operand2","0"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "0"))
			.andExpect(model().attribute("operand1", "0"));
    }

    //this tests the if the and function works properly with the user submitted values and prints out the correct output
	@Test
	    public void postParameterAnd() throws Exception {
        this.mvc.perform(post("/").param("operand1","101").param("operator","&").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "101"))
			.andExpect(model().attribute("operand1", "101"));
    }

    //this tests if the trailing 0s are removed after anding with 0's
	@Test
	    public void postParameterAnd2() throws Exception {
        this.mvc.perform(post("/").param("operand1","101").param("operator","&").param("operand2","0"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "0"))
			.andExpect(model().attribute("operand1", "101"));
    }


    //this tests the if the multiply function works properly with the user submitted values and prints out the correct output
	@Test
	    public void postParameterMultiply() throws Exception {
        this.mvc.perform(post("/").param("operand1","100").param("operator","*").param("operand2","11"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1100"))
			.andExpect(model().attribute("operand1", "100"));
    }

    //this will test the multiplication of 1's and 0's
    @Test
    public void postParameterMultiply2() throws Exception {
    this.mvc.perform(post("/").param("operand1","111").param("operator","*").param("operand2","0"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "0"))
        .andExpect(model().attribute("operand1", "111"));
    }
}