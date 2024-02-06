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

    @Test
    @Order(5)
    void deleteCourier_whenValid_shouldDelete() {
        // given
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM courier_info WHERE courier_id=5")
                .addEntity(CourierInfoEntity.class);
        Optional<CourierInfoEntity> courierInfoEntity = (Optional<CourierInfoEntity>) query.uniqueResultOptional();
        Assumptions.assumeTrue(courierInfoEntity.isPresent());

        // when
        Session session = getSession();
        session.beginTransaction();
        session.delete(courierInfoEntity.get());
        session.getTransaction().commit();

        // then
        final Query queryAfterDelete = getSession()
                .createSQLQuery("SELECT * FROM courier_info WHERE courier_id=5")
                .addEntity(CourierInfoEntity.class);
        Optional<CourierInfoEntity> courierInfoEntityAfterDelete = (Optional<CourierInfoEntity>) queryAfterDelete.uniqueResultOptional();
        Assertions.assertFalse(courierInfoEntityAfterDelete.isPresent());
    }

    @Test
    @Order(6)
    void verifyMaxCourierIdAfterDeletion() {
        // given
        Session session = getSession();
        session.beginTransaction();

        int maxCourierIdBeforeDelete = (int) session.createSQLQuery("SELECT MAX(courier_id) FROM courier_info").uniqueResult();

        // when
        session.getTransaction().commit();

        // then
        Assertions.assertEquals(maxCourierIdBeforeDelete - 1, maxCourierIdBeforeDelete);
    }
}
