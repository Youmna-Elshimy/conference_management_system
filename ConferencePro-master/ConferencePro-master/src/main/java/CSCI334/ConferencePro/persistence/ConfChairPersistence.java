package CSCI334.ConferencePro.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import CSCI334.ConferencePro.entities.ConferenceChair;
import CSCI334.ConferencePro.persistence.repos.ConfChairRepository;
import CSCI334.ConferencePro.interfaces.UserPersistenceInterface;

@Component
public class ConfChairPersistence implements UserPersistenceInterface<ConferenceChair> {
    @Autowired
    private ConfChairRepository confChairRepository;

    public ConfChairPersistence(ConfChairRepository confChairRepository) {
        this.confChairRepository = confChairRepository;
    }

    // CREATE
    public Optional<ConferenceChair> create(ConferenceChair user) {
        // if email already exists then return an empty optional
        Optional<ConferenceChair> existingUser = confChairRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return Optional.empty();
        }

        // save user information
        ConferenceChair newConferenceChair = confChairRepository.save(
                new ConferenceChair(
                        user.getEmail(),
                        user.getPassword(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getUserType()));

        return Optional.of(newConferenceChair);
    }

    // READ
    // All users
    public List<ConferenceChair> getAll() {
        // load all users in DB to new list
        List<ConferenceChair> allConferenceChairs = new ArrayList<ConferenceChair>();
        confChairRepository.findAll().forEach(allConferenceChairs::add);

        return allConferenceChairs;
    }

    // One user
    public Optional<ConferenceChair> get(ConferenceChair user) {
        // try to find user in DB with given email
        Optional<ConferenceChair> userData = confChairRepository.findByEmail(user.getEmail());

        // no user with email provided exists in the DB, return empty optional
        if (!userData.isPresent()) {
            return Optional.empty();
        }

        // user with email and password provided is found, return user info
        if (userData.get().getPassword().equals(user.getPassword())) {
            return userData;
        }

        // if no return yet then assume authorisation information is incorrect
        // return instance of user with id = 0;
        return Optional.of(new ConferenceChair("0", "", "", "", "", ""));
    }

    // UPDATE
    public Optional<ConferenceChair> update(ConferenceChair newConferenceChairData) {
        // try to find user in DB with given email
        Optional<ConferenceChair> foundConferenceChair = confChairRepository
                .findByEmail(newConferenceChairData.getEmail());

        // if user is found then update user info and return newly saved data
        if (foundConferenceChair.isPresent()) {
            ConferenceChair newConferenceChair = foundConferenceChair.get();
            if (newConferenceChairData.getEmail() != null)
                newConferenceChair.setEmail(newConferenceChairData.getEmail());
            if (newConferenceChairData.getPassword() != null)
                newConferenceChair.setPassword(newConferenceChairData.getPassword());
            if (newConferenceChairData.getFirstName() != null)
                newConferenceChair.setFirstName(newConferenceChairData.getFirstName());
            if (newConferenceChairData.getLastName() != null)
                newConferenceChair.setLastName(newConferenceChairData.getLastName());
            if (newConferenceChairData.getUserType() != null)
                newConferenceChair.setUserType(newConferenceChairData.getUserType());
            ConferenceChair updatedConferenceChair = confChairRepository.save(newConferenceChair);
            return Optional.of(updatedConferenceChair);
        } else { // else return empty
            return Optional.empty();
        }
    }

    // DELETE
    public Optional<String> delete(ConferenceChair user) {
        // try to find user in DB with given email
        Optional<ConferenceChair> foundConferenceChair = confChairRepository.findByEmail(user.getEmail());

        // delete it if found, return message
        if (foundConferenceChair.isPresent()) {
            confChairRepository.deleteById(foundConferenceChair.get().getId());
            return Optional.of(
                    "ConferenceChair with email:" + foundConferenceChair.get().getEmail() + " deleted successfully");
        } else { // else return empty
            return Optional.empty();
        }
    }
}
