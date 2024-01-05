package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.Repositories.AuthorRepository;
import guru.springframework.spring6webapp.Repositories.BookRepository;
import guru.springframework.spring6webapp.Repositories.PublisherRepository;
import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Publisher awp = new Publisher();
        awp.setPublisherName("Addison-Wesley Professional");
        awp.setAddress("36 Evergreen Ave");
        awp.setCity("Philadelphia");
        awp.setState("Pennsylvania");
        awp.setZipCode("PA 19118");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);
        Publisher awpSaved = publisherRepository.save(awp);

        ddd.setPublisher(awpSaved);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB= new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");

        Publisher jws = new Publisher();
        jws.setPublisherName("John Wiley & Sons");
        jws.setAddress("111 River Street");
        jws.setCity("Hoboken");
        jws.setState("New Jersey");
        jws.setZipCode("NJ 07030-5774");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);
        Publisher jwsSaved = publisherRepository.save(jws);

        noEJB.setPublisher(jwsSaved);

        // for manyToMany
        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);
        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(rodSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);

        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());

    }
}
