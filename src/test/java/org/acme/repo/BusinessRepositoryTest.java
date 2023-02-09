package org.acme.repo;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.entity.Business;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class BusinessRepositoryTest {

    @Inject
    BusinessRepository businessRepository;

    @Test
    public void testCreateBusiness() {
        Business business = new Business();
        business.setName("Acme Inc.");
//        business.setAddress("123 Main St.");
        businessRepository.persist(business);
        assertNotNull(business.getId());
    }

    @Test
    public void testFindBusinesses() {
        List<Business> businesses = businessRepository.listAll();
        assertEquals(1, businesses.size());
        Business firstBusiness = businesses.get(0);
        assertEquals("Acme Inc.", firstBusiness.getName());
//        assertEquals("123 Main St.", firstBusiness.getAddress());
    }
}
