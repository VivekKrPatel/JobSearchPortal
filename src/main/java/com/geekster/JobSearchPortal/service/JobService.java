package com.geekster.JobSearchPortal.service;

import com.geekster.JobSearchPortal.model.Job;
import com.geekster.JobSearchPortal.model.JobType;
import com.geekster.JobSearchPortal.repository.IJobRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    IJobRepo iJobRepo;


    public Iterable<Job> getAllJobs() {
        return iJobRepo.findAll();
    }


    public String insertJobs(List<Job> jobList) {
        Iterable<Job> addedJobs = iJobRepo.saveAll(jobList);
        if(addedJobs != null){
            return "added jobs succesfully.!!!";
        }
        return "Failed to add Jobs.!!!";
    }

    public void deleteSpecificJobById(Long id) {
        iJobRepo.deleteById(id);
    }

    public List<Job> fetchByEmployerName(String employerName) {
        return iJobRepo.findByEmployerName(employerName);
    }

    public List<Job> getJobBySalary(Double salary) {
        return iJobRepo.findBySalaryLessThanEqual(salary);
    }

    public List<Job> getJobByLocation(String location) {
        return iJobRepo.findByLocation(location);
    }


    public List<Job> getJobBycompanyName(String companyName) {
        return iJobRepo.findByCompanyName(companyName);
    }

    public String updateJobById(Job updatedJob) {
        Job oldJob = iJobRepo.findById(updatedJob.getId()).orElse(null);
        if(oldJob != null){
            iJobRepo.save(updatedJob);
            return "Job updated successfully.!!!!";
        }
        else {
            return "Id is not valid! kindly give it valid..!!!";
        }
    }


    @Transactional
    public void updateLocationBasedOnSalary(Double salary) {
        iJobRepo.updateLocationBasedOnSalary(salary);
    }

    @Transactional
    public void updateSalaryBasedOnJobType(JobType jobType) {
        iJobRepo.updateSalaryByJobType(jobType);
    }

    @Transactional
    public void removeJobBasedOnLocation(String location) {
        iJobRepo.removeByLocation(location);
    }

    @Transactional
    public void filterJobTypeBasedOnDescription(JobType jobType, String description) {
        iJobRepo.updateJobTypeBasedOnDescription(jobType,description);
    }
}
