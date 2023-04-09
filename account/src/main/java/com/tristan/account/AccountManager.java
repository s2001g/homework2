package com.tristan.account;


import com.tristan.po.Account;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class AccountManager {
    public static Account readomAccount(){
        Random random = new Random();
        String str = "21jedsakjhffhldaHLAIDSOIADAMDAAUODkdsjfasdaho2j1";
        Account accountDO = new Account();

        StringBuffer randomUsername = new StringBuffer();
        StringBuffer randomPassword = new StringBuffer();
        for(int i=0;i < 10; i++){
            randomUsername.append(str.charAt(random.nextInt(str.length())));
            randomPassword.append(str.charAt(random.nextInt(str.length())));
        }
        accountDO.setId(random.nextInt(9999));
        accountDO.setUsername(randomUsername.toString());
        accountDO.setPassword(randomPassword.toString());
        return accountDO;
    }

//    public static void rabbitMQProvider(Account account){
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        try (Connection connection = factory.newConnection();
//             Channel channel = connection.createChannel()) {
//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//            String message = account.toString();
//            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
//            System.out.println(" [x] Sent '" + message + "'");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }
}
