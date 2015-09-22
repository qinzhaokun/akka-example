package com.ebay;

import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinPool;
import scala.concurrent.duration.Duration;

public class Master extends UntypedActor {
	private final int nrOfMessages;
	  private final int nrOfElements;
	 
	  private double pi;
	  private int nrOfResults;
	  private int count;
	  private final long starttime = System.currentTimeMillis();
	 
	  private final ActorRef listener;
	  private final ActorRef workerRouter;

	  public void preStart(){
		  //System.out.println("startting master...");
	  }
    public Master(final int nrOfWorkers, final int nrOfMessages, final int nrOfElements, ActorRef listener) {
        this.nrOfElements= nrOfElements;
        this.nrOfMessages = nrOfMessages;
        this.listener = listener;
        workerRouter = getContext().actorOf(new RoundRobinPool(nrOfWorkers).props(Props.create(Worker.class)), 
        	    "router2");
        //workerRouter = this.getContext().actorOf(new Props(Worker.class),"workerRouter");
    }

    public void onReceive(Object message) {
    	if (message instanceof String) {
    	    /*for (int start = 0; start < nrOfMessages; start++) {
    	    	workerRouter.tell(new Work(start, nrOfElements), getSelf());
    	    }*/
    		for (int start = nrOfMessages; start < nrOfElements;) {
                    int end=start+ 1000000;
                    count += 1;
                    if(end>nrOfElements) {
                    	end=nrOfElements;
                    	workerRouter.tell(new Work(start, end), getSelf());
                    	break;
                    	}
                    else{
                    	workerRouter.tell(new Work(start, end), getSelf());
                    }
                    start = end+1;
            }

    	  } else if (message instanceof Result) {
    	    Result result = (Result) message;
    	    pi += result.getValue();
    	    nrOfResults += 1;
    	    if (nrOfResults == count) {
    	      // Send the result to the listener
    	    	
    	      Duration duration = Duration.create(System.currentTimeMillis() - starttime, TimeUnit.MILLISECONDS);
    	      listener.tell(new PiApproximation(pi, duration), getSelf());
    	      // Stops this actor and all its supervised children
    	      System.out.println(count);
    	      getContext().stop(getSelf());
    	    }
    	  } else {
    	    unhandled(message);
    	  }
    }
}