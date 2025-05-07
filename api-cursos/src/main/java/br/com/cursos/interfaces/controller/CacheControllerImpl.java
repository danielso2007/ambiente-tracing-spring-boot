package br.com.cursos.interfaces.controller;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cache")
public class CacheControllerImpl implements CacheController {
    
    private final RedisConnectionFactory connectionFactory;

    public CacheControllerImpl(RedisConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCache() {
        RedisServerCommands commands = connectionFactory.getConnection().serverCommands();
        commands.flushDb();  
        return ResponseEntity.ok("Cache Redis limpo com sucesso.");
    }

}
