package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher("Piter", "Saint Petersburg");
        publisherRepository.save(publisher);

        Author andrewSmith = new Author("Andrew", "Smith");
        Book myLegacy = new Book("My legacy", "123456789");
        andrewSmith.getBooks().add(myLegacy);
        myLegacy.getAuthors().add(andrewSmith);
        myLegacy.setPublisher(publisher);
        publisher.getBooks().add(myLegacy);
        authorRepository.save(andrewSmith);
        bookRepository.save(myLegacy);

        Author sayanAndreev = new Author("Sayan", "Andreev");
        Book myFirstBook = new Book("My first book", "321654987");
        sayanAndreev.getBooks().add(myFirstBook);
        myFirstBook.getAuthors().add(sayanAndreev);
        myFirstBook.setPublisher(publisher);
        publisher.getBooks().add(myFirstBook);
        authorRepository.save(sayanAndreev);
        bookRepository.save(myFirstBook);

        publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Publisher number of books: " + publisher.getBooks().size());
    }

}
