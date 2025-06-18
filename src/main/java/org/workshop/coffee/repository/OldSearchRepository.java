package org.workshop.coffee.repository;

import org.workshop.coffee.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;

@Repository
public class OldSearchRepository {

    @Autowired
    EntityManager em;

    @Autowired
    DataSource dataSource;

    public List<Product> searchProduct (String input) {
        var lowerInput = input.toLowerCase(Locale.ROOT);
        var query = em.createNativeQuery("Select * from Product where lower(description) like '%" + lowerInput + "%' OR lower(product_name) like '%" + lowerInput + "%'", Product.class);
        var resultList = (List<Product>) query.getResultList();
        return resultList;
    }

    public List searchProduct2 (String input) {
        var lowerInput = input.toLowerCase(Locale.ROOT);
        var query = em.createNativeQuery(String.format("Select * from Product where lower(description) like '%%%s%%' OR lower(product_name) like '%%%s%%'", lowerInput, lowerInput), Product.class);
        List resultList;
        resultList = query.getResultList();
        return resultList;
    }


}
