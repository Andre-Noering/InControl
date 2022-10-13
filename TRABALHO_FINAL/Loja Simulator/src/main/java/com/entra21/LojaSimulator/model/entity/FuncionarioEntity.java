package com.entra21.LojaSimulator.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "funcionario")
@PrimaryKeyJoinColumn(name = "id_pessoa", referencedColumnName = "id")
public class FuncionarioEntity extends PessoaEntity implements UserDetails {

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "senha", nullable = false)
    private String senha;

    @OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY)
    private List<VendaEntity> vendas;

    @OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY)
    private Set<PedidoCompraEntity> pedidos;

    @OneToMany(mappedBy = "gerente", fetch = FetchType.LAZY)
    private List<LojaEntity> lojas;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
