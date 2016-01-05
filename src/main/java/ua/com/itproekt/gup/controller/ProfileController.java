package ua.com.itproekt.gup.controller;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SecurityOperations;

import java.net.URI;
import java.util.HashSet;

/**
 * Created by Optical Illusion on 17.11.2015.
 */

@Controller
public class ProfileController {

    @Autowired
    ProfilesService profilesService;

    @Autowired
    ActivityFeedService activityFeedService;


    //----------------------------------- show registration page  ------
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showPage(Model model) {
        return "registration";
    }


    //----------------------------------- read profile for edit-profile page  ------
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit-profile/{id}", method = RequestMethod.GET)
    public String editProfilePageById(Model model, @PathVariable("id") String id) {
        Profile profile = new Profile();
        try {
            profile = profilesService.findById(id);
        } catch (Exception e) {
            System.out.println("Can't read profile by id: " + id);
            e.printStackTrace();
        }

        model.addAttribute("profile", profile);
        return "edit-profile";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/edit-profile", method = RequestMethod.GET)
    public String editProfilePage(Model model) {
        Profile profile = new Profile();
        String userId;

        if (SecurityOperations.isUserLoggedIn()) {
            userId = SecurityOperations.getLoggedUserId();
        } else {

            return "login";
        }


        try {
            profile = profilesService.findById(userId);
        } catch (Exception e) {
            System.out.println("Can't read profile by id: " +userId);
            e.printStackTrace();
        }

        model.addAttribute("profile", profile);
        return "edit-profile";
    }

}
