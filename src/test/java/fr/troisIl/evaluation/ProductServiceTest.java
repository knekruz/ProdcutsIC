package fr.troisIl.evaluation;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProductServiceTest {

    private Database db = null;
    private ProductService productService;

    private int countBefore = 0;

    @Before
    public void setUp() throws SQLException {
        String testDatabaseFileName = "product.db";

        // reset la BDD avant le test
        File file = new File(testDatabaseFileName);
        file.delete();

        db = new Database(testDatabaseFileName);
        db.createBasicSqlTable();

        productService = new ProductService(db);

        countBefore = count();
    }

    /**
     * Compte les produits en BDD
     *
     * @return le nombre de produit en BDD
     */
    private int count() throws SQLException {
        ResultSet resultSet = db.executeSelect("Select count(*) from Product");
        assertNotNull(resultSet);
        return resultSet.getInt(1);
    }

    @Test
    public void testInsert() throws SQLException {
        Product product = new Product("ami",40);
        productService.insert(product);
        ResultSet resultSet = db.executeSelect("Select label, quantity from Product where label = 'ami' and quantity = '40'");

        assertEquals(product.getLabel(), resultSet.getString(1));
        assertEquals((int) product.getQuantity(), resultSet.getInt(2));
        assertEquals(countBefore+1, count());
    }

    @Test
    public void testUpdate() throws SQLException {
        Product productInsert = new Product("ami",40);
        productService.insert(productInsert);
        Product product = new Product(1,"salami",50);
        productService.update(product);
        ResultSet resultSet = db.executeSelect("Select label, quantity, id from Product where id = '1'");

        assertEquals(product.getLabel(), resultSet.getString(1));
        assertEquals((int) product.getQuantity(), resultSet.getInt(2));
        assertEquals((int) product.getId(), resultSet.getInt(3));
    }

    @Test
    public void testFindById() throws SQLException {
        Product productInsert = new Product("ami",40);
        productService.insert(productInsert);
        ResultSet resultSet = db.executeSelect("Select label, quantity, id from Product where id = '1'");

        String label = productService.findById(1).getLabel();
        int quantity = productService.findById(1).getQuantity();
        int id = productService.findById(1).getId();

        assertEquals(label, resultSet.getString(1));
        assertEquals((int) quantity, resultSet.getInt(2));
        assertEquals((int) id, resultSet.getInt(3));

    }

    @Test
    public void testDelete() throws SQLException {
        Product productInsert = new Product("ami",40);
        productService.insert(productInsert);
        int countAfter = count();

        db.executeUpdate("DELETE FROM Product where id = 1");
        assertEquals(countAfter-1, count());

    }


}
