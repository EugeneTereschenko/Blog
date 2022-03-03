package com.ittest.blog.config;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.ittest.blog.config.ApplicationUserPersmission.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, COURSE_WRITE));

    private final Set<ApplicationUserPersmission> permissions;

    ApplicationUserRole(Set<ApplicationUserPersmission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPersmission> getPermissions() {
        return permissions;
    }
}
