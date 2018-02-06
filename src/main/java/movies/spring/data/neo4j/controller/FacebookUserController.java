package movies.spring.data.neo4j.controller;

import movies.spring.data.neo4j.services.FacebookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by marvin on 2018/2/6.
 */
@RestController("/facebook")
public class FacebookUserController {
    final FacebookService facebookService;

    public FacebookUserController(FacebookService facebookService){
        this.facebookService = facebookService;
    }

    @RequestMapping("/fbgraph")
    public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
        return facebookService.graph(limit == null ? 100 : limit);
    }
}
