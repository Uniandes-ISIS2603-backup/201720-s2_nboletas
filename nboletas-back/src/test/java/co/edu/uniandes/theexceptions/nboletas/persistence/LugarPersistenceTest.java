/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.persistence;

import co.edu.uniandes.theexceptions.nboletas.entities.LugarEntity;
import java.util.ArrayList;
import java.util.List;
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
public class LugarPersistenceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LugarEntity.class.getPackage())
                .addPackage(LugarPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private LugarPersistence persistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<LugarEntity> data = new ArrayList<LugarEntity>();

    public LugarPersistenceTest() {
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
        em.createQuery("delete from LugarEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            LugarEntity entity = factory.manufacturePojo(LugarEntity.class);
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
        LugarEntity newEntity = factory.manufacturePojo(LugarEntity.class);
        LugarEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        LugarEntity entity = em.find(LugarEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Test of update method, of class LugarPersistence.
     */
    @Test
    public void testUpdate() {
        LugarEntity entity = data.get(0);
        LugarEntity e2 = data.get(1);
        entity.setDireccion(e2.getDireccion());
        persistence.update(entity);
        LugarEntity resp = em.find(LugarEntity.class, entity.getId());
        Assert.assertEquals(e2.getDireccion(), resp.getDireccion());
    }

    /**
     * Test of remove method, of class LugarPersistence.
     */
    @Test
    public void testDelete() {
        LugarEntity entity = data.get(0);
        persistence.delete(entity);
        LugarEntity deleted = em.find(LugarEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Test of find method, of class LugarPersistence.
     */
    @Test
    public void testFind() {
        LugarEntity entity = data.get(0);
        LugarEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Test of findAll method, of class LugarPersistence.
     */
    @Test
    public void testFindAll() {
        List<LugarEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (LugarEntity ent : list) {
            boolean found = false;
            for (LugarEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
}
