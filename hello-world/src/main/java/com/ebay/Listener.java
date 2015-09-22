package com.ebay;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class Listener extends UntypedActor{
	
	public void preStart(){
		ActorSystem system = ActorSystem.create("PiSystem");
		final ActorRef master = getContext().actorOf(
	    	    Props.create(Master.class, 20, 2, 2000000000, getSelf()),
	    	      "master");
	 
	    // start the calculation
	    master.tell("Starting", getSelf());
	}
	public void calculate(final int nrOfWorkers, final int nrOfElements, final int nrOfMessages) {
	    // Create an Akka system
	    ActorSystem system = ActorSystem.create("PiSystem");
	 
	    // create the result listener, which will print the result and shutdown the system
	    //final ActorRef listener = system.actorOf(new Props(null, Listener.class, null), "listener");
	 
	    // create the master
	    final ActorRef master = getContext().actorOf(
	    	    Props.create(Master.class, nrOfWorkers, nrOfMessages, nrOfElements, getSelf()),
	    	      "master");
	 
	    // start the calculation
	    master.tell("Starting", getSelf());
	 
	  }
	public void onReceive(Object message) {
	    if (message instanceof PiApproximation) {
	      PiApproximation approximation = (PiApproximation) message;
	      System.out.println(String.format("\n\tPi approximation: \t\t%s\n\tCalculation time: \t%s",
	          approximation.getPi(), approximation.getDuration()));
	      //getContext().system().shutdown();
	    } else {
	      unhandled(message);
	    }
	  }
}
