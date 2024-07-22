package utils;

import java.util.ArrayList;
import java.util.List;

import entity.Orders;

public class OrdersList {
    public OrdersList(){};
    public static List<Orders> listOrders = new ArrayList<>();
    public static List<Orders> getListOrders(){
        return listOrders;
    }
}
