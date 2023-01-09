package personal.ivan.sender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import personal.ivan.sender.entity.MyTableEntity;
import personal.ivan.sender.feignClient.MyClient;

@RestController
@RequestMapping("/send")
public class MyController {

    @Autowired
    MyClient myClient;

    @GetMapping()
    public MyTableEntity getTodos() {
        return myClient.send();
    }

}
