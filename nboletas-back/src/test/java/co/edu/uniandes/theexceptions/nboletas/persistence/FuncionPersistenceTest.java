/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.persistence;

import co.edu.uniandes.theexceptions.nboletas.entities.FuncionEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * @author ja.gomez1
 */
@RunWith(Arquillian.class)
public class FuncionPersistenceTest {
    
    private static final Logger LOGGER = Logger.getLogger(FuncionPersistenceTest.class.getName());

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FuncionEntity.class.getPackage())
                .addPackage(FuncionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private FuncionPersistence persistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<FuncionEntity> data = new ArrayList<FuncionEntity>();

    public FuncionPersistenceTest() {
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
        em.createQuery("delete from FuncionEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            FuncionEntity entity = factory.manufacturePojo(FuncionEntity.class);
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
        FuncionEntity newEntity = factory.manufacturePojo(FuncionEntity.class);
        newEntity.setHora("8:00 pm");
        FuncionEntity result = persistence.create(newEntity);
        Assert.assertNotNull(result);
        FuncionEntity entity = em.find(FuncionEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Test of update method, of class FuncionPersistence.
     */
    @Test
    public void testUpdate() {
        FuncionEntity entity = data.get(0);
        try {
            entity.setFecha((new SimpleDateFormat("MM/dd/yyyy")).parse("08/31/1998"));
        } catch (Exception e) { }
        entity.setHora("9:00 pm");
        persistence.update(entity);
        FuncionEntity resp = em.find(FuncionEntity.class, entity.getId());
        Assert.assertEquals("08/31/1998", (new SimpleDateFormat("MM/dd/yyyy")).format(resp.getFecha()));
        Assert.assertEquals(entity.getId(), resp.getId());
    }

    /**
     * Test of remove method, of class FuncionPersistence.
     */
    @Test
    public void testDelete() {
        FuncionEntity entity = data.get(0);
        persistence.delete(entity);
        FuncionEntity deleted = em.find(FuncionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Test of find method, of class FuncionPersistence.
     */
    @Test
    public void testFind() {
        FuncionEntity entity = data.get(0);
        FuncionEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Test of findAll method, of class FuncionPersistence.
     */
    @Test
    public void testFindAll() {
        List<FuncionEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (FuncionEntity ent : list) {
            boolean found = false;
            for (FuncionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
}
