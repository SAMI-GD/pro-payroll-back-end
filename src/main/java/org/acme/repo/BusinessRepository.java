package org.acme.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.entity.Business;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BusinessRepository implements PanacheRepository<Business> {
}
