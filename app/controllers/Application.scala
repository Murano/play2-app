package controllers


//import com.codahale.jerkson.Json
import play.api.mvc._

import play.api.libs.json.Json

//import DefaultJsonProtocol._
//import com.google.gson.Gson


import play.api.data.Form
import play.api.data.Forms.{mapping, text, optional}

import org.squeryl.PrimitiveTypeMode._
import models.{AppDB, Bar}


object Application extends Controller {

  val barForm = Form(
    mapping(
      "name" -> optional(text)
    )(Bar.apply)(Bar.unapply)
  )

  def index = Action {
    Ok(views.html.index(barForm))
  }

  def addBar = Action { implicit request =>
    barForm.bindFromRequest.value map { bar =>
      inTransaction(AppDB.barTable insert bar)
      Redirect(routes.Application.getBars())
    } getOrElse BadRequest
  }

  def getBars = Action {
    implicit val barWrites = Json.writes[Bar]
    val bars = inTransaction {
      val bars = from(AppDB.barTable)(barTable =>
        select(barTable)
      )

      bars.toList
    }
    Ok(views.html.bars(bars))
  }

  def getBarsJson = Action {
    implicit val barWrites = Json.writes[Bar]
    val bars = inTransaction {
      val bars = from(AppDB.barTable)(barTable =>
        select(barTable)
      )

      Json.toJson(bars)
    }
    Ok(bars)
  }

}