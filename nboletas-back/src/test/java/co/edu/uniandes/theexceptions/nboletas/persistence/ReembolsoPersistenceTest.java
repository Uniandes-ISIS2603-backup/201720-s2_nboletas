package co.edu.uniandes.theexceptions.nboletas.persistence;

import co.edu.uniandes.theexceptions.nboletas.entities.ReembolsoEntity;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jm.contreras10
 */
@RunWith(Arquillian.class)
public class ReembolsoPersistenceTest {
    
    private static final Logger LOGGER = Logger.getLogger(ReembolsoPersistenceTest.class.getName());

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReembolsoEntity.class.getPackage())
                .addPackage(ReembolsoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase ReembolsoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ReembolsoPersistence persistence;

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
     * Estructura de datos que contiene la informacion a probar.
     */
    private List<ReembolsoEntity> data = new ArrayList<ReembolsoEntity>();

    public ReembolsoPersistenceTest() {
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
        em.createQuery("delete from ReembolsoEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ReembolsoEntity entity = factory.manufacturePojo(ReembolsoEntity.class);
            LOGGER.info(entity.getValor() + " valor");
            LOGGER.info(entity.getId() + " id");
            em.persist(entity);
            data.add(entity);
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        PodamFactory factory = new PodamFactoryImpl();
        ReembolsoEntity newEntity = factory.manufacturePojo(ReembolsoEntity.class);
        ReembolsoEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        ReembolsoEntity entity = em.find(ReembolsoEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getValor(), entity.getValor(), 0.0);
        /*
        Assert.assertEquals(newEntity.getUsuario().getId(), entity.getUsuario().getId());
        Assert.assertEquals(newEntity.getBoleta().getId(), entity.getBoleta().getId());
         */

    }

    /**
     * Test of update method, of class ReembolsoPersistence.
     */
    @Test
    public void testUpdate() {
        ReembolsoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ReembolsoEntity newEntity = factory.manufacturePojo(ReembolsoEntity.class);
        newEntity.setId(entity.getId());
        persistence.update(newEntity);
        ReembolsoEntity resp = em.find(ReembolsoEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getId(), resp.getId());
        Assert.assertEquals(newEntity.getValor(), resp.getValor(), 0.0);
        /*
        Assert.assertEquals(newEntity.getUsuario().getId(), entity.getUsuario().getId());
        Assert.assertEquals(newEntity.getBoleta().getId(), entity.getBoleta().getId());
         */
    }

    /**
     * Test of remove method, of class ReembolsoPersistence.
     */
    @Test
    public void testDelete() {
        ReembolsoEntity entity = data.get(0);
        persistence.delete(entity);
        ReembolsoEntity deleted = em.find(ReembolsoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Test of find method, of class ReembolsoPersistence.
     */
    @Test
    public void testFind() {
        ReembolsoEntity entity = data.get(0);
        ReembolsoEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(newEntity.getValor(), entity.getValor(), 0.0);
        /*
        Assert.assertEquals(newEntity.getUsuario().getId(), entity.getUsuario().getId());
        Assert.assertEquals(newEntity.getBoleta().getId(), entity.getBoleta().getId());
         */
    }

    /**
     * Test of findAll method, of class UsuarioPersistence.
     */
    @Test
    public void testFindAll() {
        List<ReembolsoEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ReembolsoEntity ent : list) {
            boolean found = false;
            for (ReembolsoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
}
