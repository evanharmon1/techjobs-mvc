package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController extends TechJobsController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", TechJobsController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        if (searchType.equals("all")) {
            model.addAttribute("columns", TechJobsController.columnChoices);
            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
            model.addAttribute("title", "All Jobs");
            model.addAttribute("jobs", jobs);
            model.addAttribute("searchType", searchType);
            return "search";
        } else {
            model.addAttribute("columns", TechJobsController.columnChoices);
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "Jobs with " + TechJobsController.columnChoices.get(searchType) + ": " + searchTerm);
            model.addAttribute("jobs", jobs);
            model.addAttribute("searchType", searchType);

            return "search";
        }
    }

}
