package TestDelivery;

import org.example.home.CustomersEntity;
import org.example.home.DeliveryEntity;
import org.example.home.OrdersEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.SQLException;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeliveryTest extends AbstractTest {

    /**
     * Тест проверяет корректность добавления записи в таблицу delivery.
     * Создается новая запись с уникальным delivery_id, присвоенным максимальному delivery_id + 1.
     * После выполнения проверяется, что добавление прошло успешно (вставлена одна строка).
     */
    @Test
    @Order(1)
    void addDelivery_whenValid_shouldSave() {
        // given
        Session session = getSession();
        session.beginTransaction();

        int maxDeliveryId = (int) session.createSQLQuery("SELECT MAX(delivery_id) FROM delivery").uniqueResult();
        int nextDeliveryId = maxDeliveryId + 1;

        int maxOrderId = (int) session.createSQLQuery("SELECT MAX(order_id) FROM orders").uniqueResult();

        final Query query = session.createSQLQuery("INSERT INTO delivery(delivery_id, order_id, courier_id, date_arrived, taken, payment_method) " +
                        "VALUES (:deliveryId, :orderId, :courierId, :dateArrived, :taken, :paymentMethod)")
                .setParameter("deliveryId", nextDeliveryId)
                .setParameter("orderId", maxOrderId + 1)
                .setParameter("courierId", 1)
                .setParameter("dateArrived", "2023-02-28 12:00:00")
                .setParameter("taken", "No")
                .setParameter("paymentMethod", "Cash");

        // when
        int rowsAffected = query.executeUpdate();

        // then
        session.getTransaction().commit();

        Assertions.assertEquals(1, rowsAffected);
    }

    /**
     * Тест проверяет, что количество записей в таблице delivery соответствует ожидаемому.
     */
    @Test
    @Order(2)
    void getDeliveryById_whenExists_shouldReturnDelivery() {
        // given
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM delivery")
                .addEntity(DeliveryEntity.class);

        // when
        int countTableSize = query.list().size();

        // then
        Assertions.assertEquals(16, countTableSize);
    }

    /**
     * Параметризованный тест, который проверяет, что при запросе по delivery_id
     * возвращается корректная запись из таблицы delivery.
     */
    @ParameterizedTest
    @CsvSource({"1", "10", "16"})
    @Order(3)

    void getDeliveryByCustomerId_whenValid_shouldReturn(int deliveryId) throws SQLException {
        // given
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM delivery WHERE delivery_id='" + deliveryId + "'")
                .addEntity(DeliveryEntity.class);

        // when
        DeliveryEntity deliveryEntity = (DeliveryEntity) query.uniqueResult();

        // then
        Assertions.assertNotNull(deliveryEntity);
    }

    /**
     * Тест проверяет корректность удаления записи из таблицы delivery.
     * Удаляется запись с указанным delivery_id.
     * После выполнения проверяется, что удаление прошло успешно (удалена одна строка).
     */
    @Test
    @Order(4)
    void deleteDelivery_whenValid_shouldDelete() {
        //given
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM delivery WHERE delivery_id=" + 16).addEntity(DeliveryEntity.class);
        Optional<DeliveryEntity> deliveryEntity = (Optional<DeliveryEntity>) query.uniqueResultOptional();
        Assumptions.assumeTrue(deliveryEntity.isPresent());
        //when
        Session session = getSession();
        session.beginTransaction();
        session.delete(deliveryEntity.get());
        session.getTransaction().commit();
        //then
        final Query queryAfterDelete = getSession()
                .createSQLQuery("SELECT * FROM delivery WHERE delivery_id=" + 16).addEntity(DeliveryEntity.class);
        Optional<DeliveryEntity> deliveryEntityAfterDelete = (Optional<DeliveryEntity>) queryAfterDelete.uniqueResultOptional();
        Assertions.assertFalse(deliveryEntityAfterDelete.isPresent());
    }
}
