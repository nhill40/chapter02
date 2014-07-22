package com.apress.javaee6.chapter02;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.assertNotNull;

public class BookTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;

    @BeforeClass
    public static void initEntityManager() throws SQLException {
        // pulls from test persistence.xml
        emf = Persistence.createEntityManagerFactory("chapter02PU");
        em = emf.createEntityManager();
    }

    @AfterClass
    public static void closeEntityManager() throws SQLException {
        em.close();
        emf.close();
    }

    @Before
    public void initTransaction() {
        tx = em.getTransaction();
    }

    @Test
    public void createBook() throws Exception {
        Book book = new Book();
        book.setTitle("The Hitchhiker's Guide to the Galaxy");
        book.setPrice(12.5f);
        book.setDescription("Sci-fi comedy");
        book.setIsbn("1-84023-742-2");
        book.setNumPages(354);
        book.setIllustrations(false);

        tx.begin();
        em.persist(book);
        tx.commit();
        assertNotNull(book.getId());

        List<Book> books = em.createNamedQuery("findAllBooks").getResultList();
        Assert.assertNotNull(books);
    }
}
