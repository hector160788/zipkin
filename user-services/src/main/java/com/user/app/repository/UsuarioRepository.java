package com.user.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.app.entity.UsuarioEntity;
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{

}
