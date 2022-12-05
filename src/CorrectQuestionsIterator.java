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
 * Implements an iterator to iterate over correctly answered MultipleChoiceQuestion(s) stored in a
 * singly linked list defined by its head.
 * 
 * @author Cole Bielby
 *
 */
public class CorrectQuestionsIterator implements Iterator<MultipleChoiceQuestion> {

  private LinkedNode<MultipleChoiceQuestion> next; // The next correct question

  /**
   * Creates a new CorrectQuestionsIterator to start iterating through a singly linked list storing
   * MultipleChoiceQuestion elements
   * 
   * @param startNode pointer to the head of the singly linked list
   */
  public CorrectQuestionsIterator(LinkedNode<MultipleChoiceQuestion> startNode) {
    // Ensures no method is called on null data
    if (startNode == null) {
      next = null;
      return;
    }

    boolean isCorrect = false;
    LinkedNode temp = startNode;
    // Checks if first answer is correct
    if (startNode.getData().getStudentAnswerIndex() == startNode.getData()
        .getCorrectAnswerIndex()) {
      isCorrect = true;
    }

    // Exits when a correct answer is found
    while (!isCorrect) {
      temp = temp.getNext();
      if (temp == null) {
        next = null; // If there are no correct answers, next equals null
        break; // Breaks when there are no correct answers
      }
      // If the current temp has a correct answer
      if (((MultipleChoiceQuestion) temp.getData())
          .getStudentAnswerIndex() == ((MultipleChoiceQuestion) temp.getData())
              .getCorrectAnswerIndex()) {
        isCorrect = true;
      }
    }

    next = temp;
  }

  /**
   * Returns true if this iteration has more MultipleChoiceQuestion(s) answered correctly.
   * 
   * @return true if there are more correct MultipleChoiceQuestion(s) in this iteration
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
   * Returns the next correct MultipleChoiceQuestion in this iteration
   * 
   * @return the next correct MultipleChoiceQuestion in this iteration
   * @throws NoSuchElementException with a descriptive error message if there are no more correct
   *                                questions in this iteration
   */
  @Override
  public MultipleChoiceQuestion next() throws NoSuchElementException {
    if (!this.hasNext()) {
      throw new NoSuchElementException("No more correct answers");
    }

    LinkedNode currentNext = next; // Stores next to return later
    boolean isCorrect = false;
    // Sets next to the next correct item
    while (!isCorrect) {
      next = next.getNext();
      // If there are no more corrects after this, break the loop to prevent error
      if (next == null) {
        break;
      }
      if (next.getData().getStudentAnswerIndex() == next.getData().getCorrectAnswerIndex()) {
        isCorrect = true;
      }
    }

    return (MultipleChoiceQuestion) currentNext.getData();
  }

}
