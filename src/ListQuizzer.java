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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * This class models an iterable singly-linked list data structure which stores elements of type
 * MultipleChoiceQuestion.
 * 
 * @author Cole Bielby
 *
 */
public class ListQuizzer implements Iterable<MultipleChoiceQuestion> {

  private LinkedNode<MultipleChoiceQuestion> head; // Head of this list
  private LinkedNode<MultipleChoiceQuestion> tail; // Tail of this list
  private ListingMode listingMode; // Defines which questions will be listed when iterating thru
  private int size; // total num of MCQs in this list

  /**
   * this constructor creates a new empty instance of ListQuizzer which contains zero elements and
   * sets its listing mode to be ListingMode.ALL by default.
   */
  public ListQuizzer() {
    size = 0;
    listingMode = ListingMode.ALL;
    head = null;
    tail = null;
  }

  /**
   * Checks whether this list is empty
   * 
   * @return true if this list is empty and false otherwise
   */
  public boolean isEmpty() {
    if (this.size() == 0 && this.head == null && this.tail == null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Gets the size of this list
   * 
   * @return the size of this list
   */
  public int size() {
    return this.size;
  }

  /**
   * Sets the listing mode of this list to the one provided as input
   * 
   * @param listingMode listing mode to set
   */
  public void switchMode(ListingMode listingMode) {
    this.listingMode = listingMode;
  }

  /**
   * Adds a specific MultipleChoiceQuestion to a given position of this list
   * 
   * @param index    position index where to add the newQuestion to this list
   * @param question some MultipleChoiceQuestion to add to this list
   * @throws NullPointerException      with a descriptive error message if newQuestion is null
   * @throws IndexOutOfBoundsException with a descriptive error message if index is OUT of the range
   *                                   0 .. size() inclusive
   */
  public void add(int index, MultipleChoiceQuestion question)
      throws NullPointerException, IndexOutOfBoundsException {
    if (question == null) {
      throw new NullPointerException("Question cannot be null");
    }
    if (index < 0 || index > this.size) {
      throw new IndexOutOfBoundsException(
          "Index must be within the range of 0 to size() (both inclusive)");
    }

    // Special case: Empty list or index is one (Uses addFirst method)
    if (this.isEmpty() || index == 0) {
      addFirst(question);
      return;
    }

    // Special case: Index == size (Uses addLast method)
    if (index == this.size) {
      addLast(question);
      return;
    }

    // Other case: adding between questions
    LinkedNode questionToAdd = new LinkedNode(question);
    LinkedNode prevQ = this.head;
    int currIndex = 0;
    while (currIndex < index - 1) { // Gets the question before the index we want to add
      prevQ = prevQ.getNext();
      ++currIndex;
    }
    LinkedNode nextQ = prevQ.getNext(); // Gets the question of the index we want (will be moved up)

    prevQ.setNext(questionToAdd);
    questionToAdd.setNext(nextQ);
    ++size;
  }

  /**
   * Adds a specific MutlipleChoiceQuestion to the head of this list
   * 
   * @param question some MultipleChoiceQuestion to add to the head of this list
   * @throws NullPointerException with a descriptive error message if newQuestion is null
   */
  public void addFirst(MultipleChoiceQuestion question) throws NullPointerException {
    // Checks for null value
    if (question == null) {
      throw new NullPointerException("Cannot pass null question");
    }
    // Turn question into linked node
    LinkedNode newNode = new LinkedNode(question);

    // Special Case: Empty List
    if (this.isEmpty()) {
      this.head = newNode;
      this.tail = newNode;
      ++size;
    } else { // If there are other elements in the quiz
      // Sets questions next node to current head and then makes it the new head
      newNode.setNext(this.head);
      this.head = newNode;
      ++size;
    }
  }

  /**
   * Adds a specific MutlipleChoiceQuestion to the tail of this list
   * 
   * @param question some MultipleChoiceQuestion to add to the tail of this list
   * @throws NullPointerException with a descriptive error message if newQuestion is null
   */
  public void addLast(MultipleChoiceQuestion question) throws NullPointerException {
    // Checks for null value
    if (question == null) {
      throw new NullPointerException("Cannot pass null question");
    }

    LinkedNode newNode = new LinkedNode(question);

    // Special Case: Empty List
    if (this.isEmpty()) {
      this.head = newNode;
      this.tail = newNode;
      ++size;
    } else {
      this.tail.setNext(newNode);
      this.tail = newNode;
      ++size;
    }
  }

  /**
   * This method removes all the questions from this list. The list should be empty after this
   * method is called.
   */
  public void clear() {

    // Special case: if already empty
    if (this.isEmpty()) {
      return;
    }
    // Special case: if size = 1
    if (this.size == 1) {
      this.head = null;
      this.tail = null;
      --size;
      return;
    }

    // Other case: 2 or more elements
    LinkedNode currNode = this.head;
    LinkedNode nextNode = currNode.getNext();
    int currIndex = 0;
    int sizeCopy = this.size;
    while (currIndex < this.size - 1) {
      if (currIndex != 0) {
        currNode = nextNode;
      }
      nextNode = currNode.getNext();
      currNode.setNext(null);
      --sizeCopy;
      ++currIndex;
    }
    this.head = null;
    this.tail = null;
    --sizeCopy;
    this.size = sizeCopy;

  }

  /**
   * Checks whether this list contains a match with someQuestion
   * 
   * @param someQuestion some question to find
   * @return true if this list contains a match with someQuestion and false otherwise
   */
  public boolean contains(MultipleChoiceQuestion someQuestion) {
    if (this.isEmpty()) {
      return false;
    }

    LinkedNode currNode = this.head;
    int currIndex = 0;
    if (currNode.getData().equals(someQuestion)) {
      return true;
    }

    while (currIndex < this.size - 1) {
      currNode = currNode.getNext();
      ++currIndex;
      if (currNode.getData().equals(someQuestion)) {
        return true;
      }
    }
    return false; // If not found
  }

  /**
   * Returns the MultipleChoiceQuestion stored at the given index within this list
   * 
   * @param index index of the MultipleChoiceQuestion to return
   * @return the MultipleChoiceQuestion stored at the given index within this list
   * @throws IndexOutOfBoundsException if index is out of the range 0 .. size()-1 inclusive
   */
  public MultipleChoiceQuestion get(int index) throws IndexOutOfBoundsException {
    if (index >= this.size || index < 0) {
      throw new IndexOutOfBoundsException("Index must be in bounds of the quiz");
    }

    LinkedNode currQ = this.head;
    int currIndex = 0;
    while (currIndex < index) {
      currQ = currQ.getNext();
      ++currIndex;
    }

    return (MultipleChoiceQuestion) currQ.getData();
  }

  /**
   * Gets the MultipleChoiceQuestion at the head of this list
   * 
   * @return the MultipleChoiceQuestion at the head of this list
   * @throws NoSuchElementException with a descriptive error message if this list is empty
   */
  public MultipleChoiceQuestion getFirst() throws NoSuchElementException {
    if (this.isEmpty()) {
      throw new NoSuchElementException("Cannot retrieve first question if quiz is empty");
    }
    return this.head.getData();
  }

  /**
   * Gets the MultipleChoiceQuestion at the tail of this list
   * 
   * @return the MultipleChoiceQuestion at the tail of this list
   * @throws NoSuchElementException with a descriptive error message if this list is empty
   */
  public MultipleChoiceQuestion getLast() throws NoSuchElementException {
    if (this.isEmpty()) {
      throw new NoSuchElementException("Cannot retrieve last question if quiz is empty");
    }
    return this.tail.getData();
  }

  /**
   * Removes and returns the MultipleChoiceQuestion at the given index
   * 
   * @param index of the MultipleChoiceQuestion to remove
   * @return the removed MultipleChoiceQuestion
   * @throws IndexOutOfBoundsException with a descriptive error message if index is out of the range
   *                                   0 .. size()-1 inclusive
   */
  public MultipleChoiceQuestion remove(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index > this.size - 1) {
      throw new IndexOutOfBoundsException("Index must be wihtin 0 to size() - 1");
    }

    // Special Case: size is 1 or attempt to remove first element
    if (this.size == 1 || index == 0) {
      return removeFirst();
    }

    // Special Case: attempt to remove last element
    if (index == this.size - 1) {
      return removeLast();
    }

    // Other Case: attempt to remove question between other elements
    LinkedNode nodeAfterCurr = this.head;
    LinkedNode currQ = null;
    LinkedNode nodeBeforeCurr = null;
    int currIndex = 0;

    while (currIndex < index + 1) {
      if (currIndex == index - 1) { // Gets node before the one to remove
        nodeBeforeCurr = nodeAfterCurr;
      }
      if (currIndex == index) { // Gets the node to remove
        currQ = nodeAfterCurr;
      }
      nodeAfterCurr = nodeAfterCurr.getNext();
      ++currIndex;
    }

    nodeBeforeCurr.setNext(nodeAfterCurr); // Sets node before removed's next value to node after
                                           // the removed
    --size;

    return (MultipleChoiceQuestion) currQ.getData();
  }

  /**
   * Removes and returns the MultipleChoiceQuestion at the head of this list
   * 
   * @return the MultipleChoiceQuestion at the head of this list
   * @throws NoSuchElementException with a descriptive error message if this list is empty
   */
  public MultipleChoiceQuestion removeFirst() throws NoSuchElementException {
    if (this.isEmpty()) {
      throw new NoSuchElementException("Cannot remove from empty quiz");
    }

    MultipleChoiceQuestion temp = this.head.getData();
    // Special case: quiz only has one question
    if (this.size() == 1) {
      this.head = null;
      this.tail = null;
      --size;
      return temp;
    }

    // Other case: quiz has more than 1 element
    this.head = this.head.getNext(); // Sets 2nd element to be the new head
    // Former head no longer has reference to it, up for garbage collection
    --size;

    return temp;
  }

  /**
   * Removes and returns the MultipleChoiceQuestion at the tail of this list
   * 
   * @return the MultipleChoiceQuestion at the tail of this list
   * @throws NoSuchElementException with a descriptive error message if this list is empty
   */
  public MultipleChoiceQuestion removeLast() throws NoSuchElementException {
    if (this.isEmpty()) {
      throw new NoSuchElementException("Cannot remove from empty quiz");
    }
    // Special case: quiz only has one question
    if (this.size() == 1) {
      return removeFirst();
    }

    MultipleChoiceQuestion temp = this.getLast();
    // Other case: quiz has more than 1 element
    LinkedNode secondToLastQ = this.head;
    int currIndex = 0;
    while (currIndex < this.size - 2) {
      secondToLastQ = secondToLastQ.getNext();
      ++currIndex;
    }
    secondToLastQ.setNext(null);
    this.tail = secondToLastQ;
    --size;

    return temp;
  }

  /**
   * Returns an iterator to iterate through this list with respect to the listingMode. If the
   * listingMode is ALL, the returned iterator is initialized to the head of this list. If the
   * listingMode is CORRECT, the returned iterator is initialized to the node carrying first
   * correctly answered question If the listingMode is INCORRECT, the returned iterator is
   * initialized to the node carrying first incorrectly answered question
   * 
   * @return an iterator to iterate through this list with respect to the listingMode of this list.
   */
  @Override
  public Iterator<MultipleChoiceQuestion> iterator() {
    if (this.listingMode.equals(ListingMode.ALL)) {
      return new QuizQuestionsIterator(this.head);
    } else if (this.listingMode == ListingMode.CORRECT) {
      return new CorrectQuestionsIterator(this.head);
    } else {
      return new IncorrectQuestionsIterator(this.head);
    }
  }

  /**
   * Calculates the total points of the correctly answered questions of this ListQuizzer
   * 
   * @return the score of this ListQuizzer
   */
  public int calculateScore() {
    int totalScore = 0;
    LinkedNode currNode = this.head;

    while (currNode != null) {
      if (((MultipleChoiceQuestion) currNode.getData()).isCorrect()) {
        totalScore += ((MultipleChoiceQuestion) currNode.getData()).getPointsPossible();
      }
      currNode = currNode.getNext();
    }
    return totalScore;
  }

  /**
   * Calculates the total possible points of this ListQuizzer
   * 
   * @return the score of this ListQuizzer
   */
  public int calculateTotalPoints() {
    int totalPoints = 0;
    LinkedNode currNode = this.head;

    for (int i = 0; i < this.size; ++i) {
      totalPoints += ((MultipleChoiceQuestion) currNode.getData()).getPointsPossible();
      currNode = currNode.getNext();
    }
    return totalPoints;
  }


  /**
   * Returns a deep copy of this list. This method creates a copy of this list as a new ListQuizzer
   * and adds deep copies of each MultipleChoiceQuestion stored in this list to the deep copy.
   * 
   * @return a deep copy of this list.
   */
  public ListQuizzer copy() {
    ListQuizzer copy = new ListQuizzer();
    LinkedNode currNode = this.head;
    for (int i = 0; i < this.size; ++i) {
      MultipleChoiceQuestion deepCopyQ = ((MultipleChoiceQuestion) currNode.getData()).copy();
      copy.addLast(deepCopyQ);
      currNode = currNode.getNext();
    }
    return copy;
  }

  /**
   * Loads MultipleChoiceQuestions from a file
   * 
   * @author Jeff and Mouna
   * 
   * @param file file to read
   * @return the number of added MultipleChoiceQuestions to this list
   * @throws FileNotFoundException if the file is not found
   */
  public int loadQuestions(File file) throws FileNotFoundException {
    int loadedCount = 0; // count of loaded multiple choice questions
    int answerCount = 0; // count of possible answers per question
    int indexCorrectAnswer = 0; // index of the correct answer
    int points = 0; // possible points for a multiple choice question
    // try to read the file
    Scanner reader = null; // scanner to read the file line by line
    int lineNumber = 0; // number of the last read line

    try {
      reader = new Scanner(file);
      // parse the file lines - while loop to read parts of each multiple choice question
      while (reader.hasNextLine()) { // no more lines to read
        // read title
        String title = reader.nextLine();
        lineNumber++;

        // read question stem
        if (!reader.hasNextLine()) { // no more lines to read
          return loadedCount;
        }
        String question = reader.nextLine();
        lineNumber++;

        // read possible answers count
        if (!reader.hasNextLine()) { // no more lines to read
          return loadedCount;
        }
        String count = reader.nextLine();
        lineNumber++;
        // check the validity of count
        try {
          answerCount = Integer.parseInt(count.trim());
          if (answerCount <= 0 || answerCount > 10) {
            throw new NumberFormatException();
          }
        } catch (NumberFormatException e) { // count invalid - print an error message and return
          System.out
              .println("Syntax error! A positive integer less or equal to 10 is expected at line "
                  + lineNumber + (". Load questions operation interrupted!"));
          return loadedCount;
        }
        // valid count -> create the answerList array
        String[] answerList = new String[answerCount];
        int index = 0;
        while (index < answerCount && reader.hasNextLine()) {
          String answer = reader.nextLine();
          lineNumber++;
          answerList[index] = answer;
          index++;
        }

        // read index of the correct answer
        if (!reader.hasNextLine()) { // no more lines to read
          return loadedCount;
        }
        String line = reader.nextLine();
        lineNumber++;
        try { // check the validity of the index of the correct answer
          indexCorrectAnswer = Integer.parseInt(line.trim());
          if (indexCorrectAnswer < 0 || indexCorrectAnswer >= answerCount) {
            throw new NumberFormatException();
          }
        } catch (NumberFormatException e) { // indexCorrectAnswer invalid - print error and return
          System.out.println("Syntax error! A positive integer less than " + answerCount
              + " is expected at line " + lineNumber + (". Load questions operation interrupted!"));
          return loadedCount;
        }
        // valid index of the correct answer -> read possible points
        // read points
        if (!reader.hasNextLine()) { // no more lines to read
          return loadedCount;
        }
        line = reader.nextLine();

        lineNumber++;
        try { // check the validity of the index of the correct answer
          points = Integer.parseInt(line.trim());

          if (points < 0) {
            throw new NumberFormatException();
          }
        } catch (NumberFormatException e) { // invalid points - print error message and return
          System.out.println("Syntax error! A positive integer for possible points "
              + " is expected at line " + lineNumber + (". Load questions operation interrupted!"));

          return loadedCount;
        }
        // create and add quizQuestion
        MultipleChoiceQuestion quizQuestion =
            new MultipleChoiceQuestion(title, question, answerList, indexCorrectAnswer, points);

        this.addLast(quizQuestion);
        loadedCount += 1;
        System.out.println("Question " + loadedCount + " loaded!");

      }
    } finally {
      if (reader != null)
        reader.close();
    }

    return loadedCount;
  }

  /**
   * Allows a user to take this quiz. The quiz should be taken on a deep copy of this ListQuizzer.
   * This method should not make any changes to the contents of this ListQuizzer.
   * 
   * @author Jeff and Mouna
   * 
   * @return the instance of ListQuizzer taken by the user. It should include the user's responses.
   */
  public ListQuizzer takeQuiz() {

    ListQuizzer copy = this.copy();
    copy.switchMode(ListingMode.ALL);
    Scanner input = new Scanner(System.in);
    for (MultipleChoiceQuestion question : copy) {
      System.out.println(question);
      System.out.print("Enter your answer: ");
      int entry = input.nextInt();
      question.setStudentAnswerIndex(entry - 1);
      if (question.isCorrect()) {
        System.out.println("Correct!");
      } else {
        System.out.println("Incorrect!");
      }
    }
    int correctPoints = copy.calculateScore();
    int totalPoints = copy.calculateTotalPoints();
    System.out.println("Your Score: " + correctPoints);
    System.out.println("Percentage: " + correctPoints / totalPoints);
    input.close();
    return copy;
  }

  /**
   * Returns true if o is a ListQuizzer which has the exact same contents as this ListQuizzer
   * 
   * @author Mouna
   *
   * @param o an object to compare with
   * @return true if o is instanceof ListQuizzer with the exact same contents as this ListQuizzer
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof ListQuizzer) {
      ListQuizzer other = (ListQuizzer) o;
      if (this.size() != other.size())
        return false;
      this.switchMode(ListingMode.ALL);
      other.switchMode(ListingMode.ALL);
      Iterator<MultipleChoiceQuestion> iterator = this.iterator();
      Iterator<MultipleChoiceQuestion> otherIterator = other.iterator();
      while (iterator.hasNext()) {
        if (!iterator.next().equals(otherIterator.next()))
          return false;
      }
      return true;
    }
    return false;
  }
}
