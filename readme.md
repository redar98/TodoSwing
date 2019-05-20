# Todo Swing
This is the course work for the Design Pattern Labs.

## Design patterns used
  1. **Builder**
  The builder pattern is an object creation software design pattern with the intentions of finding a solution to the telescoping constructor anti-pattern. Builder pattern is used to create instance of very complex object having telescoping constructor in easiest way. Constructors in Java are used to create object and can take parameters required to create object. Lets see an example and learn how to implement builder pattern.

  Allows you to create different flavors of an object while avoiding constructor pollution. Useful when there could be several flavors of an object. Or when there are a lot of steps involved in creation of an object.

  In our project we used Builder pattern for our Task Objects. We did this because in the future we want our task objects to have many additional fields. In this situation we would have to create many additional constructors that would match a requirement. We definitely can do so, but once there are more than 2~3 constructor parameters, we start to forget their meanings. Also having more than 3 parameters is considered a bad practice. That is why we decided to go with builder pattern.

  ~~~Java
  @Getter
  @AllArgsConstructor
  public class Task {

       private String message;
       private Date addedDate;

       public static class Builder {
           private String message;
           private Date addedDate;

           public Builder(String message) {
               this.message = message;
           }

           public Builder setMessage(String message) {
               this.message = message;
               return this;
           }

           public Builder setAddedDate(Date date) {
               this.addedDate = date;
               return this;
           }

           public Task build() {
               return new Task(this.message, this.addedDate);
           }
       }
  }
  ~~~

  2. **Singleton**
  Singleton design pattern is used when you want to have only one instance of a given class. It is a creational design pattern wherein we deal with the creation of objects.

  There are many ways to make a class singleton in Java. All these ways differs in their implementation of the pattern, but in the end, they all achieve the same end result of a single instance.

  We used Eager initialization for our model class that will hold the actual list and make operations on them available. Our UI window will mainly use this class’ instance to handle and manage user tasks.

  3. **Decorator**
  The decorator design pattern allows us to dynamically add functionality and behavior to an object without affecting the behavior of other existing objects in the same class.

  We use inheritance to extend the behavior of the class. This takes place at compile time, and all of the instances of that class get the extended behavior.

  Decorator pattern helped us make tasks even more individual depending on their priority (in the future this will make our job much easier). This gives us full control over the tasks by wrapping them inside decorator classes.

  4. **Proxy**
  Proxy is a Conceptual design pattern that provides an object that acts as a substitute for a real service object used by a client. Proxy receives client requests, does some work (access control, caching, etc.) and then passes request to a service object.

  The proxy object has the same interface as a service, which makes it interchangeable with a real object when passed to a client.

  A proxy, in its most general form, is a class functioning as an interface to something else. A proxy is a wrapper or agent object that is being called by the client to access the real serving object behind the scenes. Use of the proxy can simply be forwarding to the real object, or can provide additional logic. In the proxy extra functionality can be provided, for example caching when operations on the real object are resource intensive, or checking preconditions before operations on the real object are invoked.

  5. **Iterator**
  Iterator pattern is very commonly used design pattern in Java and .Net programming environment. This pattern is used to get a way to access the elements of a collection object in sequential manner without any need to know its underlying representation. Iterator pattern falls under behavioral pattern category.

  Iterator Pattern is a relatively simple and frequently used design pattern. There are a lot of data structures/collections available in every language. Each collection must provide an iterator that lets it iterate through its objects. However while doing so it should make sure that it does not expose its implementation.
  Our application requires us to maintain a list of tasks. Eventually, some part of our code will require to iterate over all tasks, that's why we used the iterator pattern for our task list objects. We have TaskList class that hold the tasks, there we will need to have an iterator for this list.

  In Java we can implement the Iterable<T> interface to mark our class as an object that can be iterated over. In the TodoList class we implement an interface that indicates the available methods to outer packages. We extended that interface to mark it as iterable object. This Iterable<T> interface requires iterator() method to be implemented.

  6. **Observer**
  The observer pattern defines a one-to-many dependency between objects so that when one object changes state, all of its dependents are notified and updated automatically. The object which is being watched is called the subject. The objects which are watching the state changes are called observers or listeners.

  By using Swing framework in Java we can make a class extend AbstractListModel<T> interface to give this class ability to have an event listener. Later when we update our list of tasks we want to fire an event to notify the listeners about the list change. We used eventListener approach to update our User Interface once the list in our model changes. To do this we extended our TodoListModel class with AbstractListModel<Task>.
