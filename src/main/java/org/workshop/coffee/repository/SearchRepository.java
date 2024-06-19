package org.workshop.coffee.repository;

import org.workshop.coffee.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;

@Repository
public class SearchRepository {

    @Autowired
    EntityManager em;

    @Autowired
    DataSource dataSource;

    public List<Product> searchProduct (String input) {
        // lower the input
        var lowerInput = input.toLowerCase(Locale.ROOT);

        // create a query using named parameters matching input to description and name
        var query = em.createQuery("Select p from Product p where lower(p.description) like :input OR lower(p.productName) like :input", Product.class);
        // set parameters on the query
        query.setParameter("input", "%" + lowerInput + "%");


        // get the result list
        var resultList = (List<Product>) query.getResultList();
        // return the result list
        return resultList;
    }

}
