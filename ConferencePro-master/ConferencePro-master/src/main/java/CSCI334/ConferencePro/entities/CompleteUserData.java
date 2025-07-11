package CSCI334.ConferencePro.entities;

import java.util.List;
import java.util.Objects;

public class CompleteUserData {
    // user
    private String id;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String userType;
    
    // reviewer
    private int paperLimit;
    private int assignedPapers;
    private List<String> subjectInterests;

    // author
    private String title;
    private String organisation;



    public CompleteUserData() {
    }

    public CompleteUserData(String id, String email, String password, String firstName, String lastName, String userType, int paperLimit, int assignedPapers, List<String> subjectInterests, String title, String organisation) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
        this.paperLimit = paperLimit;
        this.assignedPapers = assignedPapers;
        this.subjectInterests = subjectInterests;
        this.title = title;
        this.organisation = organisation;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getPaperLimit() {
        return this.paperLimit;
    }

    public void setPaperLimit(int paperLimit) {
        this.paperLimit = paperLimit;
    }

    public int getAssignedPapers() {
        return this.assignedPapers;
    }

    public void setAssignedPapers(int assignedPapers) {
        this.assignedPapers = assignedPapers;
    }

    public List<String> getSubjectInterests() {
        return this.subjectInterests;
    }

    public void setSubjectInterests(List<String> subjectInterests) {
        this.subjectInterests = subjectInterests;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrganisation() {
        return this.organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public CompleteUserData id(String id) {
        setId(id);
        return this;
    }

    public CompleteUserData email(String email) {
        setEmail(email);
        return this;
    }

    public CompleteUserData password(String password) {
        setPassword(password);
        return this;
    }

    public CompleteUserData firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    public CompleteUserData lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public CompleteUserData userType(String userType) {
        setUserType(userType);
        return this;
    }

    public CompleteUserData paperLimit(int paperLimit) {
        setPaperLimit(paperLimit);
        return this;
    }

    public CompleteUserData assignedPapers(int assignedPapers) {
        setAssignedPapers(assignedPapers);
        return this;
    }

    public CompleteUserData subjectInterests(List<String> subjectInterests) {
        setSubjectInterests(subjectInterests);
        return this;
    }

    public CompleteUserData title(String title) {
        setTitle(title);
        return this;
    }

    public CompleteUserData organisation(String organisation) {
        setOrganisation(organisation);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CompleteUserData)) {
            return false;
        }
        CompleteUserData completeUserData = (CompleteUserData) o;
        return Objects.equals(id, completeUserData.id) && Objects.equals(email, completeUserData.email) && Objects.equals(password, completeUserData.password) && Objects.equals(firstName, completeUserData.firstName) && Objects.equals(lastName, completeUserData.lastName) && Objects.equals(userType, completeUserData.userType) && paperLimit == completeUserData.paperLimit && assignedPapers == completeUserData.assignedPapers && Objects.equals(subjectInterests, completeUserData.subjectInterests) && Objects.equals(title, completeUserData.title) && Objects.equals(organisation, completeUserData.organisation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, firstName, lastName, userType, paperLimit, assignedPapers, subjectInterests, title, organisation);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", userType='" + getUserType() + "'" +
            ", paperLimit='" + getPaperLimit() + "'" +
            ", assignedPapers='" + getAssignedPapers() + "'" +
            ", subjectInterests='" + getSubjectInterests() + "'" +
            ", title='" + getTitle() + "'" +
            ", organisation='" + getOrganisation() + "'" +
            "}";
    }
    
    
}
