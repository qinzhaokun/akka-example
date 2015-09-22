package com.ebay;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class HelloWorld extends UntypedActor{

	@Override
	   public void preStart() {
	       System.out.println("starting Actor....");
	       final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
	       greeter.tell(Greeter.Msg.GREET, getSelf());
	   }
	
	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof String){
			System.out.println("hello "+message);
		}
		else if (message == Greeter.Msg.DONE){
			System.out.println("stoping....");
			getContext().stop(getSelf());
		}
		else{
			unhandled(message);
		}
	}

}
