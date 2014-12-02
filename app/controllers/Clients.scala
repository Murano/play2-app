package controllers

import controllers.Application._
import play.api._
import play.api.mvc._
import models._

object Clients extends Controller{

  def show(id: Int) = Action {

    Ok("Hello")
  }

}
