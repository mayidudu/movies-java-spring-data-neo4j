package com.jn.rb.data.process.controller;

import com.alibaba.fastjson.JSONObject;
import com.jn.rb.data.process.domain.FacebookUser;
import com.jn.rb.data.process.repositories.FacebookUserRepository;
import com.jn.rb.data.process.services.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by marvin on 2018/2/6.
 */
@RestController
@RequestMapping("/api/v1/facebook")
public class FacebookUserController {
    final FacebookService facebookService;

    @Autowired
    private FacebookUserRepository facebookUserRepository;

    public FacebookUserController(FacebookService facebookService){
        this.facebookService = facebookService;
    }

    @RequestMapping("/fbgraph")
    public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
        return facebookService.graph(limit == null ? 100 : limit);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject create(@RequestBody JSONObject data) throws Exception{
        System.out.println("create ========");
        FacebookUser facebookUser = new FacebookUser();
        facebookUser.setPathname("pathname");
        facebookUser.setScreenname(data.getString("screenname"));
        facebookUser.setUid(1000l);
        facebookUserRepository.save(facebookUser);
        JSONObject res = new JSONObject();
        res.put("error",1);
        return res;
    }
}
