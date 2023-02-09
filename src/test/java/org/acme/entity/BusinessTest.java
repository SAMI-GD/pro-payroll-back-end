package org.acme.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessTest {

    @Test
    void testGettersAndSetters() {
        Business business = new Business();
        business.setId(1L);
        business.setName("Test Business");
        business.setLegalName("Test Legal Name");
        business.setABN("Test ABN");
        business.setACN("Test ACN");
        business.setActive(true);

        assertEquals(1L, business.getId());
        assertEquals("Test Business", business.getName());
        assertEquals("Test Legal Name", business.getLegalName());
        assertEquals("Test ABN", business.getABN());
        assertEquals("Test ACN", business.getACN());
        assertTrue(business.getActive());
    }

    @Test
    void testConstructor() {
        Business business = new Business(2L, "Test Business", "Test Legal Name", "Test ABN", "Test ACN", false);

        assertEquals(2L, business.getId());
        assertEquals("Test Business", business.getName());
        assertEquals("Test Legal Name", business.getLegalName());
        assertEquals("Test ABN", business.getABN());
        assertEquals("Test ACN", business.getACN());
        assertFalse(business.getActive());
    }

}

