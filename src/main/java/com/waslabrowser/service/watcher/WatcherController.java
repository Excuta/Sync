package com.waslabrowser.service.watcher;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/watch")
public class WatcherController {
    private WatchService watchService;

    public WatcherController(WatchService watchService) {
        this.watchService = watchService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Follower>> watch() {
        List<Follower> followers = watchService.watch();
        return ResponseEntity.ok(followers);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Follower>> get() {
        Iterable<Follower> followers = watchService.getAll();
        return ResponseEntity.ok(followers);
    }
}
