package ua.com.gup.rent.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import ua.com.gup.rent.model.LoggedUser;
import ua.com.gup.rent.repository.RentObjectRepository;

import java.io.Serializable;

public class RentPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private RentObjectRepository rentObjectRepository;


    @Override
    public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
        if ((auth == null) || (targetDomainObject == null) || !(permission instanceof String)) {
            return false;
        }
        String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();

//        return hasPrivilege(auth, targetType, permission.toString().toUpperCase());
        return true;
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
        if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }


//        return hasPrivilege(auth, targetType.toUpperCase(),
//                permission.toString().toUpperCase());
        return isOwner((String) targetId, ((LoggedUser) auth.getPrincipal()).getProfileId());
    }


    public boolean isOwner(String rentObjectId, String userId) {
        return rentObjectRepository.isOwner(rentObjectId, userId);
    }
}