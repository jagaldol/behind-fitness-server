package com.jagaldol.behind.fitness._core.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(val userId: Long) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return arrayListOf(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun getPassword() = ""
    override fun getUsername() = ""
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}
