package cv.user.api.controller;

import cv.user.api.entity.MenuTemplate;
import cv.user.api.entity.MenuTemplateKey;
import cv.user.api.service.MenuTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/template")
public class TemplateController {
    @Autowired
    private MenuTemplateService menuTemplateService;

    @PostMapping(path = "/saveMenu")
    public Mono<?> saveMenuTemplate(@RequestBody MenuTemplate menu) {
        return Mono.justOrEmpty(menuTemplateService.save(menu));
    }
    @PostMapping(path = "/deleteMenu")
    public Mono<?> deleteMenu(@RequestBody MenuTemplateKey key) {
        return Mono.justOrEmpty(menuTemplateService.delete(key));
    }

    @GetMapping(path = "/getMenuTree")
    public Mono<?> getMenuTree(@RequestParam Integer busId) {
        return Mono.justOrEmpty(menuTemplateService.getMenuTree(busId));
    }
    @GetMapping(path = "/getMenu")
    public Mono<?> getMenu(@RequestParam Integer busId) {
        return Mono.justOrEmpty(menuTemplateService.getMenu(busId));
    }
}
