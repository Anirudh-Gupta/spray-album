package com.amitrai48.models

import java.sql.Timestamp

import slick.driver.H2Driver.api._

import scala.concurrent.Future

/**
  * Created by amit on 1/9/16.
  */

case class Album(title: String,artist: String,year: Int,created_at: Timestamp = new Timestamp(System.currentTimeMillis()),updated_at:Timestamp = new Timestamp(System.currentTimeMillis()),id: Long = 0L)

case class AlbumDTO(title: String,artist: String, year: Int)

class AlbumTable(tag: Tag) extends BaseTable[Album](tag,"albums"){
  def title = column[String]("title")
  def artist = column[String]("artist")
  def year = column[Int]("year")

  def * = (title,artist,year,created_at,updated_at,id)<>(Album.tupled,Album.unapply)
}

class AlbumRepo{
  val db = Database.forConfig("h2db.db")

  protected lazy val AlbumTable = TableQuery[AlbumTable]

  def createTable = db.run{
    AlbumTable.schema.create
  }

  def insert(album: Album): Future[Long] = db.run{
    AlbumTable returning AlbumTable.map(_.id) += album
  }

  def getAll(): Future[Seq[Album]] = db.run{
    AlbumTable.result
  }

  def getAlbum(id: Long): Future[Option[Album]] = db.run{
    AlbumTable.filter(_.id === id).result.headOption
  }
}
