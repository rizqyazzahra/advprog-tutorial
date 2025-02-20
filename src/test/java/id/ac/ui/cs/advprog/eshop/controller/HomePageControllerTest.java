package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HomePageControllerTest {

    @InjectMocks
    HomePageController homePageController;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testHomePage() {
        String result = homePageController.homePage(model);
        assertEquals("HomePage", result);
        verify(model).addAttribute("title", "ADV Shop");
    }
}