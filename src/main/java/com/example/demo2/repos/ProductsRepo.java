package com.example.demo2.repos;

import com.example.demo2.pojos.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductsRepo extends JpaRepository<Products, Long> {

//    @Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?1", nativeQuery = true)
//    User findByEmailAddress(String emailAddress);

    // эм, не помню что сдесь делал. С очередями игрался кажется
    @Transactional
    @Modifying                                                //  "тут подзапрос типа в бд"
//    @Query("update Products prod set prod.supplier = null where (select * from Suppliers sup where sup.id = id)")
//    @Query("update Products prod set prod.supplier = null where prod.supplier.id = ?1")
    @Query(value = "update products set suppliers_id = null where suppliers_id = ?1", nativeQuery = true)
    int setSupplierById(Long id);

//    @Transactional
//    @Modifying                                                //  "тут подзапрос типа в бд"
////    @Query("update Products prod set prod.supplier = null where (select * from Suppliers sup where sup.id = id)")
////    @Query("update Products prod set prod.supplier = null where prod.supplier.id = ?1")
//    @Query(value = "select id from products where suppliers_id = ?1", nativeQuery = true)
//    int setSupplierById(Long id);
}
