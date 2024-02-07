package TestAddOrderProducts;

import TestOrdersProducts.AbstractTest;
import org.example.home.OrdersProductsEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.SQLException;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrdersProductsTest extends AbstractTest {

    /**
     * Тест проверяет корректность добавления записи в таблицу orders_products.
     * Создается новая запись с уникальными order_id, product_id.
     * После выполнения проверяется, что добавление прошло успешно (вставлена одна строка).
     */
    @Order(1)
    @Test
    void addOrderProduct_whenValid_shouldSave() {
        // given
        Session session = getSession();
        session.beginTransaction();

        int maxOrderId = (int) session.createSQLQuery("SELECT MAX(order_id) FROM orders").uniqueResult();
        int maxProductId = (int) session.createSQLQuery("SELECT MAX(product_id) FROM products").uniqueResult();
        int nextOrderId = maxOrderId + 1;
        int nextProductId = maxProductId + 1;

        final Query query = session.createSQLQuery("INSERT INTO orders_products(order_id, product_id, quantity) VALUES (:orderId, :productId, :quantity)")
                .setParameter("orderId", nextOrderId)
                .setParameter("productId", nextProductId)
                .setParameter("quantity", 1);

        // when
        int rowsAffected = query.executeUpdate();

        // then
        session.getTransaction().commit();

        Assertions.assertEquals(1, rowsAffected);
    }

    /**
     * Тест проверяет, что количество записей в таблице orders_products соответствует ожидаемому.
     */
    @Test
    @Order(2)
    void getOrdersProducts_whenValid_shouldReturn() throws SQLException {
        // given
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM orders_products")
                .addEntity(OrdersProductsEntity.class);

        // when
        int countTableSize = query.list().size();

        // then
        Assertions.assertEquals(24, countTableSize);
    }

    /**
     * Параметризованный тест, который проверяет, что при запросе по order_id
     * возвращаются корректные записи из таблицы orders_products.
     */
    @Order(3)
    @ParameterizedTest
    @CsvSource({"1", "2", "4"})
    void getProductsByOrderId_whenValid_shouldReturn(int orderId) throws SQLException {
        // given
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM orders_products WHERE order_id = :orderId")
                .addEntity(OrdersProductsEntity.class)
                .setParameter("orderId", orderId);

        // when
        List<OrdersProductsEntity> ordersProductsList = query.list();

        // then
        Assertions.assertFalse(ordersProductsList.isEmpty());
    }

    /**
     * Тест проверяет корректность удаления записи из таблицы orders_products.
     * Удаляется запись с указанными order_id и product_id.
     * После выполнения проверяется, что удаление прошло успешно (удалена одна строка).
     */
    @Test
    @Order(4)
    void deleteOrderProduct_whenValid_shouldDelete() {
        // given
        Session session = getSession();
        session.beginTransaction();

        int orderIdToDelete = 16;
        int productIdToDelete = 11;

        final Query query = session.createSQLQuery("DELETE FROM orders_products WHERE order_id = :orderId AND product_id = :productId")
                .setParameter("orderId", orderIdToDelete)
                .setParameter("productId", productIdToDelete);

        // when
        int rowsAffected = query.executeUpdate();

        // then
        session.getTransaction().commit();

        Assertions.assertEquals(1, rowsAffected);
    }
}
