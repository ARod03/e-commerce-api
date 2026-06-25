package org.yearup.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yearup.models.Product;
import org.yearup.repository.ProductRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void search_shouldReturnNonFeaturedProducts(){

        //Arrange
        Product notFeatured = new Product();
        notFeatured.setFeatured(false);
        notFeatured.setPrice(67);

        List<Product> allProducts = List.of(notFeatured);

        Mockito.when(productRepository.findAll()).thenReturn(allProducts);

        //Act
        List<Product> results = productService.search(null, null, null, null);

        //Assert
        assertEquals(1, results.size());
        assertTrue(results.contains(notFeatured));


    }

}