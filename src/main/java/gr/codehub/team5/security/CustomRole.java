package gr.codehub.team5.security;

/**
 * Defining the Roles for our clients
 */
public enum CustomRole {
    ROLE_NA("n/a"),
    ROLE_DOCTOR("doctor"),
    ROLE_PATIENT("patient"),
    ROLE_CHIEFDOCTOR("chiefDoctor");

    private final String roleName;

    CustomRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static CustomRole getRoleValue(String roleParameter) {
        for (CustomRole role : CustomRole.values()) {
            if (roleParameter.equals(role.getRoleName()))
                return role;
        }
        return ROLE_NA;
    }

}
