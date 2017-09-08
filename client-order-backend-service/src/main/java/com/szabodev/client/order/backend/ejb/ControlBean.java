package com.szabodev.client.order.backend.ejb;

import com.szabodev.client.order.backend.entities.*;

import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "ControlBean")
@LocalBean
@WebService
@RolesAllowed("WEBSERVICE_USER")
public class ControlBean implements ControlBeanLocal {

    public ControlBean() {
    }

    @PersistenceContext(name = "clientorders-ejb")
    private EntityManager em;

    // ======================================================================================
    // EMPLOYEE
    // ======================================================================================

    @Override
    public void createEmployee(String username, String password, int mode, String salt) {
        if (username != null && password != null) {
            Employee employee = new Employee();
            employee.setUsername(username);
            employee.setPassword(password);
            employee.setMode(mode);
            employee.setSalt(salt);
            em.persist(employee);
        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        Query q = em.createQuery("SELECT e FROM Employee e");
        return q.getResultList();
    }

    @Override
    public Employee getEmployee(Integer id) {
        return em.find(Employee.class, id);
    }

    @Override
    public void removeEmployee(Employee employee) {
        em.remove(em.merge(employee));
    }

    @Override
    public List<Integer> getEmployeeIds() {
        Query q = em.createQuery("SELECT e.employeeid FROM Employee e");
        return q.getResultList();
    }

    @Override
    public Employee getEmployeeByName(String username) {
        Query query = em.createNamedQuery("Employee.findByUsername");
        query.setParameter("username", username);
        List<Employee> result = (List<Employee>) query.getResultList();
        if (result.isEmpty() == true) {
            return null;
        } else {
            return result.get(0);
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        em.merge(employee);
    }

    // ======================================================================================
    // PRODUCTS
    // ======================================================================================

    @Override
    public void createProduct(String productname, double price, String description) {
        if (productname != null) {
            Product product = new Product();
            product.setProductname(productname);
            product.setPrice(price);
            product.setDescription(description);
            em.persist(product);
        }
    }

    @Override
    public List<Product> getAllProduct() {
        Query q = em.createQuery("SELECT p FROM Product p");
        return q.getResultList();
    }

    @Override
    public Product getProduct(Integer id) {
        return em.find(Product.class, id);
    }

    @Override
    public void removeProduct(Product product) {
        em.remove(em.merge(product));
    }

    @Override
    public List<Integer> getProductIds() {
        Query q = em.createQuery("SELECT p.productid FROM Product p");
        return q.getResultList();
    }

    @Override
    public void updateProduct(Product product) {
        em.merge(product);
    }

    // ======================================================================================
    // RECEIPT
    // ======================================================================================

    @Override
    public boolean createReceiptForOrder(Integer orderid, byte[] receipt, String filename) {
        Orders order = em.find(Orders.class, orderid);
        if (order != null && receipt != null) {
            Receipt newreceipt = new Receipt();
            newreceipt.setFilename(filename);
            newreceipt.setReceiptfile(receipt);
            em.persist(newreceipt);
            order.addReceipt(newreceipt);
            return true;
        }
        return false;
    }

    @Override
    public void removeReceipt(Receipt receipt) {
        em.remove(em.merge(receipt));
    }

    @Override
    public List<Receipt> listReceipt() {
        Query q = em.createQuery("SELECT r FROM Receipt r");
        return q.getResultList();
    }

    @Override
    public byte[] getReceipt(int id) {
        return em.find(Receipt.class, id).getReceiptfile();
    }

    @Override
    public Receipt getReceipt2(int id) {
        return em.find(Receipt.class, id);
    }

    @Override
    public List<Integer> getReceiptIds() {
        Query q = em.createQuery("SELECT r.receiptid FROM Receipt r");
        return q.getResultList();
    }

    // ======================================================================================
    // CLIENTS
    // ======================================================================================

    @Override
    public void createClient(String name, String address) {
        if (name != null && address != null) {
            Client client = new Client();
            client.setName(name);
            client.setAddress(address);
            em.persist(client);
        }
    }

    @Override
    public void removeClient(Client client) {
        em.remove(em.merge(client));
    }

    @Override
    public List<Client> getAllClient() {
        Query q = em.createQuery("SELECT c FROM Client c");
        return q.getResultList();
    }

    @Override
    public Client getClient(Integer id) {
        return em.find(Client.class, id);
    }

    @Override
    public List<Integer> getClientIds() {
        Query q = em.createQuery("SELECT c.clientid FROM Client c");
        return q.getResultList();
    }

    @Override
    public void updateClient(Client client) {
        em.merge(client);
    }

    // ======================================================================================
    // Request
    // ======================================================================================

    @Override
    public void createRequestForClient(Integer clientid, Integer productid, String comment) {
        Client client = em.find(Client.class, clientid);
        Product product = em.find(Product.class, productid);
        if (client != null && productid != null) {
            Request newRequest = new Request();
            newRequest.setComment(comment);
            em.persist(newRequest);
            client.addRequest(newRequest);
            product.addRequest(newRequest);
        }
    }

    @Override
    public Request getRequest(Integer id) {
        return em.find(Request.class, id);
    }

    @Override
    public void removeRequest(Request request) {
        em.remove(em.merge(request));
    }

    @Override
    public List<Integer> getRequestIds() {
        Query q = em.createQuery("SELECT r.requestid FROM Request r");
        return q.getResultList();
    }

    @Override
    public List<Request> getAllRequest() {
        Query q = em.createQuery("SELECT r FROM Request r");
        return q.getResultList();
    }

    @Override
    public void updateRequest(Request request) {
        em.merge(request);
    }

    // ======================================================================================
    // ORDERS
    // ======================================================================================

    @Override
    public void createOrderForClient(Integer clientid, Integer productid, String comment) {
        Client client = em.find(Client.class, clientid);
        Product product = em.find(Product.class, productid);
        if (client != null && productid != null && comment != null) {
            Orders neworder = new Orders();
            neworder.setComment(comment);
            em.persist(neworder);
            client.addOrder(neworder);
            product.addOrder(neworder);
        }
    }

    @Override
    public Orders getOrder(Integer id) {
        return em.find(Orders.class, id);
    }

    @Override
    public void removeOrder(Orders order) {
        em.remove(em.merge(order));
    }

    @Override
    public List<Integer> getOrdersIds() {
        Query q = em.createQuery("SELECT o.ordersid FROM Orders o");
        return q.getResultList();
    }

    @Override
    public List<Orders> getAllOrders() {
        Query q = em.createQuery("SELECT o FROM Orders o");
        return q.getResultList();
    }

    @Override
    public void updateOrder(Orders order) {
        em.merge(order);
    }

}
