package com.example.demo2.cont;

import com.example.demo2.auth.auth.InDatabase.DAOUser;
import com.example.demo2.auth.auth.JwtUserDetailsService;
import com.example.demo2.hib.Output;
import com.example.demo2.pojos.*;
import com.example.demo2.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.*;

@RestController
@CrossOrigin
public class Controller {

    @Autowired
    private ProductsRepo productsRepo;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    UserRepoCategory userRepository;

    @Autowired
    CategoriesRepo categoriesRepo;

    @Autowired
    ApplicationAreasRepo applicationAreasRepo;

    @Autowired
    CommentsRepo commentsRepo;

    @Autowired
    SuppliersRepo suppliersRepo;

    @Autowired
    CustomersRepo customersRepo;

    @RequestMapping("/suppliers/{id}")
    public Optional<Suppliers> fingByIdSup(@PathVariable Long id){
        return suppliersRepo.findById(id);
    }

    @RequestMapping(value = "/suppliers/delete", method = RequestMethod.POST)
    public void deleteSupById(@RequestBody Suppliers emp){
        suppliersRepo.deleteById(emp.getId());
    }


    @RequestMapping(value = "/suppliers", method = RequestMethod.GET)
    public Iterable<Suppliers> suppliers(RestTemplate restTemplate){
        return suppliersRepo.findAll();
    }

    @RequestMapping(value = "/suppliers", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.ALL_VALUE}
            )
    @ResponseBody
    public Suppliers addSupplier(@RequestBody Suppliers emp) {

        emp.setDate(new Timestamp(1));

        Iterable<Suppliers> suppliersList;
        suppliersList = suppliersRepo.findAll();
        Collection<Long> cltn = new ArrayList<>();
        suppliersList.forEach( s -> cltn.add(s.getId()));

        // TODO temporary desigion with uuid and timestamp, rewrite
        if(cltn.contains(emp.getId())){
            return suppliersRepo.save(emp);
        } else {
            UUID uuid = UUID.randomUUID();
            Long l = (long) uuid.hashCode();
            emp.setId(l);
            return suppliersRepo.save(emp);
        }

    }


    @RequestMapping(value = "/users", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.ALL_VALUE}
    )
    @ResponseBody
    public User addUsers(@RequestBody User emp) {

        Iterable<User> suppliersList;
        suppliersList = userRepository.findAll();
        Collection<Long> cltn = new ArrayList<>();
        suppliersList.forEach( s -> cltn.add(s.getId()));

        DAOUser daoUser = new DAOUser();
        daoUser.setUsername(emp.getEmail());
        daoUser.setPassword(emp.getPassword());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        emp.setBirthDate(timestamp);

        if(cltn.contains(emp.getId())){
            userDetailsService.loadByUserName(daoUser, emp.getId());
            return userRepository.save(emp);
        } else {
            UUID uuid = UUID.randomUUID();
            Long l = (long) uuid.hashCode();
            emp.setId(l);
            userDetailsService.save(daoUser);
            return userRepository.save(emp);
        }

    }

    @RequestMapping("/comment")
    public Optional<?> comment(RestTemplate restTemplate){
        return commentsRepo.findById((long) 1);
    }

    @RequestMapping("/comments")
    public Iterable<Comments> comments(RestTemplate restTemplate){
        return commentsRepo.findAll();
    }


    @RequestMapping("/user_category/{id}")
    public Optional<?> userCategory(RestTemplate restTemplate, @PathVariable Long id){
        return userRepository.findById(id);
    }

    @RequestMapping("/user_category1/{username}")
    public Optional<?> userName(RestTemplate restTemplate, @PathVariable("username") String username){
        return userRepository.findByEmail(username);
    }

    @RequestMapping("/api/user")
    public Iterable<com.example.demo2.pojos.User> users(RestTemplate restTemplate){
        return userRepository.findAll();
    }

    @RequestMapping("/category")
    public Optional<?> category(RestTemplate restTemplate){
        return categoriesRepo.findById((long) 1);
    }

    @RequestMapping("/applicationAreas")
    public Iterable<ApplicationArea> applicationAreas(){
        return applicationAreasRepo.findAll();
    }


    @RequestMapping(value = "/applicationAreasAdd", method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.ALL_VALUE}
    )
    @ResponseBody
    public ApplicationArea addApplicationAreas(@RequestBody ApplicationArea applicationArea) {
        Iterable<ApplicationArea> applicationAreas = applicationAreasRepo.findAll();
        Collection<Long> cltn = new ArrayList<>();
        applicationAreas.forEach( s -> cltn.add(s.getId()));
        UUID uuid = UUID.randomUUID();
        applicationArea.setId((long) uuid.hashCode());
        return applicationAreasRepo.save(applicationArea);
    }

    @RequestMapping("/applicationAreasDelete/{id}")
    public void deleteApplicationAreas(@PathVariable Long id){
        // TODO ну вообще в идеале возвращать оптионал с кодом ответа.
        applicationAreasRepo.deleteById(id);
    }

    @RequestMapping("/categories")
    public Iterable<Category> categories(){
        return categoriesRepo.findAll();
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.ALL_VALUE}
    )
    @ResponseBody
    public Category addCategory(@RequestBody Category category) {
        System.out.println(category); // TODO проверка на добавление одинаковых категорий
        UUID uuid = UUID.randomUUID(); // TODO может сгенерить существующий Id-шник все-таки
        category.setId((long) uuid.hashCode());
        return categoriesRepo.save(category);
    }

    @RequestMapping("/categoriesDelete/{id}")
    public void deleteCategories(@PathVariable Long id){
        // TODO ну вообще в идеале возвращать оптионал с кодом ответа.
        categoriesRepo.deleteById(id);
    }

    @RequestMapping("/")
    public Output index(RestTemplate restTemplate){
        // TODO почистить как-нибудь здесь все
        Output output = new Output();
        output.setValue((float) 1);
        return output;
    }

}
