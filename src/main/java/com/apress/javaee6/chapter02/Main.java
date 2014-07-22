package com.apress.javaee6.chapter02;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        Book book = new Book();
        book.setTitle("The Hitchhiker's Guide to the Galaxy");
        book.setPrice(12.5f);
        book.setDescription("Sci-fi comedy");
        book.setIsbn("1-84023-742-2");
        book.setNumPages(354);
        book.setIllustrations(false);

        // pulls from production persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter02PU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(book);
        tx.commit();

        em.close();
        emf.close();
    }
}
