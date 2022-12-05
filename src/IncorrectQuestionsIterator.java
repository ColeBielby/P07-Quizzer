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
 * Implements an iterator to iterate over incorrectly answered MultipleChoiceQuestion(s) stored in a
 * singly linked list defined by its head.
 * 
 * @author Cole Bielby
 *
 */
public class IncorrectQuestionsIterator implements Iterator<MultipleChoiceQuestion> {

  private LinkedNode<MultipleChoiceQuestion> next; // Next node in list

  /**
   * Creates a new InCorrectQuestionsIterator to start iterating through a singly linked list
   * storing MultipleChoiceQuestion elements
   * 
   * @param startNode pointer to the head of the singly linked list
   */
  public IncorrectQuestionsIterator(LinkedNode<MultipleChoiceQuestion> startNode) {
    boolean isIncorrect = false;
    LinkedNode temp = startNode;
    // Checks if first answer is incorrect
    if (startNode.getData().getStudentAnswerIndex() != startNode.getData()
        .getCorrectAnswerIndex()) {
      isIncorrect = true;
    }

    // Exits when an incorrect answer is found
    while (!isIncorrect) {
      temp = temp.getNext();
      if (temp == null) {
        next = null; // If there are no incorrect answers, next equals null
        break; // Breaks when there are no incorrect answers
      }
      // If the current temp has an incorrect answer
      if (((MultipleChoiceQuestion) temp.getData())
          .getStudentAnswerIndex() != ((MultipleChoiceQuestion) temp.getData())
              .getCorrectAnswerIndex()) {
        isIncorrect = true;
      }
    }

    next = temp;
  }

  /**
   * Returns true if this iteration has more MultipleChoiceQuestion(s) answered incorrectly.
   * 
   * @return true if there are more incorrect MultipleChoiceQuestion(s) in this iteration
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
   * Returns the next incorrect MultipleChoiceQuestion in this iteration
   * 
   * @return the next incorrect MultipleChoiceQuestion in this iteration
   * @throws NoSuchElementException with a descriptive error message if there are no more incorrect
   *                                questions in this iteration
   */
  @Override
  public MultipleChoiceQuestion next() throws NoSuchElementException {
    if (!this.hasNext()) {
      throw new NoSuchElementException("No more incorrect answers");
    }

    LinkedNode currentNext = next; // Stores next to return later
    boolean isIncorrect = false;
    // Sets next to the next incorrect item
    while (!isIncorrect) {
      next = next.getNext();
      // If there are no more incorrects after this, break the loop to prevent error
      if (next == null) {
        break;
      }
      if (next.getData().getStudentAnswerIndex() != next.getData().getCorrectAnswerIndex()) {
        isIncorrect = true;
      }
    }

    return (MultipleChoiceQuestion) currentNext.getData();
  }
}
