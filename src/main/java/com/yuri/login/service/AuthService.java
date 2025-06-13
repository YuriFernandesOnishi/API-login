package com.yuri.login.service;

import com.yuri.login.dto.AuthResponseDto;
import com.yuri.login.dto.LoginRequestDto;
import com.yuri.login.dto.RegisterRequestDto;
import com.yuri.login.entity.Role;
import com.yuri.login.entity.User;
import com.yuri.login.repository.RoleRepository;
import com.yuri.login.repository.UserRepository;
import com.yuri.login.security.JwtTokenUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;     // Repositório para usuários
    private final RoleRepository roleRepository;     // Repositório para roles
    private final PasswordEncoder passwordEncoder;   // Codificador de senha (BCrypt)
    private final JwtTokenUtility jwtUtil;           // Utilitário para gerar e validar JWT

    /**
     * Lógica para registrar novos usuários
     */

    public void register(RegisterRequestDto request) {
        log.debug("Iniciando registro do usuário com email: {}", request.getEmail());

        // Verifica se o email já está cadastrado
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email já registrado!");
        }

        // Verifica se a senha está preenchida
        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new IllegalArgumentException("A senha não pode estar vazia!");
        }

        log.debug("Verificação de senha concluída para o email: {}", request.getEmail());

        // Procura pelo role padrão USER
        Role defaultRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Role padrão (USER) não encontrada no banco!"));

        // Criptografando a senha e criando o usuário
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));  // Configura a senha com criptografia
        user.setActive(true);
        user.setRole(defaultRole);

        log.debug("Usuário criado: {}", user);

        // Salva o usuário no banco
        userRepository.save(user);

        log.info("Usuário registrado com sucesso: {}", request.getEmail());
    }

    /**
     * Lógica para autenticar usuários e gerar um token JWT
     */
    public AuthResponseDto login(LoginRequestDto request) {
        // Busca o usuário pelo email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));

        // Verifica se o usuário está ativo
        if (!user.isActive()) {
            throw new IllegalArgumentException("Usuário inativo, contato o administrador!");
        }

        // Verifica se a senha está correta
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Credenciais inválidas!");
        }

        // Gera um token JWT
        String token = jwtUtil.generateToken(user.getEmail());

        // Retorna o token JWT
        return new AuthResponseDto(token);
    }
}