package com.cheng.web.controller;

import com.cheng.common.utils.JsonResult;
import com.cheng.web.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 订购商品 controller
 * @author cheng
 *         2018/10/3 13:50
 */
@Controller
public class PayController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/buy")
    @ResponseBody
    public JsonResult buy(String itemId) {
        boolean result = customerService.displayBuy(itemId);
        return JsonResult.ok(result ? "订单创建成功...":"订单创建失败...");
    }

    /**
     * 模拟集群环境下的数据不一致
     * @param itemId
     * @return
     */
    @GetMapping("/buy2")
    @ResponseBody
    public JsonResult doGetLogin(String itemId) {
        boolean result = customerService.displayBuy(itemId);
        return JsonResult.ok(result ? "订单创建成功...":"订单创建失败...");
    }
}
