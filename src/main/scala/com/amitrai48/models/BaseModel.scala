package com.amitrai48.models

import java.sql.Timestamp

/**
  * Created by amit on 1/9/16.
  */

trait BaseModel{
  val id : Long
  val created_at : Timestamp
  val updated_at : Timestamp
}
