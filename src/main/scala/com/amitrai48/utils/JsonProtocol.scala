package com.amitrai48.utils

import java.sql.Timestamp

import com.amitrai48.models.{Album, AlbumDTO}
import spray.json.{DefaultJsonProtocol, DeserializationException, JsNumber, JsValue, JsonFormat}


/**
  * Created by amit on 2/9/16.
  */


object JsonProtocol extends DefaultJsonProtocol{
  implicit object TimestampJsonFormat extends JsonFormat[Timestamp] {
    def write(x: Timestamp) = JsNumber(x.getTime)
    def read(value: JsValue) = value match {
      case JsNumber(x) => new Timestamp(x.toLong)
      case x => throw new DeserializationException("Date expected")
    }
  }

  implicit val albumFormat = jsonFormat6(Album)

  implicit val albumDTOFormat = jsonFormat3(AlbumDTO)
}
