package com.ebay;

import java.util.concurrent.TimeUnit;

import scala.concurrent.duration.Duration;

public class Main {
	public static void main(String[] args) {
		long start=System.currentTimeMillis();

        double acc = 0.0;
        for(int i=2;i<=2000000000;i++){
            acc =acc +i;
        }

        Duration duration = Duration.create(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);
        System.out.println("result="+acc+",cal time="+duration);
	    akka.Main.main(new String[] { Listener.class.getName() });
	  }
}
