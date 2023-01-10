package personal.ivan.sender.feignClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import personal.ivan.sender.entity.MyTableEntity;

@FeignClient(name="my-sender",url="http://localhost:8675")
public interface MyClient {
    @RequestMapping(method = RequestMethod.GET, value = "/myservice/get/47")
    MyTableEntity send();
}
