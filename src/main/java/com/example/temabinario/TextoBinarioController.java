package com.example.temabinario;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TextoBinarioController {

    @PostMapping("/convertir")
    public String convertirTextoABinario(@RequestBody String texto) {
        StringBuilder binario = new StringBuilder();
        for (char c : texto.toCharArray()) {
            binario.append(String.format("%8s", Integer.toBinaryString(c))
                             .replace(' ', '0')).append(" ");
        }
        return binario.toString().trim();
    }
}
