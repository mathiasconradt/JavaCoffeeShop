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
        input = input.toLowerCase(Locale.ROOT);

        // Create a query using named parameters matching input to description and name
        var query = em.createNativeQuery("Select * from Product where lower(description) like :input OR lower(product_name) like :input", Product.class);
        
        // get the result list
        var resultList = (List<Product>) query.getResultList();
        // return the result list
        return resultList;
    }

}
