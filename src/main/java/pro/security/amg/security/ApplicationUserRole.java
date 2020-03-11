package pro.security.amg.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static pro.security.amg.security.ApplicatioUserPermission.*;

public enum ApplicationUserRole {
    DOCTOR_ROLE(Sets.newHashSet()),
    ADMIN_ROLE(Sets.newHashSet(PERSON_READ, PERSON_WRITE));


    private final Set<ApplicatioUserPermission> permissions;

    ApplicationUserRole(Set<ApplicatioUserPermission> permissions) {
        this.permissions = permissions;
    }
}

