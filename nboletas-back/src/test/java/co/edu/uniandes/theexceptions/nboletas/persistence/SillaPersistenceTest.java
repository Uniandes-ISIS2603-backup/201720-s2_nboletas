/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.persistence;

import co.edu.uniandes.theexceptions.nboletas.entities.SillaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.*;
import javax.persistence.*;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.*;
import org.junit.runner.*;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author fc.alvarez10
 */
@RunWith(Arquillian.class)
public class SillaPersistenceTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SillaEntity.class.getPackage())
                .addPackage(SillaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase XYZPersistence cuyos métodos se
     * van a probar.
     */
    @Inject
    private SillaPersistence persistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    /**
     *
     */
    private List<SillaEntity> data = new ArrayList<SillaEntity>();

    public SillaPersistenceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private void clearData() {
        em.createQuery("delete from SillaEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            SillaEntity entity = factory.manufacturePojo(SillaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class BoletaPersistence.
     */
    @Test
    public void testCreate() {

        PodamFactory factory = new PodamFactoryImpl();
        SillaEntity newEntity = factory.manufacturePojo(SillaEntity.class);
        SillaEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        SillaEntity entity = em.find(SillaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCosto(), entity.getCosto(), 0.00);
    }

    /**
     * Test of uptade method, of class BoletaPersistence.
     */
    @Test
    public void testUptade() {
        SillaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        SillaEntity newEntity = factory.manufacturePojo(SillaEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        SillaEntity resp = em.find(SillaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    /**
     * Test of remove method, of class BoletaPersistence.
     */
    @Test
    public void testDelete() {
        SillaEntity entity = data.get(0);
        persistence.delete(entity);
        SillaEntity deleted = em.find(SillaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Test of find method, of class BoletaPersistence.
     */
    @Test
    public void testFind() {
        SillaEntity entity = data.get(0);
        SillaEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Test of findAll method, of class BoletaPersistence.
     */
    @Test
    public void testFindAll() {
        List<SillaEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (SillaEntity ent : list) {
            boolean found = false;
            for (SillaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

}
