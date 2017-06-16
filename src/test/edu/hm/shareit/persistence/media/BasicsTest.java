package edu.hm.shareit.persistence.media;


import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * These tests are intended to demonstrate basic ORM principles.
 * They are **not** correct unit-tests for the classes provided herein.
 */
public class BasicsTest {

    private static final Injector injector = Guice.createInjector(new GuiceTestModule());
    @Inject
    private SessionFactory sessionFactory;
    private Session entityManager;
    private Transaction tx;

    // dirty trick to store lecture's id for demonstration purposes
    private static long lectureId;

    public BasicsTest() {
        injector.injectMembers(this);
    }

    /**
     * Database is initialized with some data up-front.
     */
    /**
     * Database is initialized with some data up-front.
     */
    @BeforeClass
    public static void initialize() {
        Session entityManager = injector.getInstance(SessionFactory.class).getCurrentSession();
        Transaction tx = entityManager.beginTransaction();



        Book book = new Book();
        book.setTitle("Neville");
        book.setAuthor("Me");
        book.setIsbn("978-1509304981");

        Disc disc = new Disc();
        disc.setTitle("Boooom");
        disc.setDirector("You");
        disc.setFsk(12);
        disc.setBarcode("1234567890");

        entityManager.persist(disc);

        entityManager.persist(book);

/*        Phone phone = new Phone("08912345");
        entityManager.persist(phone);

        Teacher dozent = new Teacher("Lockhart", "Gilderoy", new BigDecimal(10000));
        dozent.setPhone(phone);
        entityManager.persist(dozent);

        Teacher dozent2 = new Teacher("Snape", "Severus",  new BigDecimal(15000));
//        dozent2.setPhone(phone); // causes a JPA Exception
        entityManager.persist(dozent2);

        Lecture lecture = new Lecture("Defence Against the Dark Arts");
        lecture.addTeacher(dozent);
        lecture.addTeacher(dozent2);
        entityManager.persist(lecture);
        lectureId = lecture.id;

        Lecture lecture2 = new Lecture("Potions");
        lecture2.addTeacher(dozent2);
        entityManager.persist(lecture2);
*/
        tx.commit();
    }

    /**
     * Shut down database after all tests have run.
     */
    @AfterClass
    public static void shutDown() {
        injector.getInstance(SessionFactory.class).close();
    }

    /**
     * Initializes a entityManager before each test.
     */
    @Before
    public void setUp() {
        entityManager = sessionFactory.getCurrentSession();
        tx = entityManager.beginTransaction();
    }

    /**
     * Close entityManager after a test.
     */
    @After
    public void tearDown() {
        // some tests might close entityManager for demo purposes
        if (entityManager.isOpen()) {
            tx.commit();
        }
    }

    /**
     * Demonstrates finding of a Person entity given the FirstName.
     */
    @Test
    public void testFind() {
        String firstName = "Neville";
        //property does not need to be private but is case sensitive!
        String queryString = "from Book p where p.title='" + firstName  + "'";
        List<Book> list = entityManager.createQuery(queryString).list();

        assertEquals(1, list.size());
    }

    /**
     * Demonstrates another feature of how to find entities.
     */
    @Test
    public void testFindLike() {
        String queryString = "from Book where title like '%evil%'";
        org.hibernate.query.Query query = entityManager.createQuery(queryString);
        List<Book> list = query.list();
        assertEquals(1, list.size());
    }

    @Test
    public void testLoadBook() {
        //"978-1509304981"
        Book book = (Book) entityManager.get(Book.class, "978-1509304981");
        assertNotNull(book);

    }

    @Test
    public void testLoadDisc() {
        Disc disc = (Disc) entityManager.get(Disc.class, "1234567890");

        assertNotNull(disc);
    }

    /**
     * Same as <code>testLoadLecture</code> but with closing entityManager
     * after the find operation has completed -- and before **referenced** fields are accessed.
     */
    /*@Test(expected=org.hibernate.LazyInitializationException.class)
    public void testLazyLoading() {



        //kein lazy loading

        Lecture lecture = (Lecture) entityManager.get(Lecture.class, lectureId);
        assertNotNull(lecture);
        entityManager.close();
        List<Teacher> teachers = lecture.getTeachers();
        assertEquals(2, teachers.size());
    }*/

    @Test
    public void testAllPersonsWithCriteria() {
        /*CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = criteriaBuilder.createQuery(Person.class);
        CriteriaQuery<Person> root = query.select(query.from(Person.class));

        Query<Person> q = entityManager.createQuery(query);
        List<Person> persons = q.getResultList();
        assertEquals(3, persons.size());
        */
    }

    @Test
    public void testPersonForNameCriteria() {
      /*
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Person> query = builder.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);

        query.where(builder.equal(root.get("firstName"), "Neville"));

        Query<Person> q = entityManager.createQuery(query);
        List<Person> persons = q.getResultList();
        assertEquals(1, persons.size());
         */
    }

}
