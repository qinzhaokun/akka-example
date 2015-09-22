package com.ebay;

import akka.actor.UntypedActor;

public class Worker extends UntypedActor {
	public void preStart(){
		  //System.out.println("startting working...");
	  }
	private double calculatePiFor(int start, int nrOfElements) {
		  double acc = 0.0;
		  /*for (int i = start * nrOfElements; i <= ((start + 1) * nrOfElements - 1); i++) {
		    acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1);
		  }*/
		  for(int i=start;i<=nrOfElements;i++){
              acc =acc +i;
          }
		  return acc;
		}


    public void onReceive(Object message) {
        if (message instanceof Work) {
            Work work = (Work) message;
            double result = calculatePiFor(work.getStart(), work.getNrOfElement());
            getSender().tell(new Result(result), getSelf());
        } else {
            unhandled(message);
        }
    }
}
