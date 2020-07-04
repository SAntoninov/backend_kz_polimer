package com.example.demo2.cont;

import com.example.demo2.pojos.Tasks;
import com.example.demo2.repos.TasksRepo;
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
@CrossOrigin(origins = "http://localhost:4200")
public class TasksController {

    @Autowired
    private TasksRepo tasksRepo;

    @GetMapping("/tasks/{id}")
    public Optional<Tasks> fingByTaskId(@PathVariable Long id){
        return tasksRepo.findById(id);
    }

    @RequestMapping(value = "tasks", method = RequestMethod.GET)
    public Page<Tasks> getTasksPage(@RequestParam("page") int page, @RequestParam("size") int size){
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"));
        Pageable pageable = new PageRequest(page, size, sort);
        return tasksRepo.findAll(pageable);
    }

    @RequestMapping(value = "/tasks", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.ALL_VALUE}
    )
    @ResponseBody
    public Tasks addTask(@RequestBody Tasks emp) {

        Iterable<Tasks> tasks;
        tasks = tasksRepo.findAll();
        Collection<Long> cltn = new ArrayList<>();
        tasks.forEach( s -> cltn.add(s.getId()));

        if(cltn.contains(emp.getId())){
            return tasksRepo.save(emp);
        } else {
            UUID uuid = UUID.randomUUID();
            Long l = (long) uuid.hashCode();
            emp.setId(l);
            return tasksRepo.save(emp);
        }

    }

}
