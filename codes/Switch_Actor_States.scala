package com.fractal.akka

/**
  * Created by padmach on 29/3/18.
  */

import akka.actor._

case object ActNormalMessage
case object TryToFindSolution
case object BadGuysMakeMeAngry

class Human extends Actor {
  import context._

  def normalSate:Receive = {

    case TryToFindSolution =>  println("Looking for Solution to my problem...")
    case BadGuysMakeMeAngry => println("I'm angry!!")
                                become(angryState)

    case _ => println("In Normal Default message")
  }

  def angryState:Receive = {

    case ActNormalMessage => println("I'm moving into normal state...")
                             become(normalSate)

    case _ => println("In Angry..default state")
  }
  def receive = {

    case BadGuysMakeMeAngry => become(angryState)
    case ActNormalMessage => become(normalSate)
  }

}

object Switch_Actor_States {


  def main(args:Array[String]): Unit = {

    val system = ActorSystem("Switching_States")

    val actor = system.actorOf(Props[Human], "PersonActor")

    /*actor ! ActNormalMessage
    actor ! TryToFindSolution
*/
    Thread.sleep(1000)

   /* actor ! BadGuysMakeMeAngry
    actor ! ActNormalMessage*/

    actor ! BadGuysMakeMeAngry
    actor ! ActNormalMessage
    actor ! BadGuysMakeMeAngry




  }

}
