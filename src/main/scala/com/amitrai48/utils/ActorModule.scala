package com.amitrai48.utils

import akka.actor.ActorSystem

/**
  * Created by amit on 2/9/16.
  */

trait ActorModule{
  val system: ActorSystem
}

trait ActorModuleImpl extends ActorModule {
  this: Configuration =>
  val system = ActorSystem("AlbumActor",config)
}
