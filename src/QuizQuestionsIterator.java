//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P07 Quiz
// Course: CS 300 Fall 2022
//
// Author: Cole Bielby
// Email: cbielby@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is an iterator that moves through MultipleChoiceQuestion(s) in a singly linked list defined
 * by its head
 * 
 * @author Cole Bielby
 *
 */
public class QuizQuestionsIterator implements Iterator<MultipleChoiceQuestion> {

  private LinkedNode<MultipleChoiceQuestion> next; // Stores the next node

  /**
   * Creates a new QuizQuestionsIterator to start iterating through a singly linked list storing
   * MultipleChoiceQuestion elements
   * 
   * @param startNode pointer to the head of the singly linked list
   */
  public QuizQuestionsIterator(LinkedNode<MultipleChoiceQuestion> startNode) {
    next = startNode;
  }

  /**
   * Returns true if this iteration has more MultipleChoiceQuestion(s).
   * 
   * @return true if there are more MultipleChoiceQuestion(s) in this iteration
   */
  @Override
  public boolean hasNext() {
    if (next == null) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Returns the next MultipleChoiceQuestion in this iteration
   * 
   * @return the next MultipleChoiceQuestion in this iteration
   * @throws NoSuchElementException with a descriptive error message if there are no more questions
   *                                in this iteration
   */
  @Override
  public MultipleChoiceQuestion next() throws NoSuchElementException {
    if (!this.hasNext()) {
      throw new NoSuchElementException("No more questions in this iterator");
    }

    LinkedNode temp = next;
    next = next.getNext();
    return (MultipleChoiceQuestion) temp.getData();
  }

}
