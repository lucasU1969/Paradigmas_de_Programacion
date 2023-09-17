package queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class QueueTest {

  @Test public void test01QueueShouldBeEmptyWhenCreated() {
    assertTrue( new Queue().isEmpty() );
  }
  @Test public void test02AddElementsToTheQueue() {
    assertFalse( queueWithSomething().isEmpty() );
  }
  @Test public void test03AddedElementsIsAtHead() {
    assertEquals( "Something", queueWithSomething().head() );
  }
  @Test public void test04TakeRemovesElementsFromTheQueue() {
    Queue queue = queueWithSomething();
    queue.take();
    assertTrue( queue.isEmpty() );
  }
  @Test public void test05TakeReturnsLastAddedObject() {
    assertEquals( "Something", queueWithSomething().take() );
  }
  @Test public void test06QueueBehavesFIFO() {
    Queue queue = queueWithFirstAndSecond( firstAddedObject, secondAddedObject);
    
    assertEquals( queue.take(), firstAddedObject );
    assertEquals( queue.take(), secondAddedObject );
    assertTrue( queue.isEmpty() );
  }
  @Test public void test07HeadReturnsFirstAddedObject() {
    assertEquals( queueWithFirstAndSecond(firstAddedObject, secondAddedObject).head(), firstAddedObject );
  }
  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
    Queue queue = queueWithSomething();
    assertEquals( 1, queue.size() );
    queue.head();
    assertEquals( 1, queue.size() );
  }
  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, queueWithFirstAndSecond(firstAddedObject, secondAddedObject).size() );
  }
  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    assertThrowsLike(() -> new Queue().take(), EmptySlot.EmptyQueue);
  }
  @Test public void test09CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = queueWithSomething();
    queue.take();
    assertThrowsLike(() -> queue.take(), EmptySlot.EmptyQueue);
  }
  @Test public void test10CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    assertThrowsLike(() -> new Queue().head(), EmptySlot.EmptyQueue);
  }
  private Queue queueWithSomething() {
	  return new Queue().add( "Something" );
  }
  public Queue queueWithFirstAndSecond( Object firstToAdd, Object secondToAdd) {
    return new Queue().add( firstToAdd ).add( secondToAdd );
  }
  private String firstAddedObject = "First";
  
  private String secondAddedObject = "Second";
  
  private void assertThrowsLike( Executable executable, String message ) {
	  assertEquals( message, assertThrows( Error.class, executable ).getMessage() );
  }
}