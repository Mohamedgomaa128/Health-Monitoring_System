package BackEnd.Controller;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("api")
@RestController

public class Controller {

    @GetMapping("/getAnalytics")
    public int login(@RequestParam(value = "data1")String startData,
                     @RequestParam(value = "data2")String endData){
        // return the analysis function (startData,endData)
        return 0;
    }

}