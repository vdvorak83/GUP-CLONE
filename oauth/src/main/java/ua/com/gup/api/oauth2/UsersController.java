/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.api.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.dto.profile.ProfileDTO;
import ua.com.gup.mongo.model.login.LoggedUser;
import ua.com.gup.mongo.model.profiles.ProfileFilterOptions;
import ua.com.gup.service.profile.ProfilesService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class UsersController {

    @Autowired
    private ProfilesService profilesService;

    @GetMapping(path = "/exists")
    public ResponseEntity userExists(@RequestParam(name = "login") String login) {
        boolean profileExistsWithEmail = profilesService.profileExistsWithEmail(login.toLowerCase());
        return new ResponseEntity(profileExistsWithEmail, HttpStatus.OK);
    }

    @GetMapping(path = "/principal")
    public @ResponseBody
    LoggedUser getUser(OAuth2Authentication oAuth2Authentication) {
        return (LoggedUser) oAuth2Authentication.getPrincipal();
    }


}