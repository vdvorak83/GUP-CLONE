package ua.com.itproekt.gup.api.rest.loginAndSignUp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.login.FormLoggedUser;
import ua.com.itproekt.gup.model.login.LoggedUser;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.CookieUtil;
import ua.com.itproekt.gup.util.LogUtil;
import ua.com.itproekt.gup.util.Oauth2Util;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

//@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class LoginRestController {
	private final static Logger LOG = Logger.getLogger(LoginRestController.class);

	@Autowired
	ProfilesService profileService;

	@Autowired
	private DefaultTokenServices tokenServices;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Qualifier("userDetailsServiceImpl")
	@Autowired
	UserDetailsService userDetailsService;

//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ResponseEntity<Void> login(@RequestParam String email, @RequestParam String password, HttpServletResponse response) {
//		LoggedUser loggedUser;
//		try {
//			loggedUser = (LoggedUser)userDetailsService.loadUserByUsername(email);
//		} catch (UsernameNotFoundException ex) {
//			LOG.debug("Incorrect email: " + LogUtil.getExceptionStackTrace(ex));
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//
//		if (!passwordEncoder.matches(password, loggedUser.getPassword())) {
//			LOG.debug("Password doesn't match: email [" + email + "]");
//			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//		}
//
//		authenticateByEmailAndPassword(loggedUser, response);
//		LOG.debug("Login profile email : [" + email + "]");
//
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Void> login(@RequestBody FormLoggedUser formLoggedUser, HttpServletResponse response) {
        LoggedUser loggedUser;
        try {
            loggedUser = (LoggedUser)userDetailsService.loadUserByUsername(formLoggedUser.getEmail());
        } catch (UsernameNotFoundException ex) {
            LOG.debug("Incorrect email: " + LogUtil.getExceptionStackTrace(ex));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!passwordEncoder.matches(formLoggedUser.getPassword(), loggedUser.getPassword())) {
            LOG.debug("Password doesn't match: email [" + formLoggedUser.getEmail() + "]");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        authenticateByEmailAndPassword(loggedUser, response);
        LOG.debug("Login profile email : [" + formLoggedUser.getEmail() + "]");

        return new ResponseEntity<>(HttpStatus.OK);
    }

	private void authenticateByEmailAndPassword(User user, HttpServletResponse response) {
		Authentication userAuthentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
		OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(Oauth2Util.getOAuth2Request(), userAuthentication);
		OAuth2AccessToken oAuth2AccessToken = tokenServices.createAccessToken(oAuth2Authentication);

		CookieUtil.addCookie(response, Oauth2Util.ACCESS_TOKEN_COOKIE_NAME, oAuth2AccessToken.getValue(), Oauth2Util.ACCESS_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
		CookieUtil.addCookie(response, Oauth2Util.REFRESH_TOKEN_COOKIE_NAME, oAuth2AccessToken.getRefreshToken().getValue(), Oauth2Util.REFRESH_TOKEN_COOKIE_EXPIRES_IN_SECONDS);
	}

//		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);
//			Authenticate the user
//		Authentication authentication = authenticationManager.authenticate(authRequest);
//		SecurityContext securityContext = SecurityContextHolder.getContext();
//		securityContext.setAuthentication(authentication);
//
//			Create a new session and add the security context.
//		HttpSession session = request.getSession(true);
//		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);


/*--------------------------------------- Check -----------------------------------------------------------------*/
	@RequestMapping(value = "/login/checkEmail", method = RequestMethod.POST)
	public String existEmailCheck(@RequestBody String email) {

		email = email.split("=")[0];

		try {
			email = URLDecoder.decode(email, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			LOG.error(LogUtil.getExceptionStackTrace(ex));
		}

		return (profileService.profileExistsWithEmail(email)) ? Boolean.TRUE.toString() : Boolean.FALSE.toString();
	}

}