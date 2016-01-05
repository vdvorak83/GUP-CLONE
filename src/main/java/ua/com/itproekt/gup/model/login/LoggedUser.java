package ua.com.itproekt.gup.model.login;


import com.mongodb.DBObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class LoggedUser extends User {
    private final String profileId;


    public LoggedUser(DBObject dbObject) {

        super((String)dbObject.get("username"),
                (String)dbObject.get("password"),
                (Boolean)dbObject.get("enabled"),
                (Boolean)dbObject.get("accountNonExpired"),
                (Boolean)dbObject.get("credentialsNonExpired"),
                (Boolean)dbObject.get("accountNonLocked"),
                (List<? extends GrantedAuthority>)dbObject.get("authorities"));

        this.profileId = (String)dbObject.get("profileId");
    }


    public LoggedUser(String username, String password, boolean enabled,
                      boolean accountNonExpired, boolean credentialsNonExpired,
                      boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
                      String profileId) {

        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        this.profileId = profileId;
    }

    public String getProfileId() {
        return profileId;
    }
}
