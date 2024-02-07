package TestAddCourier;

import org.example.home.CourierInfoEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.SQLException;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourierTest extends AbstractTest{

    /**
     * Тест проверяет, что количество записей в таблице courier_info соответствует ожидаемому.
     */
    @Test
    @Order(1)
    void getCourierInfo_whenValid_shouldReturn() throws SQLException{
        // given
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM courier_info")
                .addEntity(CourierInfoEntity.class);

        // when
        int countTableSize = query.list().size();

        // then
        Assertions.assertEquals(4, countTableSize);
    }

    /**
     * Тест проверяет, что при запросе по courier_id возвращается корректная запись.
     */
    @Test
    @Order(2)
    void getCourierInfoById_whenValid_shouldReturn() throws SQLException {
        // given
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM courier_info WHERE courier_id=1")
                .addEntity(CourierInfoEntity.class);

        // when
        CourierInfoEntity courierInfoEntity = (CourierInfoEntity) query.uniqueResult();

        // then
        Assertions.assertNotNull(courierInfoEntity);
    }

    /**
     * Параметризованный тест, который проверяет, что при запросе по
     * courier_id, first_name и last_name
     * возвращается корректная запись.
     */
    @ParameterizedTest
    @CsvSource({
            "1, John, Rython",
            "2, Kate, Looran",
            "3, Bob, Kolaris",
            "4, Michael, Frontal"
    })
    @Order(3)
    void getCourierInfoByIdFirstLastName_whenValid_shouldReturn(Integer courierId, String firstName, String lastName) throws SQLException {
        // given
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM courier_info WHERE " +
                        "courier_id = :courierId " +
                        "AND first_name = :firstName " +
                        "AND last_name = :lastName")
                .setParameter("courierId", courierId)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .addEntity(CourierInfoEntity.class);

        // when
        CourierInfoEntity courierInfoEntity = (CourierInfoEntity) query.uniqueResult();

        // then
        Assertions.assertNotNull(courierInfoEntity);
        Assertions.assertEquals(firstName, courierInfoEntity.getFirstName());
        Assertions.assertEquals(lastName, courierInfoEntity.getLastName());
    }

    /**
     * Тест проверяет корректность добавления новой записи в таблицу courier_info.
     */
    @ParameterizedTest
    @CsvSource({
            "Chipi, Chipi, + 7 800 555 3535, foot",
            "Chapa, Chapa, + 7 555 555 3535, car"
    })
    @Order(4)
    void addCourierInfo_whenValid_shouldSave(String firstName, String lastName, String phoneNumber, String deliveryType) {
        // given
        Session session = getSession();
        session.beginTransaction();

        int maxCourierId = (int) session.createSQLQuery("SELECT MAX(courier_id) FROM courier_info").uniqueResult();

        CourierInfoEntity entity = new CourierInfoEntity();
        entity.setCourierId((short) (maxCourierId + 1));
        entity.setFirstName(firstName);
        entity.setLastName(lastName);
        entity.setPhoneNumber(phoneNumber);
        entity.setDeliveryType(deliveryType);

        // when
        session.persist(entity);
        session.getTransaction().commit();

        // then
        Assertions.assertNotNull(entity.getCourierId());
        Assertions.assertEquals(firstName, entity.getFirstName());
        Assertions.assertEquals(lastName, entity.getLastName());
        Assertions.assertEquals(phoneNumber, entity.getPhoneNumber());
        Assertions.assertEquals(deliveryType, entity.getDeliveryType());
    }

    /**
     * Тест проверяет корректность удаления записи из таблицы courier_info.
     */
    @Test
    @Order(5)
    void deleteLastCourier_shouldDelete() {
        // given
        Session session = getSession();
        session.beginTransaction();

        // Получаем текущий максимальный courier_id
        int maxCourierIdBeforeDelete = (int) session.createSQLQuery("SELECT MAX(courier_id) FROM courier_info").uniqueResult();

        // Создаем запрос на удаление курьера с максимальным courier_id
        final Query deleteQuery = session.createSQLQuery("DELETE FROM courier_info WHERE courier_id = :maxCourierId")
                .setParameter("maxCourierId", maxCourierIdBeforeDelete);

        // when
        int rowsAffected = deleteQuery.executeUpdate();
        session.getTransaction().commit();

        // then
        Assertions.assertEquals(1, rowsAffected);
    }

    /**
     * Тест проверяет, что после удаления курьера, максимальный courier_id уменьшается на 1.
     */
    @Test
    @Order(6)
    void verifyMaxCourierIdAfterDeletion() {
        // given
        Session session = getSession();
        session.beginTransaction();

        // Получаем текущий максимальный courier_id
        int maxCourierIdBeforeDelete = (int) session.createSQLQuery("SELECT MAX(courier_id) FROM courier_info").uniqueResult();

        // Удаляем курьера с courier_id=5
        final Query deleteQuery = session.createSQLQuery("DELETE FROM courier_info WHERE courier_id = 5");
        deleteQuery.executeUpdate();

        // Получаем новый максимальный courier_id после удаления
        int maxCourierIdAfterDelete = (int) session.createSQLQuery("SELECT MAX(courier_id) FROM courier_info").uniqueResult();

        // when
        session.getTransaction().commit();

        // then
        Assertions.assertEquals(maxCourierIdBeforeDelete - 1, maxCourierIdAfterDelete);
    }
}
