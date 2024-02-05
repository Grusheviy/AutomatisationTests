package TestAddProduct;

import org.example.home.ProductsEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;

import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTest extends AbstractTest{
    @Test
    @Order(1)
    void getProducts_whenValid_shouldReturn() throws SQLException {
        // given
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM products")
                .addEntity(ProductsEntity.class);

        // when
        int countTableSize = query.list().size();

        // then
        Assertions.assertEquals(10, countTableSize);
    }

    @Test
    @Order(2)
    void getProductById_whenValid_shouldReturn() throws SQLException {
        // given
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM products WHERE product_id=1")
                .addEntity(ProductsEntity.class);

        // when
        ProductsEntity productEntity = (ProductsEntity) query.uniqueResult();

        // then
        Assertions.assertNotNull(productEntity);
    }

    @Test
    @Order(3)
    void getProductByProductName_whenValid_shouldReturn() throws SQLException {
        // given
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM products WHERE menu_name= 'VIVA LAS VEGAS ROLL'")
                .addEntity(ProductsEntity.class);

        // when
        ProductsEntity productEntity = (ProductsEntity) query.uniqueResult();

        // then
        Assertions.assertNotNull(productEntity);
    }

    @Test
    @Order(4)
    void addProduct_whenValid_shouldSave() {
        // given
        ProductsEntity entity = new ProductsEntity();
        entity.setMenuName("New Product");
        entity.setPrice(String.valueOf(99.99f)); // Задаем значение как строку

        // when
        Session session = getSession();
        session.beginTransaction();

        int maxProductId = (int) session.createSQLQuery("SELECT MAX(product_id) FROM products").uniqueResult();
        entity.setProductId((short) (maxProductId + 1));

        session.persist(entity);
        session.getTransaction().commit();

        // then
        Assertions.assertNotNull(entity.getProductId());
    }

    @Test
    @Order(5)
    void deleteProduct_whenValid_shouldDelete() {
        // given
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM products WHERE product_id=11")
                .addEntity(ProductsEntity.class);
        Optional<ProductsEntity> productEntity = (Optional<ProductsEntity>) query.uniqueResultOptional();
        Assumptions.assumeTrue(productEntity.isPresent());

        // when
        Session session = getSession();
        session.beginTransaction();
        session.delete(productEntity.get());
        session.getTransaction().commit();

        // then
        final Query queryAfterDelete = getSession()
                .createSQLQuery("SELECT * FROM products WHERE product_id=11")
                .addEntity(ProductsEntity.class);
        Optional<ProductsEntity> productEntityAfterDelete = (Optional<ProductsEntity>) queryAfterDelete.uniqueResultOptional();
        Assertions.assertFalse(productEntityAfterDelete.isPresent());
    }

    @Test
    @Order(6)
    void addProduct_whenNotValid_shouldThrow() {
        // given
        ProductsEntity entity = new ProductsEntity();

        // when
        Session session = getSession();
        session.beginTransaction();
        session.persist(entity);

        // then
        Assertions.assertThrows(PersistenceException.class, () -> session.getTransaction().commit());
    }
}
