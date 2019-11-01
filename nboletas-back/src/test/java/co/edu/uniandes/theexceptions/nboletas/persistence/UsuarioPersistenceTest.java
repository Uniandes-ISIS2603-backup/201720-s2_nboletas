package co.edu.uniandes.theexceptions.nboletas.persistence;

import co.edu.uniandes.theexceptions.nboletas.entities.BoletaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.ReembolsoEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.UsuarioEntity;
import java.util.LinkedList;
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
 * @author jm.contreras10
 */
@RunWith(Arquillian.class)
public class UsuarioPersistenceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase UsuarioPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private UsuarioPersistence persistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    /**
     * Estructura de datos que almacena los datos de los usuarios.
     */
    private List<UsuarioEntity> data = new LinkedList<UsuarioEntity>();

    public UsuarioPersistenceTest() {
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
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
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
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getUserName(), entity.getUserName());
        Assert.assertEquals(newEntity.getPassword(), entity.getPassword());
        Assert.assertEquals(newEntity.getNombreUsuario(), entity.getNombreUsuario());
        Assert.assertEquals(newEntity.getEmail(), entity.getEmail());
        Assert.assertEquals(newEntity.getPais(), entity.getPais());
        Assert.assertEquals(newEntity.getCiudad(), entity.getCiudad());

        for (BoletaEntity boleta : entity.getBoletas()) {
            boolean found = false;
            List<BoletaEntity> bol = newEntity.getBoletas();
            for (int i = 0; i < bol.size() && !found; i++) {
                if (bol.get(i).getId() == boleta.getId()) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

        for (ReembolsoEntity reem : entity.getReembolsos()) {
            boolean found = false;
            List<ReembolsoEntity> r = newEntity.getReembolsos();
            for (int i = 0; i < r.size() && !found; i++) {
                if (r.get(i).getId() == reem.getId()) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

    }

    /**
     * Test of update method, of class UsuarioPersistence.
     */
    @Test
    public void testUpdate() {
        UsuarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setId(entity.getId());
        persistence.update(newEntity);
        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getId(), resp.getId());

        Assert.assertEquals(newEntity.getUserName(), resp.getUserName());
        Assert.assertEquals(newEntity.getPassword(), resp.getPassword());
        Assert.assertEquals(newEntity.getNombreUsuario(), resp.getNombreUsuario());
        Assert.assertEquals(newEntity.getEmail(), resp.getEmail());
        Assert.assertEquals(newEntity.getPais(), resp.getPais());
        Assert.assertEquals(newEntity.getCiudad(), resp.getCiudad());

        for (BoletaEntity boleta : resp.getBoletas()) {
            boolean found = false;
            List<BoletaEntity> bol = newEntity.getBoletas();
            for (int i = 0; i < bol.size() && !found; i++) {
                if (bol.get(i).getId() == boleta.getId()) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

        for (ReembolsoEntity reem : resp.getReembolsos()) {
            boolean found = false;
            List<ReembolsoEntity> r = newEntity.getReembolsos();
            for (int i = 0; i < r.size() && !found; i++) {
                if (r.get(i).getId() == reem.getId()) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of remove method, of class UsuarioPersistence.
     */
    @Test
    public void testDelete() {
        UsuarioEntity entity = data.get(0);
        persistence.delete(entity);
        UsuarioEntity deleted = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Test of find method, of class UsuarioPersistence.
     */
    @Test
    public void testFind() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getUserName(), entity.getUserName());
        Assert.assertEquals(newEntity.getPassword(), entity.getPassword());
        Assert.assertEquals(newEntity.getNombreUsuario(), entity.getNombreUsuario());
        Assert.assertEquals(newEntity.getEmail(), entity.getEmail());
        Assert.assertEquals(newEntity.getPais(), entity.getPais());
        Assert.assertEquals(newEntity.getCiudad(), entity.getCiudad());

        for (BoletaEntity boleta : entity.getBoletas()) {
            boolean found = false;
            List<BoletaEntity> bol = newEntity.getBoletas();
            for (int i = 0; i < bol.size() && !found; i++) {
                if (bol.get(i).getId() == boleta.getId()) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

        for (ReembolsoEntity reem : entity.getReembolsos()) {
            boolean found = false;
            List<ReembolsoEntity> r = newEntity.getReembolsos();
            for (int i = 0; i < r.size() && !found; i++) {
                if (r.get(i).getId() == reem.getId()) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of findAll method, of class UsuarioPersistence.
     */
    @Test
    public void testFindAll() {
        List<UsuarioEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (UsuarioEntity ent : list) {
            boolean found = false;
            for (UsuarioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
}
