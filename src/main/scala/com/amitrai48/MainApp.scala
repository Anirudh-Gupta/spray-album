package com.amitrai48

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import com.amitrai48.rest.RoutesActor
import com.amitrai48.utils.{ActorModuleImpl, ConfigurationModuleImpl}
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

import scala.concurrent.ExecutionContext

/**
  * Created by amit on 2/9/16.
  */
object MainApp extends App{
  val logger = LoggerFactory.getLogger(this.getClass)

  val mode = System.getProperty("MODE", "dev")

  logger.info(s"Starting App server in ${mode}")

  val module = new ConfigurationModuleImpl with ActorModuleImpl

  implicit def ec: ExecutionContext = ExecutionContext.global

  val service = module.system.actorOf(Props(classOf[RoutesActor], module), "routesActor")

  implicit val system = module.system

  implicit val timeout = Timeout(5.seconds)

  IO(Http) ? Http.Bind(service, interface = "localhost", port = 8085)

}
