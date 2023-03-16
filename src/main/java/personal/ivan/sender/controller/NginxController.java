package personal.ivan.sender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import personal.ivan.sender.entity.MyTableEntity;
import personal.ivan.sender.feignClient.MyClient;

@RestController
@RequestMapping("/")
public class NginxController {


    @GetMapping()
    @ResponseBody
    public String get() {
        System.out.println("server on port 4028 is listening");
        return "server on port 4028 is listening";
    }
}
