package CSCI334.ConferencePro.factories;

import org.springframework.stereotype.Component;

import CSCI334.ConferencePro.entities.Author;
import CSCI334.ConferencePro.entities.CompleteUserData;
import CSCI334.ConferencePro.entities.ConferenceChair;
import CSCI334.ConferencePro.entities.Reviewer;
import CSCI334.ConferencePro.entities.User;

@Component
public class UsersFactory {
    // return the appropriate user object based on the given user data
    public User createAppropriateUser(CompleteUserData userData) {

        if (userData.getUserType().toLowerCase().equals("reviewer")) {
            return new Reviewer(userData.getEmail(), userData.getPassword(), userData.getFirstName(),
                    userData.getLastName(), userData.getUserType(), userData.getPaperLimit(), userData.getAssignedPapers(),
                    userData.getSubjectInterests());
        } else if (userData.getUserType().toLowerCase().equals("author")) {
            return new Author(userData.getEmail(), userData.getPassword(), userData.getFirstName(),
                    userData.getLastName(), userData.getUserType(), userData.getTitle(), userData.getOrganisation());
        } else {
            return new ConferenceChair(userData.getEmail(), userData.getPassword(), userData.getFirstName(),
                    userData.getLastName(), userData.getUserType());
        }

    }
}