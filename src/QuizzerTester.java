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

import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the various other classes in the
 * package
 * 
 * @author Cole Bielby
 *
 */
public class QuizzerTester {


  /**
   * This method tests and make use of the MultipleChoiceQuestion constructor, an accessor (getter)
   * method, overridden method toString() and equal() method defined in the MultipleChoiceQuestion
   * class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testMultipleChoiceQuestion() {

    // Data fields to be used for the question
    String testTitle = "Title";
    String testQuestion = "Question";
    String[] testAnswers = new String[] {"Answer1", "Answer2", "Answer3"};
    int testCorrectIndex = 2;
    int testPointsPossible = 5;

    // Creation of test question
    MultipleChoiceQuestion testQ = new MultipleChoiceQuestion(testTitle, testQuestion, testAnswers,
        testCorrectIndex, testPointsPossible);

    // 1) Tests getter (getTitle())
    if (!testQ.getTitle().equals(testTitle)) {
      System.out.println("MCQ.getTitle() failed to return the correct title");
      return false;
    }

    // 2) Tests toString() method
    String expectedString = "QUESTION TITLE: " + "\"" + testTitle + "\"" + "\n" + "Question:\n"
        + testQuestion + "\n" + "Available Answers:\n" + testQ.getAnswers();
    if (!expectedString.equals(testQ.toString())) {
      System.out.println("MCQ.toString() failed to return the correct string representation");
      return false;
    }

    // 3) Tests equals() method
    MultipleChoiceQuestion testQCopy = new MultipleChoiceQuestion(testTitle, testQuestion,
        testAnswers, testCorrectIndex, testPointsPossible);
    MultipleChoiceQuestion notEqual = new MultipleChoiceQuestion("Diff Title", "Diff Q",
        testAnswers, testCorrectIndex, testPointsPossible);
    if (!testQ.equals(testQCopy)) {
      System.out.println("MCQ.equals() failed to recognize an equal question");
      return false;
    }
    if (testQ.equals(notEqual)) {
      System.out.println("MCQ.equals() incorrectly determined two different questions are equal");
      return false;
    }

    return true; // If no error occurs
  }

  /**
   * This method tests and makes use of the LinkedNode constructor, an accessor (getter) method, and
   * a mutator (setter) method defined in the LinkedNode class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLinkedNode() {

    // Constructs two nodes, one is after the another
    LinkedNode testNodeNext = new LinkedNode(10);
    LinkedNode testNode = new LinkedNode(6, testNodeNext); // testNodeNext is after this node

    // 0) If constructor doesn't work
    if (testNodeNext == null || testNode == null || testNodeNext.getData() == null
        || testNode.getData() == null) {
      System.out.println("Constructor for LinkedNode did not correctly initialize a node");
      return false;
    }

    // 1) Tests initial getData
    if ((Integer) testNode.getData() != 6) {
      System.out.println("LinkedNode.getData() failed to return the correct data");
      return false;
    }

    // 2) Tests accessor method getNext()
    if ((Integer) testNode.getNext().getData() != 10) {
      System.out.println("LinkedNode.getNext().getData() failed to return the next node's data");
      return false;
    }

    // 3) Tests setNext()
    LinkedNode testNode3 = new LinkedNode(3);
    testNodeNext.setNext(testNode3);
    if ((Integer) testNodeNext.getNext().getData() != 3) {
      System.out
          .println("LinkedNode.setNext() failed to correctly set a node's next node in the list");
      return false;
    }

    // 4) Tests toString()
    String expectedString = "10->";
    if (!testNodeNext.toString().equals(expectedString)) {
      System.out
          .println("LinkedNode.toString() failed to return the correct string rep. of a node");
      return false;
    }

    // 5) Tests toString() on a final node
    String expectedString2 = "3";
    if (!testNode3.toString().equals(expectedString2)) {
      System.out.println(
          "LinkedNode().toString() failed to return the correct string rep of the " + "final node");
      return false;
    }

    return true; // If all tests pass
  }

  /**
   * Tester for ListQuizzer.addFirst() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddFirst() {
    // Creation of a test question
    String testTitle = "Title";
    String testQuestion = "Question";
    String[] testAnswers = new String[] {"Answer1", "Answer2", "Answer3"};
    int testCorrectIndex = 2;
    int testPointsPossible = 5;
    MultipleChoiceQuestion testQ = new MultipleChoiceQuestion(testTitle, testQuestion, testAnswers,
        testCorrectIndex, testPointsPossible);

    // 1) Tests adding first to empty quiz
    ListQuizzer testQuiz = new ListQuizzer();
    testQuiz.addFirst(testQ);
    if (testQuiz.size() != 1 || !testQuiz.getFirst().equals(testQ)) {
      System.out.println("LQ.addFirst failed to correctly add a head to an empty quiz");
      return false;
    }

    // 2) Tests adding first to a quiz with 1+ question
    MultipleChoiceQuestion testQ2 = new MultipleChoiceQuestion(testTitle + "2", testQuestion + "2",
        testAnswers, testCorrectIndex - 1, testPointsPossible + 5);
    testQuiz.addFirst(testQ2);
    if (testQuiz.size() != 2 || !testQuiz.getFirst().equals(testQ2)) {
      System.out.println(
          "LQ.addFirst failed to add a question to the head when another question was in the quiz");
      return false;
    }

    return true; // If no errors
  }

  /**
   * Tester for ListQuizzer.addLast() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddLast() {
    // Creation of a test question
    String testTitle = "Title";
    String testQuestion = "Question";
    String[] testAnswers = new String[] {"Answer1", "Answer2", "Answer3"};
    int testCorrectIndex = 2;
    int testPointsPossible = 5;
    MultipleChoiceQuestion testQ = new MultipleChoiceQuestion(testTitle, testQuestion, testAnswers,
        testCorrectIndex, testPointsPossible);

    // 1) Tests adding last to empty quiz
    ListQuizzer testQuiz = new ListQuizzer();
    testQuiz.addLast(testQ);
    if (testQuiz.size() != 1 || !testQuiz.getLast().equals(testQ)) {
      System.out.println("LQ.addLast failed to correctly add a tail to an empty quiz");
      return false;
    }

    // 2) Tests adding last to quiz with 1+ questions
    MultipleChoiceQuestion testQ2 = new MultipleChoiceQuestion(testTitle + "2", testQuestion + "2",
        testAnswers, testCorrectIndex - 1, testPointsPossible + 5);
    testQuiz.addLast(testQ2);
    if (testQuiz.size() != 2 || !testQuiz.getLast().equals(testQ2)) {
      System.out.println(
          "LQ.addLast failed to add a question to the tail when another question was in the quiz");
      return false;
    }

    return true; // If no errors
  }

  /**
   * Tests get method for ListQuizzer
   * 
   * @return true if it works false if not
   */
  public static boolean testGet() {
    // Creation of a test questions and test
    String testTitle = "Title";
    String testQuestion = "Question";
    String[] testAnswers = new String[] {"Answer1", "Answer2", "Answer3"};
    int testCorrectIndex = 2;
    int testPointsPossible = 5;
    MultipleChoiceQuestion testQ = new MultipleChoiceQuestion(testTitle, testQuestion, testAnswers,
        testCorrectIndex, testPointsPossible);
    MultipleChoiceQuestion testQ2 = new MultipleChoiceQuestion(testTitle + "2", testQuestion + "2",
        testAnswers, testCorrectIndex - 1, testPointsPossible + 5);
    ListQuizzer testQuiz = new ListQuizzer();
    testQuiz.addLast(testQ);
    testQuiz.addLast(testQ2);

    // 1) Tests get for the first q
    if (!testQuiz.get(0).equals(testQ)) {
      System.out.println("LQ.get() failed to get the first element correctly");
      return false;
    }

    // 2) Tests for second q
    if (!testQuiz.get(1).equals(testQ2)) {
      System.out.println("LQ.get() failed to get the second element correctly");
      return false;
    }
    return true; // If no error
  }

  /**
   * Tester for ListQuizzer.add() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAdd() {
    // Creation of a test questions and test
    String testTitle = "Title";
    String testQuestion = "Question";
    String[] testAnswers = new String[] {"Answer1", "Answer2", "Answer3"};
    int testCorrectIndex = 2;
    int testPointsPossible = 5;
    MultipleChoiceQuestion testQ = new MultipleChoiceQuestion(testTitle, testQuestion, testAnswers,
        testCorrectIndex, testPointsPossible);
    MultipleChoiceQuestion testQ2 = new MultipleChoiceQuestion(testTitle + "2", testQuestion + "2",
        testAnswers, testCorrectIndex - 1, testPointsPossible + 5);
    MultipleChoiceQuestion testQ3 = new MultipleChoiceQuestion(testTitle + "3", testQuestion + "3",
        testAnswers, testCorrectIndex - 1, testPointsPossible + 10);
    ListQuizzer testQuiz = new ListQuizzer();
    testQuiz.add(0, testQ); // Adds to empty quiz
    testQuiz.add(1, testQ2); // Adds a tail
    testQuiz.add(1, testQ3); // Adds in the middle

    // 1) Tests for adding to empty quiz
    if (!testQuiz.get(0).equals(testQ)) {
      System.out.println("LQ.add() failed to add question to empty quiz");
      return false;
    }
    // 2) Tests for adding a tail, ensuring it stays when adding another
    if (!testQuiz.getLast().equals(testQ2)) {
      System.out.println("LQ.add() failed to add tail question or failed to keep the tail when "
          + "adding other questions");
      return false;
    }
    // 3) Tests for adding in middle
    if (!testQuiz.get(1).equals(testQ3) || testQuiz.size() != 3) {
      System.out
          .println("LQ.add() failed to add to middle of quiz or size was not updated correctly");
      return false;
    }

    return true;
  }

  /**
   * Tester for ListQuizzer.remove() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemove() {
    // Creation of a test questions and test
    String testTitle = "Title";
    String testQuestion = "Question";
    String[] testAnswers = new String[] {"Answer1", "Answer2", "Answer3"};
    int testCorrectIndex = 2;
    int testPointsPossible = 5;
    MultipleChoiceQuestion testQ = new MultipleChoiceQuestion(testTitle, testQuestion, testAnswers,
        testCorrectIndex, testPointsPossible);
    MultipleChoiceQuestion testQ2 = new MultipleChoiceQuestion(testTitle + "2", testQuestion + "2",
        testAnswers, testCorrectIndex - 1, testPointsPossible + 5);
    MultipleChoiceQuestion testQ3 = new MultipleChoiceQuestion(testTitle + "3", testQuestion + "3",
        testAnswers, testCorrectIndex - 1, testPointsPossible + 10);
    ListQuizzer testQuiz = new ListQuizzer();
    testQuiz.add(0, testQ); // Adds to empty quiz
    testQuiz.add(1, testQ2); // Adds a tail
    testQuiz.add(1, testQ3); // Adds in the middle (list is testQ, testQ3, testQ2)

    // 1) Removing a middle element
    MultipleChoiceQuestion firstRemoved = testQuiz.remove(1);
    if (!firstRemoved.equals(testQ3) || testQuiz.size() != 2) {
      System.out.println(
          "LQ.remove() failed to removed question from middle of list or update size correctly");
      return false;
    }

    // 2) Removing the head
    MultipleChoiceQuestion headRemoved = testQuiz.remove(0);
    if (!headRemoved.equals(testQ) || testQuiz.size() != 1) {
      System.out.println("LQ.remove() failed to remove head question");
      return false;
    }

    return true;
  }

  /**
   * Tester for ListQuizzer.removeFirst() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemoveFirst() {
    // Creation of a test questions and test
    String testTitle = "Title";
    String testQuestion = "Question";
    String[] testAnswers = new String[] {"Answer1", "Answer2", "Answer3"};
    int testCorrectIndex = 2;
    int testPointsPossible = 5;
    MultipleChoiceQuestion testQ = new MultipleChoiceQuestion(testTitle, testQuestion, testAnswers,
        testCorrectIndex, testPointsPossible);
    MultipleChoiceQuestion testQ2 = new MultipleChoiceQuestion(testTitle + "2", testQuestion + "2",
        testAnswers, testCorrectIndex - 1, testPointsPossible + 5);
    ListQuizzer testQuiz = new ListQuizzer();
    testQuiz.addLast(testQ);
    testQuiz.addLast(testQ2);

    MultipleChoiceQuestion returnedQ = testQuiz.removeFirst();

    // 1) Tests removing first of multiple elements
    if (!returnedQ.equals(testQ) || testQuiz.size() != 1) {
      System.out.println(
          "LQ.removeFirst() failed to remove/return the first question or didn't update size");
      return false;
    }

    // 2) Tests removing only element
    MultipleChoiceQuestion returnedQ2 = testQuiz.removeFirst();
    if (!returnedQ2.equals(testQ2) || testQuiz.size() != 0) {
      System.out
          .println("LQ.removeFirst() failed to remove the first question of a 1 question quiz");
      return false;
    }

    return true;
  }

  /**
   * Tester for ListQuizzer.removeLast() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemoveLast() {
    // Creation of a test questions and test
    String testTitle = "Title";
    String testQuestion = "Question";
    String[] testAnswers = new String[] {"Answer1", "Answer2", "Answer3"};
    int testCorrectIndex = 2;
    int testPointsPossible = 5;
    MultipleChoiceQuestion testQ = new MultipleChoiceQuestion(testTitle, testQuestion, testAnswers,
        testCorrectIndex, testPointsPossible);
    MultipleChoiceQuestion testQ2 = new MultipleChoiceQuestion(testTitle + "2", testQuestion + "2",
        testAnswers, testCorrectIndex - 1, testPointsPossible + 5);
    ListQuizzer testQuiz = new ListQuizzer();
    testQuiz.addLast(testQ);
    testQuiz.addLast(testQ2);

    MultipleChoiceQuestion returnedQ = testQuiz.removeLast();

    // 1) Tests removing last of multiple elements
    if (!returnedQ.equals(testQ2) || testQuiz.size() != 1) {
      System.out.println(
          "LQ.removeLast() failed to remove/return the last question or didn't update size");
      return false;
    }

    // 2) Tests removing only element
    MultipleChoiceQuestion returnedQ2 = testQuiz.removeLast();
    if (!returnedQ2.equals(testQ) || testQuiz.size() != 0) {
      System.out.println("LQ.removeLast() failed to remove the last question of a 1 question quiz");
      return false;
    }

    return true;
  }

  /**
   * Tests other methods like contains and clear
   * 
   * @return true if functional, false if not
   */
  public static boolean testMisc() {
    // Creation of a test questions and test
    String testTitle = "Title";
    String testQuestion = "Question";
    String[] testAnswers = new String[] {"Answer1", "Answer2", "Answer3"};
    int testCorrectIndex = 2;
    int testPointsPossible = 5;
    MultipleChoiceQuestion testQ = new MultipleChoiceQuestion(testTitle, testQuestion, testAnswers,
        testCorrectIndex, testPointsPossible);
    MultipleChoiceQuestion testQ2 = new MultipleChoiceQuestion(testTitle + "2", testQuestion + "2",
        testAnswers, testCorrectIndex - 1, testPointsPossible + 5);
    ListQuizzer testQuiz = new ListQuizzer();
    testQuiz.addLast(testQ);
    testQuiz.addLast(testQ2);

    // 1) Tests contains method
    if (!testQuiz.contains(testQ2) || !testQuiz.contains(testQ)) {
      System.out.println("LQ.contains() failed to recognize a question in the quiz");
      return false;
    }

    // 2) Tests clear method and ensures copy works later
    // Makes copy to test again
    ListQuizzer testForLater = testQuiz.copy();
    ListQuizzer testQuiz2 = testQuiz.copy();
    testQuiz2.addLast(testQ);

    testQuiz.clear();
    if (testQuiz.size() != 0) {
      System.out.println("LQ.clear() failed to clear out the quiz");
      return false;
    }
    testQuiz2.clear();
    if (testQuiz2.size() != 0) {
      System.out.println("LQ.clear() failed to clear out the quiz");
      return false;
    }

    
    // 4) Tests calculateTotalPoints()
    if (testForLater.calculateTotalPoints() != 15) {
      System.out.println("LQ.calculateTotalPoints() did not return the correct num");
      return false;
    }
    
    // 5) Tests calculateScore()
    testForLater.get(0).setStudentAnswerIndex(2); // Correct answer
    testForLater.get(1).setStudentAnswerIndex(0); // Incorrect answer
    if (testForLater.calculateScore() != 5) {
      System.out.println("LQ.calculateScore() did not return the correct score");
      return false;
    }
    
    return true; // If no errors
  }

  /**
   * This method checks for the correctness of QuizQuestionsIterator class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testQuizQuestionsIterator() {
    // Creation of a test questions and test
    String testTitle = "Title";
    String testQuestion = "Question";
    String[] testAnswers = new String[] {"Answer1", "Answer2", "Answer3"};
    int testCorrectIndex = 2;
    int testPointsPossible = 5;
    MultipleChoiceQuestion testQ = new MultipleChoiceQuestion(testTitle, testQuestion, testAnswers,
        testCorrectIndex, testPointsPossible);
    MultipleChoiceQuestion testQ2 = new MultipleChoiceQuestion(testTitle + "2", testQuestion + "2",
        testAnswers, testCorrectIndex - 1, testPointsPossible + 5);
    MultipleChoiceQuestion testQ3 = new MultipleChoiceQuestion(testTitle + "3", testQuestion + "3",
        testAnswers, testCorrectIndex - 1, testPointsPossible + 10);
    LinkedNode node1 = new LinkedNode(testQ);
    LinkedNode node2 = new LinkedNode(testQ2);
    LinkedNode node3 = new LinkedNode(testQ3);
    node1.setNext(node2);
    node2.setNext(node3);

    QuizQuestionsIterator iterator = new QuizQuestionsIterator(node1); // Iterator begins at node 1

    // 1) Should return true (next node is 1)
    if (!iterator.hasNext() || !iterator.next().equals(node1.getData())) {
      System.out.println("QQI.next() failed to return the next node's data (test 1) or did not "
          + "recognize there is a next node");
      return false;
    }

    // 2) Should return true (next node is 2)
    if (!iterator.hasNext() || !iterator.next().equals(node2.getData())) {
      System.out.println("QQI.next() failed to return the next node's data (test 2) or did not "
          + "recognize there is a next node");
      return false;
    }
    // 3) Should return true (next node is 3)
    if (!iterator.hasNext() || !iterator.next().equals(node3.getData())) {
      System.out.println("QQI.next() failed to return the next node's data (test 2) or did not "
          + "recognize there is a next node");
      return false;
    }

    // 4) Has next should be false
    if (iterator.hasNext()) {
      System.out.println("QQI.hasNext() failed to return false when there are no more nodes");
      return false;
    }
    return true; // If no errors
  }

  /**
   * This method checks for the correctness of IncorrectQuestionsIterator class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testInCorrectQuestionsIterator() {
    String testTitle = "Title";
    String testQuestion = "Question";
    String[] testAnswers = new String[] {"Answer1", "Answer2", "Answer3"};
    int testCorrectIndex = 2;
    int testPointsPossible = 5;
    MultipleChoiceQuestion testQ = new MultipleChoiceQuestion(testTitle, testQuestion, testAnswers,
        testCorrectIndex, testPointsPossible);
    testQ.setStudentAnswerIndex(testCorrectIndex); // Student got it correct
    MultipleChoiceQuestion testQ2 = new MultipleChoiceQuestion(testTitle + "2", testQuestion + "2",
        testAnswers, testCorrectIndex, testPointsPossible + 5);
    testQ2.setStudentAnswerIndex(0); // Student got it incorrect
    MultipleChoiceQuestion testQ3 = new MultipleChoiceQuestion(testTitle + "3", testQuestion + "3",
        testAnswers, testCorrectIndex, testPointsPossible + 10);
    testQ3.setStudentAnswerIndex(0); // Student got it incorrect
    MultipleChoiceQuestion testQ4 = new MultipleChoiceQuestion(testTitle + "4", testQuestion + "3",
        testAnswers, testCorrectIndex, testPointsPossible + 10);
    testQ4.setStudentAnswerIndex(testCorrectIndex); // Student got it correct
    LinkedNode node1 = new LinkedNode(testQ);
    LinkedNode node2 = new LinkedNode(testQ2);
    LinkedNode node3 = new LinkedNode(testQ3);
    LinkedNode node4 = new LinkedNode(testQ4);
    node1.setNext(node2);
    node2.setNext(node3);
    node3.setNext(node4); // node1 -> node2 -> node3 -> node 4 (c, i, i, c)

    IncorrectQuestionsIterator iterator = new IncorrectQuestionsIterator(node1);

    // 1) Iterator should return node2 (first incorrect answer)
    if (!iterator.hasNext() || !iterator.next().equals(node2.getData())) {
      System.out.println("ICQI.next() failed to return the first incorrect answer or failed to "
          + "recognize another incorrect answer");
      return false;
    }

    // 2) Iterator should return node 3
    if (!iterator.hasNext() || !iterator.next().equals(node3.getData())) {
      System.out.println("ICQI.next() failed to return the second incorrect answer or failed to "
          + "recognize another incorrect answer");
      return false;
    }

    // 3) Should not see another incorrect answer
    if (iterator.hasNext()) {
      System.out.println("ICQI.hasNext() recognized another incorrect answer when there was not");
      return false;
    }

    return true; // If no errors
  }

  /**
   * This method checks for the correctness of CorrectQuestionsIterator class
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testCorrectQuestionsIterator() {
    String testTitle = "Title";
    String testQuestion = "Question";
    String[] testAnswers = new String[] {"Answer1", "Answer2", "Answer3"};
    int testCorrectIndex = 2;
    int testPointsPossible = 5;
    MultipleChoiceQuestion testQ = new MultipleChoiceQuestion(testTitle, testQuestion, testAnswers,
        testCorrectIndex, testPointsPossible);
    testQ.setStudentAnswerIndex(testCorrectIndex); // Student got it correct
    MultipleChoiceQuestion testQ2 = new MultipleChoiceQuestion(testTitle + "2", testQuestion + "2",
        testAnswers, testCorrectIndex, testPointsPossible + 5);
    testQ2.setStudentAnswerIndex(0); // Student got it incorrect
    MultipleChoiceQuestion testQ3 = new MultipleChoiceQuestion(testTitle + "3", testQuestion + "3",
        testAnswers, testCorrectIndex, testPointsPossible + 10);
    testQ3.setStudentAnswerIndex(0); // Student got it incorrect
    MultipleChoiceQuestion testQ4 = new MultipleChoiceQuestion(testTitle + "4", testQuestion + "3",
        testAnswers, testCorrectIndex, testPointsPossible + 10);
    testQ4.setStudentAnswerIndex(testCorrectIndex); // Student got it correct
    LinkedNode node1 = new LinkedNode(testQ);
    LinkedNode node2 = new LinkedNode(testQ2);
    LinkedNode node3 = new LinkedNode(testQ3);
    LinkedNode node4 = new LinkedNode(testQ4);
    node1.setNext(node2);
    node2.setNext(node3);
    node3.setNext(node4); // node1 -> node2 -> node3 -> node 4 (c, i, i, c)

    CorrectQuestionsIterator iterator = new CorrectQuestionsIterator(node1);

    // 1) Should return first node (first correct node)
    if (!iterator.hasNext() || !iterator.next().equals(node1.getData())) {
      System.out.println("CQI.hasNext() failed to recognize another correct q, or next() failed to "
          + "produce the next correctnode");
      return false;
    }

    // 2) Should return last node (2nd correct node)
    if (!iterator.hasNext() || !iterator.next().equals(node4.getData())) {
      System.out.println("CQI.hasNext() failed to recognize next correct q, or next() failed to "
          + "return the last correct node");
      return false;
    }

    // 3) Ensures hasNext() does not recognize another node
    if (iterator.hasNext()) {
      System.out.println("CQI.hasNext() incorrectly recognized another correct node");
      return false;
    }

    return true; // If no error
  }


  /**
   * Runs all the tester methods defined in this QuizzerTester
   * 
   * @return true if all tests pass and false if any of the tests fail
   */
  public static boolean runAllTests() {
    boolean allCorrect = true;

    try {
      System.out.println("testMultipleChoiceQuestion(): " + testMultipleChoiceQuestion());
      if (testMultipleChoiceQuestion() == false) {
        allCorrect = false;
      }
      System.out.println("testLinkedNode(): " + testLinkedNode());
      if (testLinkedNode() == false) {
        allCorrect = false;
      }
      System.out.println("testAddFirst(): " + testAddFirst());
      if (testAddFirst() == false) {
        allCorrect = false;
      }
      System.out.println("testAddLast(): " + testAddLast());
      if (testAddLast() == false) {
        allCorrect = false;
      }
      System.out.println("testGet(): " + testGet());
      if (testGet() == false) {
        allCorrect = false;
      }
      System.out.println("testAdd(): " + testAdd());
      if (testAdd() == false) {
        allCorrect = false;
      }
      System.out.println("testRemove(): " + testRemove());
      if (testRemove() == false) {
        allCorrect = false;
      }
      System.out.println("testRemoveFirst(): " + testRemoveFirst());
      if (testRemoveFirst() == false) {
        allCorrect = false;
      }
      System.out.println("testRemoveLast(): " + testRemoveLast());
      if (testRemoveLast() == false) {
        allCorrect = false;
      }
      System.out.println("testMisc(): " + testMisc());
      if (testMisc() == false) {
        allCorrect = false;
      }
      System.out.println("testQuizQuestionsIterator(): " + testQuizQuestionsIterator());
      if (testQuizQuestionsIterator() == false) {
        allCorrect = false;
      }
      System.out.println("testInCorrectQuestionsIterator(): " + testInCorrectQuestionsIterator());
      if (testInCorrectQuestionsIterator() == false) {
        allCorrect = false;
      }
      System.out.println("testCorrectQuestionsIterator(): " + testCorrectQuestionsIterator());
      if (testCorrectQuestionsIterator() == false) {
        allCorrect = false;
      }
    } catch (NullPointerException e) {
      System.out.println("A NullPointerException was thrown from the most recent incomplete test");
      return false;
    } catch (NoSuchElementException e) {
      System.out
          .println("A NoSuchElementException was thrown from the most recent incomplete test");
      return false;
    } catch (IndexOutOfBoundsException e) {
      System.out
          .println("An IndexOutOfBounds exception was thrown from the most recent incomplete test");
      return false;
    }

    return allCorrect;
  }

  /**
   * Main method
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.print("runAllTests(): " + runAllTests());
  }
}
