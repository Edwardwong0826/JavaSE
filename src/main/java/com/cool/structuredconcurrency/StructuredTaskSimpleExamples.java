package com.cool.structuredconcurrency;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.concurrent.StructuredTaskScope.Subtask.State;

import com.cool.structuredconcurrency.LongRunningTask.TaskResponse;

public class StructuredTaskSimpleExamples {
    
    public static void main(String[] args) throws Exception {
    	
        System.out.println("Main : Started");

        // Simulate interrupt to the Main Thread before Child threads complete
        // the StructuredTaskScope join method will throw an interrupted exception to the caller
        // at the same time also send the cancellation request to the two virtual threads which are running
        // assuming at this point the main threads was waiting at scope join, so join is able to handle interrupted exception
        //interruptMain();


        //exampleCompleteAllTasks();
        //exampleShutdownOnFailure();
        exampleShutdownOnSuccess();
        
        System.out.println("Main : Completed");

    }
    
    private static void exampleShutdownOnFailure()
            throws Exception {

        // ShutdownOnFailure shutdown when first child thread fails
        // this is used when we want to immediately return from scope StructuredTaskScope join if any subtasks fails
        try(var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            
            var dataTask = new LongRunningTask("dataTask", 3,  "row1", true);
            var restTask = new LongRunningTask("restTask", 10, "json2", false);
            
            // Start running the tasks in parallel 
            Subtask<TaskResponse> dataSubTask = scope.fork(dataTask);
            Subtask<TaskResponse> restSubTask = scope.fork(restTask);
        
            // Wait till first Child Task fails. Send cancellation to
            // all other Child Tasks
            scope.join();
            scope.throwIfFailed();
            //scope.throwIfFailed(t->new Exception(t)); // or we can choose the exception we want throw
            
            // Handle Success Child Task Results
            System.out.println(dataSubTask.get());
            System.out.println(restSubTask.get());
        }

    }

    private static void exampleShutdownOnSuccess()
            throws InterruptedException, ExecutionException {


        // ShutdownOnSuccess Shutdown when first child thread success
        // this is used when we want to immediately return from scope StructuredTaskScope join if any subtasks succeeds
        try(var scope = new StructuredTaskScope.ShutdownOnSuccess<TaskResponse>()) {

            // if any first of the task failed, it will keep waiting other tasks to be completed until there is first success task
            // otherwise if all failed it will just throw execution exception
            var wthr1Task = new LongRunningTask("Weather-1", 3,  "32", false);
            var wthr2Task = new LongRunningTask("Weather-2", 10, "30", false);

            // Start running the tasks in parallel
            Subtask<TaskResponse> subTask1 = scope.fork(wthr1Task);
            Subtask<TaskResponse> subTask2 = scope.fork(wthr2Task);

            // Wait till first Child Task Succeeds. Send Cancellation
            // to all other Child Tasks
            scope.join();

            // Handle Successful Child Task
            TaskResponse result = scope.result();
            //TaskResponse result = scope.result(t->new Exception()); // we can specify any exception we want to throw if get threw
            System.out.println(result);
        }

    }

	private static void exampleCompleteAllTasks() throws InterruptedException {

        // StructuredTaskScope will ensure all the child threads when complete is guarantee to exit or terminate
        try(var scope = new StructuredTaskScope<TaskResponse>()) {
            
            var expTask = new LongRunningTask("expedia-task", 3,  "100$", false);
            var hotTask = new LongRunningTask("hotwire-task", 10, "110$", false);

            // All of these tasks will get their own virtual thread
            // Start running the tasks in parallel
            Subtask<TaskResponse> expSubTask = scope.fork(expTask);
            Subtask<TaskResponse> hotSubTask = scope.fork(hotTask);

			// Code to simulate random exception being thrown
			// This should still terminate the child threads
            if (true) {
            	Thread.sleep(Duration.ofSeconds(2));
            	throw new RuntimeException("Some Exception");
            }

            // No thread cancellation is going to be sent
            // Join will wait for all tasks to complete (success or not)
            scope.join();

            // Scope.joinUntil will wait the time that we specify, if the tasks are not completed it will just exit
            // And at the same time send the interrupts to the two subtasks
//            try{
//                scope.joinUntil(Instant.now().plus(Duration.ofSeconds(2)));
//            } catch (TimeoutException e) {
//                throw new RuntimeException(e);
//            }


            // Handle Child Task Results (might have succeeded or failed)
            State expState = expSubTask.state();
            if (expState == State.SUCCESS) 
                System.out.println(expSubTask.get());
            else if (expState == State.FAILED) 
                System.out.println(expSubTask.exception());
            
            State hotState = hotSubTask.state();
            if (hotState == State.SUCCESS) 
                System.out.println(hotSubTask.get());
            else if (hotState == State.FAILED)
                System.out.println(hotSubTask.exception());

        // StructuredTaskScope it will use thread cancellation to ensure before end of scope or block
        // to make sure all the thread or child thread are exit or terminate no matter what
        }

    }

    
    private static void interruptMain() {
        
        Thread mainThread = Thread.currentThread();

        // Notice this is the new thread that created to interrupt to the main thread
        Thread.ofPlatform().start(() -> {
            
            try {
                Thread.sleep(Duration.ofSeconds(2));
                mainThread.interrupt();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

}
