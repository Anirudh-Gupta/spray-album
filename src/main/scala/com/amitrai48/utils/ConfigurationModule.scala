package com.amitrai48.utils

import com.typesafe.config.{Config, ConfigFactory}

/**
  * Created by amit on 2/9/16.
  */

trait Configuration{
  def config: Config
}

trait ConfigurationModuleImpl extends Configuration{
  override def config: Config = ConfigFactory.load(this.getClass().getClassLoader(), "application.conf")
}
