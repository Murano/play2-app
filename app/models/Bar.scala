package models

import org.squeryl.{Schema, KeyedEntity}

case class Bar(name: Option[String]) extends KeyedEntity[Long] {
  val id: Long = 0
//  def json(implicit gson: Gson): JsValue = play.api.libs.json.Json.parse(gson.toJson(this))
}

object AppDB extends Schema {
  val barTable = table[Bar]("bar")
}
