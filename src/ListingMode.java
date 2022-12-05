//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P07 Quiz
// Course:   CS 300 Fall 2022
//
// Author:   Cole Bielby
// Email:    cbielby@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////

/**
 * An enum representing the three states of the quiz list:
 * - ALL (all questions)
 * - CORRECT (only correctly answered questions)
 * - INCORRECT (only incorrectly answered questions)
 */
public enum ListingMode {
  /**
   * ALL stands for listing ALL the MultipleChoiceQuestions in the quiz6
   */
  ALL, 
  /**
   * CORRECT stands for listing the correct MultipleChoiceQuestions only (answered correctly)
   */
  CORRECT, 
  /**
   * INCORRECT stands for listing the incorrect MultipleChoiceQuestions only (answered incorrectly)
   */
  INCORRECT;
}