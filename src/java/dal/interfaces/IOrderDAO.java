/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dal.interfaces;

import models.Order;

public interface IOrderDAO {

    public Order checkDishOrderByUser(int userId, int product_id);

    public int createNewOrder(int sum, String fullname, String email,
            String phone, String address, int user_id, String note, String cod, int table_id);

    public Order getCurrentOrder(int userId, String afalse);

}
