package com.spring.security.springsecuritycourse.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.spring.security.springsecuritycourse.security.ApplicationPermissionEnum.*;

//este enumerador es el encargado de definir los distintos roles existentes con sus permisos
public enum ApplicationRolesEnum {
    ADMIN(Set.of(STUDENT_WRITE, STUDENT_READ, COURSE_WRITE, COURSE_READ)),
    ADMINTRAINEE(Set.of(STUDENT_WRITE, STUDENT_READ, COURSE_READ)),
    STUDENT(Set.of(STUDENT_READ, COURSE_READ));

    private final Set<ApplicationPermissionEnum> permissions;

    ApplicationRolesEnum(Set<ApplicationPermissionEnum> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationPermissionEnum> getPermissions() {
        return permissions;
    }

    //este metodo es aquel que crea los permisos para cada role a partir del enumerador de permisos exietntes
    //y los devuelve en set<SimpleGrantedAuthority> (la clase: SimpleGrantedAuthority es una implementacion de la interfaz GrantedAuthority)
    //esta clase crea los distintos permisos y ademas tambien es la encargada de crear los roles (Spring crea los roles con el perfijo ROLE_ + {nombre-role})
    public Set<SimpleGrantedAuthority> getGrantedAutories(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_".concat(this.name())));
        return permissions;
    }
}
