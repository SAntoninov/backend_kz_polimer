package com.example.demo2.cont;

import com.example.demo2.pojos.PhysicalProperties;
import com.example.demo2.pojos.Products;
import com.example.demo2.repos.PhysicalPropertiesRepo;
import com.example.demo2.repos.ProdRepo;
import com.example.demo2.repos.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin
public class ProductsController {

    @Autowired
    private PhysicalPropertiesRepo physicalPropertiesRepo;

    @Autowired
    private ProductsRepo productsRepo;

    @Autowired
    private ProdRepo prodRepo;


    @GetMapping("/physicalProperties")
    public Iterable<PhysicalProperties> getPhysicalProperties(){
        return physicalPropertiesRepo.findAll();
    }

    @GetMapping("/product")
    public Iterable<Products> getProducts(){
        return productsRepo.findAll();
    }

    @GetMapping("/products/{id}")
    public Optional<Products> fingByProductId(@PathVariable Long id){
        return productsRepo.findById(id);
    }

    @RequestMapping(value = "products", method = RequestMethod.GET)
    public Page<Products> getProductsPage(@RequestParam("page") int page, @RequestParam("size") int size){
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"));
        Pageable pageable = new PageRequest(page, size, sort);
        return productsRepo.findAll(pageable);
    }

    @RequestMapping(value = "/products", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.ALL_VALUE}
    )
    @ResponseBody
    public Products addProduct(@RequestBody Products emp) {

        Iterable<Products> suppliersList;
        suppliersList = productsRepo.findAll();
        Collection<Long> cltn = new ArrayList<>();
        suppliersList.forEach( s -> cltn.add(s.getId()));

        if(cltn.contains(emp.getId())){
            return productsRepo.save(emp);
        } else {
            UUID uuid = UUID.randomUUID();
            Long l = (long) uuid.hashCode();
            emp.setId(l);
            return productsRepo.save(emp);
        }

    }

}
