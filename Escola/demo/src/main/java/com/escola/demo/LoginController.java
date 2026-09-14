package com.escola.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password) {

        try {

            InputStream arquivo = getClass()
                    .getResourceAsStream("/data/usuarios.json");

            List<Map<String, String>> usuarios = objectMapper.readValue(
                    arquivo,
                    new TypeReference<List<Map<String, String>>>() {}
            );

            for (Map<String, String> usuario : usuarios) {

                String usuarioJson = usuario.get("username");
                String senhaJson = usuario.get("password");

                System.out.println("Username encontrado no JSON: " + usuarioJson);
                System.out.println("Password encontrada no JSON: " + senhaJson);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "login";
    }
}