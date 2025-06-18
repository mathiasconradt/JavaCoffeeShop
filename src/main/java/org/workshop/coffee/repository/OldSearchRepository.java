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

    public boolean doSomething() {
        var token = "eyJhbGciOiJub25lIiwidHlwIjoiSldUIn0.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNzE4NzI0NDgzLCJjdXN0b21fZGF0YSI6IlRoaXMgaXMgc29tZSBjdXN0b20gZGF0YSJ9";
        return token == null;
    }

    public boolean doSomething2() {
        var token2 = "eyJhbGciOiJub25lIiwidHlwIjoiSldUIn0.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNzE4NzI0NDgzLCJjdXN0b21fZGF0YSI6IlRoaXMgaXMgc29tZSBjdXN0b20gZGF0YSJ9";
        return token2 == null;
    }

    public boolean doSomething3() {
        var jwt = "eyJhbGciOiJub25lIiwidHlwIjoiSldUIn0.eyJzdWIiOiJ1c2VyMTIzIiwibmFtZSI6IkphbmUgRG9lIiwiaXNzIjoiZXhhbXBsZS5jb20iLCJpYXQiOjE3MTg3Mjg0MDEsImN1c3RvbV9maWVsZCI6IlRoaXMgZGF0YSBpcyBqdXN0IGZvciBkZW1vbnN0cmF0aW9uIn0";
        return jwt == null;
    }

}
