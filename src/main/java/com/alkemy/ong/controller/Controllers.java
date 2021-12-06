package com.alkemy.ong.controller;

public interface Controllers {
    String ADMIN = "hasRole(T(com.alkemy.ong.security.RoleEnum).ADMIN)";
    String USER = "hasRole(T(com.alkemy.ong.security.RoleEnum).USER)";
}
