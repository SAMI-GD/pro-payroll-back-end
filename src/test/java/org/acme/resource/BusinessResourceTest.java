package org.acme.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.entity.Business;
import org.acme.repo.BusinessRepository;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.net.URI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@Transactional
public class BusinessResourceTest {
    @Inject
    BusinessRepository businessRepository;

    @Test
    public void testGetAllBusinesses() {
        // Given
        Business business = new Business();
        business.setName("Test Business");
        business.setLegalName("Test Legal Name");
        business.setABN("123456789");
        business.setACN("987654321");
        businessRepository.persist(business);

        // When
        Response response = (Response) given()
                .when().get("/business/all")
                .then()
                .statusCode(200)
                .extract().response();

        // Then
        Business[] businesses = response.readEntity(Business[].class);
        assertEquals(1, businesses.length);
        assertEquals("Test Business", businesses[0].getName());
        assertEquals("Test Legal Name", businesses[0].getLegalName());
        assertEquals("123456789", businesses[0].getABN());
        assertEquals("987654321", businesses[0].getACN());
    }

    @Test
    public void testAddBusiness() {
        // Given
        Business business = new Business();
        business.setName("Test Business");
        business.setLegalName("Test Legal Name");
        business.setABN("123456789");
        business.setACN("987654321");

        // When
        Response response = (Response) given()
                .when().contentType("application/json")
                .body(business)
                .post("/business/add")
                .then()
                .statusCode(201)
                .header("Location", is(notNullValue()))
                .extract().response();

        URI location = response.getLocation();

        // Then
        Business createdBusiness = given()
                .when().get(location)
                .then()
                .statusCode(200)
                .extract().as(Business.class);
        assertEquals("Test Business", createdBusiness.getName());
        assertEquals("Test Legal Name", createdBusiness.getLegalName());
        assertEquals("123456789", createdBusiness.getABN());
        assertEquals("987654321", createdBusiness.getACN());
    }

    @Test
    public void testDeleteBusiness() {
        Business business = new Business();
        business.setName("Test Business");
        business.setLegalName("Test Legal Name");
        business.setABN("12345678901");
        business.setACN("987654321");

        businessRepository.persist(business);
        Long id = business.getId();

        given().when().delete("/business/delete/" + id).then().statusCode(200);

        assertEquals(0, businessRepository.listAll().size());
    }

}

