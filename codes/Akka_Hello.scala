package com.fractal.akka

/**
  * Created by padmach on 21/3/18.
  */

import akka._
import akka.actor._

case class WhoToGreet(who:String)
case class PrinterMessage(message:String)

class Printer extends Actor{
  def receive ={
    case mesg:String => println("Received Message " + mesg)
  }
}

class Greeter(greetmsg:String, printActor:ActorRef ) extends Actor
{

  def receive = {
  case WhoToGreet(who) => println("Greet message "+who)

  case PrinterMessage(message) => printActor ! message
  }

}

object Akka_Hello {


  def main(args:Array[String]): Unit ={

     val system= ActorSystem("HelloAkka")

    val printer:ActorRef = system.actorOf(Props(new Printer), "printerActor")

    val greeter1:ActorRef = system.actorOf(Props(new Greeter("Hello",printer)), "helloGreet")

    val greeter2:ActorRef = system.actorOf(Props(new Greeter("Hi",printer)), "hiGreet")

    greeter1 ! WhoToGreet("Akka")
    greeter1 ! PrinterMessage("AkkaMessage")


    greeter2 ! WhoToGreet("Play")
    greeter2 ! PrinterMessage("PlayMessage")

  }

}
