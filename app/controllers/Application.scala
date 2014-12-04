package controllers


import com.codahale.jerkson.Json
import play.api.mvc._
/*import spray.json._
import DefaultJsonProtocol._*/


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
    val bars = inTransaction {
      val bars = from(AppDB.barTable)(barTable =>
        select(barTable)
      )

      Json.generate(bars)
    }
    Ok(views.html.bars(bars))
  }

}