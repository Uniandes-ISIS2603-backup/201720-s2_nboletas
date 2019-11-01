/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.persistence;

import co.edu.uniandes.theexceptions.nboletas.entities.ArtistaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.EspectaculoEntity;
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
 * @author jf.ramos
 */
@RunWith(Arquillian.class)
public class ArtistaPersistenceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ArtistaEntity.class.getPackage())
                .addPackage(ArtistaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase XYZPersistence cuyos métodos se
     * van a probar.
     */
    @Inject
    private ArtistaPersistence persistence;

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
    private List<ArtistaEntity> data = new ArrayList<ArtistaEntity>();

    public ArtistaPersistenceTest() {
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
        em.createQuery("delete from ArtistaEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ArtistaEntity entity = factory.manufacturePojo(ArtistaEntity.class);
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
        ArtistaEntity newEntity = factory.manufacturePojo(ArtistaEntity.class);
        ArtistaEntity result = persistence.create(newEntity);
        Assert.assertNotNull(result);
        ArtistaEntity entity = em.find(ArtistaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Test of uptade method, of class BoletaPersistence.
     */
    @Test
    public void testUpdate() {
        ArtistaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ArtistaEntity newEntity = factory.manufacturePojo(ArtistaEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        ArtistaEntity resp = em.find(ArtistaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    /**
     * Test of remove method, of class BoletaPersistence.
     */
    @Test
    public void testDelete() {
        ArtistaEntity entity = data.get(0);
        persistence.delete(entity);
        ArtistaEntity deleted = em.find(ArtistaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Test of find method, of class BoletaPersistence.
     */
    @Test
    public void testFind() {
        ArtistaEntity entity = data.get(0);
        ArtistaEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Test of findAll method, of class BoletaPersistence.
     */
    @Test
    public void testFindAll() {
        List<ArtistaEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ArtistaEntity ent : list) {
            boolean found = false;
            for (ArtistaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

}
