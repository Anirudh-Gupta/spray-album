package com.amitrai48.models

import java.sql.Timestamp

import slick.driver.H2Driver.api._

abstract class BaseTable[T](tag: Tag,name: String) extends Table[T](tag,name){
  def id = column[Long]("id",O.PrimaryKey,O.AutoInc)
  def created_at = column[Timestamp]("created_at")
  def updated_at = column[Timestamp]("updated_at")
}