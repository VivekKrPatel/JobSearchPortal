package com.geekster.JobSearchPortal.controller;

import com.geekster.JobSearchPortal.model.Job;
import com.geekster.JobSearchPortal.model.JobType;
import com.geekster.JobSearchPortal.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/job")
public class JobController {

    @Autowired
    JobService jobService;

    @GetMapping(value = "/jobs")
    public Iterable<Job> getAllJobs(){
        return jobService.getAllJobs();
    }

    @PostMapping(value = "/jobs")
    public String addJobs(@Valid @RequestBody List<Job> jobList){
        return jobService.insertJobs(jobList);
    }

    @DeleteMapping(value = "/byId/{id}")
    public void removeJobById(@PathVariable Long id){
        jobService.deleteSpecificJobById(id);
    }

//    custom finder fetch data through employer name
    @GetMapping(value="/byEmployerName/{employerName}")
    public List<Job> getByEmployerName(@PathVariable String employerName){
        return jobService.fetchByEmployerName(employerName);
    }

    //    custom finder fetch data through employer name
    @GetMapping(value="/bySalary/{salary}")
    public List<Job> getBySalary(@PathVariable Double salary){
        return jobService.getJobBySalary(salary);
    }

    @GetMapping(value="/byLocation/{location}")
    public List<Job> getByLocation(@PathVariable String location){
        return jobService.getJobByLocation(location);
    }

    @GetMapping(value = "/byCompanyName/{companyName}")
    public List<Job> getByCompanyName(@PathVariable String companyName){
        return jobService.getJobBycompanyName(companyName);
    }

    @PutMapping(value = "job")
    public String updateJob(@RequestBody Job updatedJob){
        return jobService.updateJobById(updatedJob);
    }

//    custom queries
    @PutMapping(value = "/location/{location}/BySalary/{salary}")
    public void updateLocationBasedOnSalary(@PathVariable Double salary){
        jobService.updateLocationBasedOnSalary(salary);
    }
    @PutMapping(value = "/salary/{salary}/ByJobType/{jobType}")
    public void updateSalaryBasedOnJobType( @PathVariable JobType jobType){
        jobService.updateSalaryBasedOnJobType(jobType);
    }

    @DeleteMapping(value = "/byLocation/{location}")
    public void deleteJobBasedOnLocation(@PathVariable String location){
        jobService.removeJobBasedOnLocation(location);
    }

    @PutMapping(value = "/jobType/{jobType}/byDescription/{description}")
    public void updateJobTypeBasedOnDescription(@PathVariable JobType jobType,String description){
        jobService.filterJobTypeBasedOnDescription(jobType,description);
    }
}
