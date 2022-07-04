package com.waslabrowser.service.watcher;

import com.waslabrowser.service.common.response.PageResponseBody;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/watch")
public class WatcherController {
    private WatchService watchService;

    public WatcherController(WatchService watchService) {
        this.watchService = watchService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponseBody<Follower>> watch() {
        PageResponseBody<Follower> followers = watchService.watch();
        return ResponseEntity.ok(followers);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponseBody<Follower>> get() {
        PageResponseBody<Follower> all = watchService.getAll();
        return ResponseEntity.ok(all);
    }
}
