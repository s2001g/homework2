package com.tristan.account;

import com.tristan.po.Account;
import io.seata.core.context.RootContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class AccountController {
//    @NacosInjected
//    private NamingService namingService;
//
//    @RequestMapping(value = "/get", method = GET)
//    @ResponseBody
//    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
//        return namingService.getAllInstances(serviceName);
//    }

    @RequestMapping("/create")
    public Account create() throws IOException {
        AccountService service = new AccountService();

        return service.createAccount();
    }

    @RequestMapping("/updatePoints")
    public Account updatePoints(Integer accountId, Integer points) throws IOException {
        System.out.println("updatePointsSeata全局事务id=================>{}"+ RootContext.getXID());
        AccountService service = new AccountService();
        return service.updatePoints(accountId ,points);
    }

    @RequestMapping("/select")
    public Account select(Integer id) throws IOException {
        AccountService service = new AccountService();

        return service.selectAccount(id);
    }
}
