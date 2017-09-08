package com.szabodev.client.order.backend.ejb;

import java.util.List;

import javax.ejb.Local;

import com.szabodev.client.order.backend.entities.Employee;
import com.szabodev.client.order.backend.entities.Orders;
import com.szabodev.client.order.backend.entities.Receipt;
import com.szabodev.client.order.backend.entities.Request;
import com.szabodev.client.order.backend.entities.Client;
import com.szabodev.client.order.backend.entities.Product;

@Local
public interface ControlBeanLocal {

    void createClient(String name, String address);

    void createOrderForClient(Integer clientid, Integer receiptid, String ordername);

    List<Orders> getAllOrders();

    List<Client> getAllClient();

    boolean createReceiptForOrder(Integer orderid, byte[] receipt, String filename);

    List<Receipt> listReceipt();

    byte[] getReceipt(int id);

    Receipt getReceipt2(int id);

    void removeClient(Client client);

    Client getClient(Integer id);

    List<Integer> getClientIds();

    void removeReceipt(Receipt receipt);

    Orders getOrder(Integer id);

    void removeOrder(Orders order);

    List<Integer> getReceiptIds();

    List<Integer> getOrdersIds();

    void createEmployee(String username, String password, int mode, String salt);

    List<Employee> getAllEmployee();

    Employee getEmployee(Integer id);

    void removeEmployee(Employee employee);

    List<Integer> getEmployeeIds();

    void createProduct(String productname, double price, String description);

    List<Integer> getProductIds();

    void removeProduct(Product product);

    Product getProduct(Integer id);

    List<Product> getAllProduct();

    Employee getEmployeeByName(String username);

    void updateEmployee(Employee employee);

    void updateOrder(Orders order);

    void updateProduct(Product product);

    void updateClient(Client client);

    void createRequestForClient(Integer clientid, Integer productid, String comment);

    Request getRequest(Integer id);

    void removeRequest(Request request);

    List<Integer> getRequestIds();

    List<Request> getAllRequest();

    void updateRequest(Request request);

}