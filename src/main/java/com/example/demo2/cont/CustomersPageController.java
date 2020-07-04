package com.example.demo2.cont;

import com.example.demo2.pojos.Customers;
import com.example.demo2.repos.CustomersRepo;
import com.example.demo2.repos.UserRepoCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin
public class CustomersPageController {

    @Autowired
    CustomersRepo customersRepo;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    UserRepoCategory userRepository;

    @GetMapping("/customers/{id}")
    public Optional<Customers> fingByIdCustomer(@PathVariable Long id){
        return customersRepo.findById(id);
    }

    @GetMapping("/customers")
    public Page<Customers> getCustomerPage(@RequestParam("page") int page, @RequestParam("size") int size){
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"));
        Pageable pageable = new PageRequest(page, size, sort);
        return customersRepo.findAll(pageable);
    }


    @RequestMapping(value = "/customers", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.ALL_VALUE}
    )
    @ResponseBody
//    public Customers addCustomer(@RequestBody Customers emp, Principal principal, @PathVariable("username") String username) {
    public Customers addCustomer(@RequestBody Customers emp, Principal principal) {

        emp.setDate(new Timestamp(1));
        Iterable<Customers> suppliersList;
        suppliersList = customersRepo.findAll();
        Collection<Long> cltn = new ArrayList<>();
        suppliersList.forEach( s -> cltn.add(s.getId()));

        if(cltn.contains(emp.getId())){
            return customersRepo.save(emp);
        } else {
            UUID uuid = UUID.randomUUID();
            Long l = (long) uuid.hashCode();
            emp.setId(l);
            return customersRepo.save(emp);
        }

    }

}
