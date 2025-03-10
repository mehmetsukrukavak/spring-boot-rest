package com.mehmetsukrukavak.spring_boot_rest.controller;

import com.mehmetsukrukavak.spring_boot_rest.model.JobPost;
import com.mehmetsukrukavak.spring_boot_rest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobRestController {

    @Autowired
    private JobService jobService;

    @GetMapping("jobPosts")
    public List<JobPost> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("jobPost/{Id}")
    public JobPost getJobById(@PathVariable int Id) {
        return jobService.getJob(Id);
    }
    @PostMapping("jobPost")
    public JobPost  addJob(@RequestBody JobPost jobPost) {
        jobService.addJob(jobPost);
        return jobService.getJob(jobPost.getPostId());
    }
    @PutMapping("jobPost")
    public JobPost  updateJob(@RequestBody JobPost jobPost) {
        jobService.updateJob(jobPost);
        return jobService.getJob(jobPost.getPostId());
    }

    @DeleteMapping("jobPost/{Id}")
    public String  deleteJob(@PathVariable int Id) {
        jobService.deleteJob(Id);
        return "Deleted";
    }

    @GetMapping("load")
    public String loadData(){
        jobService.load();
        return "Successfully loaded";
    }

    @GetMapping("jobPosts/keyword/{keyword}")
    public List<JobPost> searcByKeyword(@PathVariable("keyword") String keyword) {
        return jobService.search(keyword);
    }
}
