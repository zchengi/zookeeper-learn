package com.cheng.web.controller;

import com.cheng.common.utils.JsonResult;
import com.cheng.web.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
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
    public JsonResult doGetLogin(String itemId) {

        if (StringUtils.isNoneBlank(itemId)) {
            customerService.displayBuy(itemId);
        } else {
            return JsonResult.errorMsg("商品id不能为空");
        }

        return JsonResult.ok();
    }
}
