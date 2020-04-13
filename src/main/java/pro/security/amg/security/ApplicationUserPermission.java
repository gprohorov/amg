package pro.security.amg.security;

public enum ApplicationUserPermission {

    PERSON_READ("person:read"),
    PERSON_WRITE("person:write"),
    DOCTOR_READ("doctor:read"),
    DOCTOR_WRITE("doctor:write"),
    INTERN_READ("intern:read"),
    INTERN_WRITE("intern:write")

    ;

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
