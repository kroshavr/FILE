package hogwarts.school.controller;

import hogwarts.school.service.InfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {
    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/getPort")
    public ResponseEntity<String> getPort() {
        String Port = infoService.getPort();
        return ResponseEntity.ok(Port);
    }

    @GetMapping("/getIntegerValue")
    public ResponseEntity<Integer> getIntegerValue () {
        return ResponseEntity.ok(infoService.getIntegerValue());
    }
}
