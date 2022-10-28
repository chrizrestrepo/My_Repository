package com.spring.security.springsecuritycourse.security;

//este es el enumerador en donde se listan los diferentes permisos que se pueden tener en la aplicacion
public enum ApplicationPermissionEnum {
    STUDENT_WRITE("student:write"),
    STUDENT_READ("student:read"),
    COURSE_WRITE("course:write"),
    COURSE_READ("course:read");

    private final String permission;

    ApplicationPermissionEnum(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
