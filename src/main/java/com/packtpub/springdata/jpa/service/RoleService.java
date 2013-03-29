package com.packtpub.springdata.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.packtpub.springdata.jpa.model.Role;
import com.packtpub.springdata.jpa.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	public List<Role> getRoles() {
		return Lists.newArrayList(roleRepository.findAll());
	}
}
