package pro.security.amg.security;

public enum ApplicatioUserPermission {

    PERSON_READ("person:read"),

    PERSON_WRITE("person:write");

    private final String permission;

    ApplicatioUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
