package com.ittest.blog.config;

public enum ApplicationUserPersmission {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String permission;

    ApplicationUserPersmission(String permission) {
        this.permission = permission;
    }

    public String getPersmission() {
        return permission;
    }
}
