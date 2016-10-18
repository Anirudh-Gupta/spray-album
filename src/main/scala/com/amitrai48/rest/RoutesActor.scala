package com.amitrai48.rest

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorRefFactory}
import com.amitrai48.models.{Album, AlbumDTO, AlbumRepo}
import com.amitrai48.utils.Configuration
import spray.http.StatusCodes
import spray.http.StatusCodes._
import spray.routing.HttpService
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by amit on 2/9/16.
  */
class RoutesActor(modules: Configuration) extends Actor with HttpService{
  import spray.httpx.SprayJsonSupport._
  import com.amitrai48.utils.JsonProtocol._

  override def actorRefFactory: ActorRefFactory = context

  val albumRepo = new AlbumRepo

  //create a schema when starting app
  albumRepo.createTable

  def AlbumListAllRoute = get{
    path("listall"){
      onComplete(albumRepo.getAll()){
        case scala.util.Success(albums) => complete(albums)
        case scala.util.Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
      }
    }
  }

  def AlbumCreateRoute = post{
    path("create"){
      entity(as[AlbumDTO]){albumDTO =>
        val album = Album(albumDTO.title,albumDTO.artist,albumDTO.year)
        onComplete(albumRepo.insert(album)){
          case scala.util.Success(id) => complete(StatusCodes.Created)
          case scala.util.Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
        }
      }
    }
  }
  def AlbumGetByIdRoute = get{
    path(LongNumber){id =>
      onComplete(albumRepo.getAlbum(id)){
        case scala.util.Success(album) => complete(album)
        case scala.util.Failure(ex) => complete(InternalServerError, s"An error occurred: ${ex.getMessage}")
      }
    }
  }
  override def receive: Receive = runRoute(pathPrefix("api" / "album"){AlbumListAllRoute~AlbumCreateRoute~AlbumGetByIdRoute}~
    get{
      path(""){
        complete{
          "Hii Your server is up and running"
        }
      }
    }
  )
}
