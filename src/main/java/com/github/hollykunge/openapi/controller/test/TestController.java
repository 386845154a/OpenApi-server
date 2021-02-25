package com.github.hollykunge.openapi.controller.test;

import com.github.hollykunge.openapi.vo.base.ResVo;
import com.github.hollykunge.openapi.vo.res.base.ObjectRestResponse;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zhuqz
 * @date: 2020/6/24 13:34
 * @description: 测试api各种类型接口(提供开发测试使用，建议不要删除)
 */
@RestController
@RequestMapping("/test")

public class TestController {
    /**
     * get测试1
     * url:http://127.0.0.1:8030/test/getTest1
     * @return
     */
    @GetMapping("getTest1")
    public ObjectRestResponse getTest1(){
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        String res = "ok";
        String msg = "200";
        objectRestResponse.msg(msg);
        objectRestResponse.data(res);
        objectRestResponse.rel(true);
        return objectRestResponse;
    }

    /**
     * get测试2
     * url:http://127.0.0.1:8030/test/getTest2?param1={param1}&param2={param2}
     * @param param1
     * @param param2
     * @return
     */
    @GetMapping("getTest2")
    public ObjectRestResponse getTest2(@RequestParam("param1") String param1,@RequestParam("param2") String param2){
        String msg = "200";
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.msg(msg);
        objectRestResponse.data(param1+param2);
        objectRestResponse.rel(true);
        return objectRestResponse;
    }

    /**
     * get测试3
     * url:http://127.0.0.1:8030/test/getTest3/{param1}
     * @param param1
     * @return
     */
    @GetMapping("/getTest3/{param1}")
    public ObjectRestResponse getTest2(@PathVariable("param1") String param1){
        String msg = "200";
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.msg(msg);
        objectRestResponse.data(param1);
        objectRestResponse.rel(true);
        return objectRestResponse;
    }
    /**
     * post测试1
     * url:http://127.0.0.1:8030/test/postTest1
     * @param resVo
     * @return
     */
    @PostMapping("/postTest1")
    public ObjectRestResponse postTest1(@RequestBody ResVo resVo){
        String msg = "200";
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.msg(msg);
        objectRestResponse.data(resVo);
        objectRestResponse.rel(true);
        return objectRestResponse;
    }
    /**
     * post测试1
     * url:http://127.0.0.1:8030/test/postTest2
     * @param jsonStr
     * @return
     */
    @PostMapping("/postTest2")
    public ObjectRestResponse postTest2(@RequestBody String jsonStr){
        String msg = "200";
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.msg(msg);
        objectRestResponse.data(jsonStr);
        objectRestResponse.rel(true);
        return objectRestResponse;
    }
    /**
     * delete测试1
     * url:http://127.0.0.1:8030/test/deleteTest1?param1={param1}
     * @param param1
     * @return
     */
    @DeleteMapping("deleteTest1")
    public ObjectRestResponse deleteTest1(@RequestParam("param1") String param1){
        String msg = "200";
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.msg(msg);
        objectRestResponse.data(param1);
        objectRestResponse.rel(true);
        return objectRestResponse;
    }
    /**
     * put测试1
     * url:http://127.0.0.1:8030/test/putTest1
     * @param resVo
     * @return
     */
    @PutMapping("/putTest1")
    public ObjectRestResponse putTest1(@RequestBody ResVo resVo){
        String msg = "200";
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.msg(msg);
        objectRestResponse.data(resVo);
        objectRestResponse.rel(true);
        return objectRestResponse;
    }
    /**
     * put测试2
     * url:http://127.0.0.1:8030/test/putTest2
     * @param jsonStr
     * @return
     */
    @PutMapping("/putTest2")
    public ObjectRestResponse putTest2(@RequestBody String jsonStr){
        String msg = "200";
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.msg(msg);
        objectRestResponse.data(jsonStr);
        objectRestResponse.rel(true);
        return objectRestResponse;
    }
    /**
     * 一个比较全的测试 requestparam 和 requestbody都有
     * url:http://127.0.0.1:8030/test/deleteTest2?param1={param1}&param2={param2}
     * @param param1
     * @param param2
     * @param resVo
     * @return
     */
    @DeleteMapping("deleteTest2")
    public ObjectRestResponse deleteTest2(@RequestParam("param1") String param1,@RequestParam("param2") String param2,@RequestBody ResVo resVo){
        String msg = "200";
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.msg(msg);
        objectRestResponse.data(param1+param2 + resVo.toString());
        objectRestResponse.rel(true);
        return objectRestResponse;
    }
}