package com.ebay;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor{
	public static enum Msg {
	       GREET, DONE
	   }
	@Override
	public void onReceive(Object message) throws Exception {
		if(message == Msg.GREET){
			System.out.println("hello world.");
			getSender().tell(Msg.DONE, getSelf());
		}
		else{
			unhandled(message);
		}
		
	}

}
