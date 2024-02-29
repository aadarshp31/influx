package com.app.server.role;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RoleService {
  private final RoleRepository roleRepository;

  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public List<Role> getAllRoles() {
    return (List<Role>) roleRepository.findAll();
  }

}
