package com.am.restauarnts.data.network;


public final class ServerEndPoints {
    public static final String BASE = "https://amarneham.000webhostapp.com/restaurants/restaurants/";
    public static final String RESTAURANTS = BASE + "restaurants/read.php";
    public static final String MENU = BASE + "menu/readByRestaurant.php";
    public static final String ORDER_CREATE = BASE + "orders/create.php";
    public static final String ORDER_GET = BASE + "orders/readById.php";
    public static final String ORDER_GET_ALL = BASE + "orders/readByUserBrief.php";
    public static final String TOP_FOOD_GET_ALL = BASE + "stocks/top.php";

}
