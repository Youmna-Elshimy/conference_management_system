package CSCI334.ConferencePro.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import CSCI334.ConferencePro.entities.Author;
import CSCI334.ConferencePro.interfaces.UserPersistenceInterface;
import CSCI334.ConferencePro.persistence.repos.AuthorRepository;

@Component
public class AuthorPersistence implements UserPersistenceInterface<Author> {
    @Autowired
    private AuthorRepository repository;

    public AuthorPersistence(AuthorRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Optional<Author> create(Author author) {
        // if email already exists then return an empty optional
        Optional<Author> existingUser = repository.findByEmail(author.getEmail());
        if (existingUser.isPresent()) {
            return Optional.empty();
        }

        // save author information
        Author newAuthor = repository.save(
                new Author(
                        author.getEmail(),
                        author.getPassword(),
                        author.getFirstName(),
                        author.getLastName(),
                        author.getUserType(),
                        author.getTitle(),
                        author.getOrganisation()));

        return Optional.of(newAuthor);
    }

    // READ
    // All authors
    public List<Author> getAll() {
        // load all authors in DB to new list
        List<Author> allAuthors = new ArrayList<Author>();
        repository.findAll().forEach(allAuthors::add);

        return allAuthors;
    }

    // One author
    public Optional<Author> get(Author author) {
        // try to find author in DB with given email
        Optional<Author> authorData = repository.findByEmail(author.getEmail());

        // no author with email provided exists in the DB, return empty optional
        if (!authorData.isPresent()) {
            return Optional.empty();
        }

        // author with email and password provided is found, return author info
        if (authorData.get().getPassword().equals(author.getPassword())) {
            return authorData;
        }

        // if no return yet then assume authorisation information is incorrect
        // return instance of author with id = 0;
        return Optional.of(new Author("0", "", "", "", "", "", "", ""));
    }

    // UPDATE
    public Optional<Author> update(Author newAuthorData) {
        // try to find author in DB with given email
        Optional<Author> foundAuthor = repository.findByEmail(newAuthorData.getEmail());

        // if author is found then update author info and return newly saved data
        if (foundAuthor.isPresent()) {
            Author newAuthor = foundAuthor.get();
            if (newAuthorData.getEmail() != null)
                newAuthor.setEmail(newAuthorData.getEmail());
            if (newAuthorData.getPassword() != null)
                newAuthor.setPassword(newAuthorData.getPassword());
            if (newAuthorData.getFirstName() != null)
                newAuthor.setFirstName(newAuthorData.getFirstName());
            if (newAuthorData.getLastName() != null)
                newAuthor.setLastName(newAuthorData.getLastName());
            if (newAuthorData.getUserType() != null)
                newAuthor.setUserType(newAuthorData.getUserType());
            if (newAuthorData.getTitle() != null)
                newAuthor.setTitle(newAuthorData.getTitle());
            if (newAuthorData.getOrganisation() != null)
                newAuthor.setOrganisation(newAuthorData.getOrganisation());
            Author updatedAuthor = repository.save(newAuthor);
            return Optional.of(updatedAuthor);
        } else { // else return empty
            return Optional.empty();
        }
    }

    // DELETE
    public Optional<String> delete(Author author) {
        // try to find author in DB with given email
        Optional<Author> foundUser = repository.findByEmail(author.getEmail());

        // delete it if found, return message
        if (foundUser.isPresent()) {
            repository.deleteById(foundUser.get().getId());
            return Optional.of("Author with email:" + foundUser.get().getEmail() + " deleted successfully");
        } else { // else return empty
            return Optional.empty();
        }
    }
}
