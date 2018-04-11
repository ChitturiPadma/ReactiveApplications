package com.fractal.akka

/**
  * Created by padmach on 11/4/18.
  */
import akka.actor._
import akka._

case object Started

class Actor1 extends Actor{

  // This is invoked just before starting the actor
  override def preStart(): Unit = {
    println("In Pre-start method")
    println("Actor is about start")
  }

  /* This is invoked just before the actor is terminated. It releases the resources created in the preStart method.

  */
  override def postStop(): Unit = {
    println("In Post Stop method")
    println("Just before the actor is terminated")
  }

  def receive = {

    case Started => println("Received...Started message")

    case _ => println("Default message")
  }
}

object Actor_Lifecycle {

  def main(args:Array[String]): Unit =
  {

    val system  = ActorSystem("ActorLifeCycle")
    val actor = system.actorOf(Props[Actor1], "Actor1")

    actor ! Started
    actor ! PoisonPill
  }

}
