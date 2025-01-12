/*
 * Responsável pela inserção dos métodos na tabela;
 */

package com.ProjetoIntegrador.feeling.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoIntegrador.feeling.model.Theme;
import com.ProjetoIntegrador.feeling.repository.ThemeRepository;

@RestController
@RequestMapping("/theme")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ThemeController {
	@Autowired
	private ThemeRepository repository;

	@GetMapping
	public ResponseEntity<List<Theme>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Theme> getById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/topic/{topic}") 
	public ResponseEntity<List<Theme>> getByTopic(@PathVariable String topic) {
		return ResponseEntity.ok(repository.findAllByTopicContainingIgnoreCase(topic));
	}

	@PostMapping
	public ResponseEntity<Theme> post(@RequestBody Theme theme) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(theme));
	}

	@PutMapping
	public ResponseEntity<Theme> put(@RequestBody Theme theme) {
		return ResponseEntity.ok(repository.save(theme));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}

}
