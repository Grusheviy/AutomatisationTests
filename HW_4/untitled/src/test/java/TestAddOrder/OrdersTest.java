package TestAddOrder;

import org.example.home.OrdersEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.sql.SQLException;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrdersTest extends AbstractTest {

    @Test
    @Order(1)
    void getOrders_whenValid_shouldReturn() throws SQLException {
        // given
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM orders")
                .addEntity(OrdersEntity.class);

        // when
        int countTableSize = query.list().size();

        // then
        Assertions.assertEquals(15, countTableSize);
    }

    @Order(2)
    @ParameterizedTest
    @CsvSource({"1", "2", "3"})
    void getOrdersByCustomerId_whenValid_shouldReturn(int customerId) throws SQLException {
        // given
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM orders WHERE customer_id=1")
                .addEntity(OrdersEntity.class);

        // when
        OrdersEntity ordersEntity = (OrdersEntity) query.uniqueResult();

        // then
        Assertions.assertNotNull(ordersEntity);
    }

    @Order(3)
    @Test
    void getOrdersByDate_whenValid_shouldReturn() throws SQLException {
        // given
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM orders WHERE DATE(date_get) = CURRENT_DATE")
                .addEntity(OrdersEntity.class);

        // when
        int countTableSize = query.list().size();

        // then
        Assertions.assertEquals(15, countTableSize);
    }

    @Test
    @Order(4)
    void addOrder_whenValid_shouldSave() {
        // given
        Session session = getSession();
        session.beginTransaction();

        int maxOrderId = (int) session.createSQLQuery("SELECT MAX(order_id) FROM orders").uniqueResult();
        int nextOrderId = maxOrderId + 1;

        final Query query = session.createSQLQuery("INSERT INTO orders(order_id, customer_id, date_get) VALUES (:orderId, :customerId, datetime('now'))")
                .setParameter("orderId", nextOrderId)
                .setParameter("customerId", 16);

        // when
        int rowsAffected = query.executeUpdate();

        // then
        session.getTransaction().commit();

        Assertions.assertEquals(1, rowsAffected);
    }

    @Test
    @Order(5)
    void deleteOrder_whenValid_shouldDelete() {
        // given
        Session session = getSession();
        session.beginTransaction();

        int orderIdToDelete = 16;

        final Query query = session.createSQLQuery("DELETE FROM orders WHERE order_id = :orderId")
                .setParameter("orderId", orderIdToDelete);

        // when
        int rowsAffected = query.executeUpdate();

        // then
        session.getTransaction().commit();

        Assertions.assertEquals(1, rowsAffected);
    }
}
