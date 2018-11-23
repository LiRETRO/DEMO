package net.meloli.demo.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.meloli.demo.sys.base.BaseController;
import net.meloli.demo.sys.rabbitmq.config.RabbitMQConfig;
import net.meloli.demo.sys.rabbitmq.service.IProducerService;
import net.meloli.demo.sys.service.inf.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "博客接口", tags = "BlogController")
@RestController
@RequestMapping("/api/blog")
public class BlogController extends BaseController {

    @Autowired
    IBlogService iBlogService;

    @Autowired
    IProducerService iProducerService;

    @RequestMapping(value = "sendMsgTest", method = RequestMethod.GET)
    public void sendMsgTest() {
        // 循环发送消息，测试接收者是否可以接收到
        for(int i = 0, len = 10; i < len; ++i ) {
            iProducerService.send(RabbitMQConfig.QUEUE, "测试RabbitMQ消息，这是第" + (i + 1) + "条");
        }
    }

    @ApiOperation(value = "异常测试", notes = "异常测试中")
    @RequestMapping(value = "exceptionTest", method = RequestMethod.GET)
    public void exceptionTest() throws Exception {
        throw new Exception("异常测试");
    }

    @ApiOperation(value = "获取博客List", notes = "获取博客List")
    @RequestMapping(value = "getBlogList", method = RequestMethod.GET)
    public Object getBlogList() {
        return iBlogService.getBlogList();
    }
}