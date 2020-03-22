package pro.security.amg.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static pro.security.amg.security.ApplicatioUserPermission.*;

public enum ApplicationUserRole {

    ADMIN(Sets.newHashSet(PERSON_READ, PERSON_WRITE, DOCTOR_READ, DOCTOR_WRITE, INTERN_READ, INTERN_WRITE)),
    DOCTOR(Sets.newHashSet(PERSON_READ, PERSON_WRITE, INTERN_READ)),
    INTERN(Sets.newHashSet(PERSON_READ)),
    PERSON(Sets.newHashSet());


    private final Set<ApplicatioUserPermission> permissions;

    ApplicationUserRole(Set<ApplicatioUserPermission> permissions) {
        this.permissions = permissions;
    }
}

