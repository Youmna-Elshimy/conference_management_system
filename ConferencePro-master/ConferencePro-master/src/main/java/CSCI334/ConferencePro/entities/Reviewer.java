package CSCI334.ConferencePro.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import CSCI334.ConferencePro.persistence.ReviewerPersistence;

@Component
@Document(collection = "reviewers")
public class Reviewer extends User {
  private int paperLimit;
  private int assignedPapers;
  private List<String> subjectInterests;

  public Reviewer() {
    super();
    paperLimit = -1;
    subjectInterests = new ArrayList<>();
  }

  // constructor without id, one will be generated in DB
  public Reviewer(String email, String password, String firstName, String lastName, String userType, int paperLimit,
      int assignedPapers, List<String> subjectInterests) {
    super(email, password, firstName, lastName, userType);
    this.paperLimit = paperLimit;
    this.assignedPapers = assignedPapers;
    this.subjectInterests = subjectInterests;
  }

  public Reviewer(String id, String email, String password, String firstName, String lastName, String userType, int paperLimit,
  int assignedPapers, List<String> subjectInterests) {
    super(id, email, password, firstName, lastName, userType);
    this.paperLimit = paperLimit;
    this.assignedPapers = assignedPapers;
    this.subjectInterests = subjectInterests;
  }

  public Reviewer(int paperLimit,int assignedPapers, List<String> subjectInterests) {
    this.paperLimit = paperLimit;
    this.assignedPapers = assignedPapers;
    this.subjectInterests = subjectInterests;
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

  public Reviewer paperLimit(int paperLimit) {
    setPaperLimit(paperLimit);
    return this;
  }

  public Reviewer assignedPapers(int assignedPapers) {
    setAssignedPapers(assignedPapers);
    return this;
  }

  public Reviewer subjectInterests(List<String> subjectInterests) {
    setSubjectInterests(subjectInterests);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Reviewer)) {
            return false;
        }
        Reviewer reviewer = (Reviewer) o;
        return paperLimit == reviewer.paperLimit && assignedPapers == reviewer.assignedPapers && Objects.equals(subjectInterests, reviewer.subjectInterests);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paperLimit, assignedPapers, subjectInterests);
  }

  @Override
  public String toString() {
    return "{" +
      " paperLimit='" + getPaperLimit() + "'" +
      ", assignedPapers='" + getAssignedPapers() + "'" +
      ", subjectInterests='" + getSubjectInterests() + "'" +
      "}";
  }  
  
  // keywords
  public void addSubjectInterest(String keyword) {
    subjectInterests.add(keyword);
  }

  public void removeKeyword(String keyword) {
    // iterates through keywords list and remove the given keyword if found
    Iterator<String> iterator = subjectInterests.iterator();
    while (iterator.hasNext()) {
      String element = iterator.next();
      if (element.equals(keyword)) {
        iterator.remove();
      }
    }
  }

  // persistence
  public Optional<Reviewer> create(ReviewerPersistence persistence) {
    System.out.println("Creating a reviewer in Reviewer.java");
    return persistence.create(this);
  }
  public List<Reviewer> getAll(ReviewerPersistence persistence) {
    return persistence.getAll();
  }
  public Optional<Reviewer> get(ReviewerPersistence persistence) {
    return persistence.get(this);
  }
  public Optional<Reviewer> update(ReviewerPersistence persistence) {
    return persistence.update(this);
  }
  public Optional<String> delete(ReviewerPersistence persistence) {
    return persistence.delete(this);
  }
}
