package com.tristan.orders;

import com.tristan.orders.po.Account;
import com.tristan.orders.po.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Component
public class OrderManager {

    @Autowired
    private RestTemplate restTemplate;

//    public Account getAccountFromService(int accountId){
//        String url = "http://localhost:8081/account/select?id="+accountId;
//        try {
//            String result = HttpClientUtils.get(url);
//            return new Account(result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    @Bean
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }


    public Orders readomOrders(int accountId){
        Random random = new Random();
        String str = "21jedsakjhffhldaHLAIDSOIADAMDAAUODkdsjfasdaho2j1";
        Orders orders = new Orders();
//
//        StringBuffer randomUsername = new StringBuffer();
//        StringBuffer randomPassword = new StringBuffer();
//        for(int i=0;i < 10; i++){
//            randomUsername.append(str.charAt(random.nextInt(str.length())));
//            randomPassword.append(str.charAt(random.nextInt(str.length())));
//        }

        orders.setId(random.nextInt(9999));
        if(accountId == 0)
            orders.setAccountId(random.nextBoolean()?"6186":"6358");
        else
            orders.setAccountId(String.valueOf(accountId));
        orders.setInfo(String.valueOf(random.nextInt(100)));
        return orders;
    }


//    @Async
    public void increasePoints(String accountId, String info) throws InterruptedException {

//        RestTemplate restTemplate = new RestTemplate();
//        String uri = restTemplate.getForObject("http://account/updatePoints", String.class);
        String uri = "http://ACCOUNT-PROVIDER/account/updatePoints?accountId={accountId}&points={points}";
//       String uri = "http://172.20.10.4:8081/account/updatePoints?accountId={accountId}&points={points}";
//        172.20.10.4
        Map map = new HashMap();
        map.put("accountId", accountId);
        map.put("points", info);
        Account account = restTemplate.getForObject(uri, Account.class, map);
        System.out.println(account.toString());
        return;
    }

}
