package com.spring.security.springsecuritycourse.security;

import java.util.Set;
import static com.spring.security.springsecuritycourse.security.ApplicationPermissionEnum.*;

//este enumerador es el encargado de definir los distintos roles existentes con sus permisos
public enum ApplicationRolesEnum {
    ADMIN(Set.of(STUDENT_WRITE, STUDENT_READ, COURSE_WRITE, COURSE_READ)),
    STUDENT(Set.of(STUDENT_WRITE, STUDENT_READ, COURSE_READ));

    private final Set<ApplicationPermissionEnum> permissions;

    ApplicationRolesEnum(Set<ApplicationPermissionEnum> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationPermissionEnum> getPermissions() {
        return permissions;
    }
}
