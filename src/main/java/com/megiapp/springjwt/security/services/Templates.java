package com.megiapp.springjwt.security.services;

public class Templates {
    public final static String MAIL_TO_MANAGER_NEW_ORDER_SUBJECT = "New order Received - %s";
    public final static String MAIL_TO_CLIENT_NEW_ORDER_SUBJECT = "Thanks for your order %s";
    public final static String MAIL_TO_CLIENT_ORDER_DELIVERED_SUBJECT = "Order %s has been delivered";

    public final static String MAIL_TO_MANAGER_NEW_ORDER_BODY =
            "There is a new order in the queue.\n" +
                    "Order details\n" +
                    "\tOrder Nr.: {ORDER_NR}\n" +
                    "\tOrder Timestamp: {ORDER_TIME}\n" +
                    "\tClient Name: {CLIENT_NAME}\n" +
                    "\tTaco(s): {TACO_NAMES}\n" +
                    "\tTotal Price: {TOTAL_PRICE}\n" +
                    "\tOrder status: {ORDER_STATUS}\n" +
                    "----------------------------------\n" +
                    "Please Update the Status as soon as possible\n" +
                    "\n" +
                    "Best regards\n" +
                    "Taco App System\n";

    public final static String MAIL_TO_CLIENT_NEW_ORDER_BODY =
            "Dear customer,\n" + "\n" + "We have received your order and we are working on delivering it on time.\n\n" +
                    "Order details\n" +
                    "\tOrder Nr.: {ORDER_NR}\n" +
                    "\tOrder Timestamp: {ORDER_TIME}\n" +
                    "\tClient Name: {CLIENT_NAME}\n" +
                    "\tTaco(s): {TACO_NAMES}\n" +
                    "\tTotal Price: {TOTAL_PRICE}\n" +
                    "\tOrder status: {ORDER_STATUS}\n" +
                    "----------------------------------\n" +
                    "We wil inform you once the order is out for delivery.\n" +
                    "\n" +
                    "Best regards\n" +
                    "Taco Team\n";


    public final static String MAIL_TO_CLIENT_BODY_ORDER_STATUS =
            "Dear customer,\n" + "\n" +
                    "Your Order with Nr. {ORDER_NR} has now the Status : {ORDER_STATUS}. \n" +
                    "We hope you enjoy the Tacos.\n" +
                    "\n" +
                    "\n" +
                    "Thanks for choosing us :)" +
                    "\n----------------------------------\n" +
                    "\n" + "\n" +
                    "Best regards\n" +
                    "Taco Team\n";
}



