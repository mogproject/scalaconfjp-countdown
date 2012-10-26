import scalaj.http.HttpOptions._
import scalaj.http.{Token, Http}
import java.util.Calendar

object ScalaConfJPCountDown extends App {
  def tweet(msg: String) {
    if (msg.isEmpty) return
    val consumer = Token("BK5XIfKA8pmO9AaQdOkcQ", "3bzIaiXHjFFrwz9n3r5dqUiCCMabievCsMnS2TPFPw")
    val accessToken = Token("903128250-uzeKqQcrxbL1P9XaPEZOeYgN8j9JHx3zSif0SYg6", "ABiXSwDnj6e5mMjlj8T0lqr0oZ8NZUxHfx58SRb48eE")
    Http.post("https://api.twitter.com/1/statuses/update.xml")
      .options(connTimeout(1000), readTimeout(5000))
      .params("status" -> msg)
      .oauth(consumer, accessToken)
      .asXml
  }

  def createMessage(dateDiff: Long): String = {
    val event = "Scala Conference in Japan"
    val body = dateDiff match {
      case -2 => "本サービスは本日をもって終了いたします。ご利用ありがとうございました。"
      case -1 => event + " 本日はアンカンファレンス開催日です!"
      case 0 => "お待たせしました!! " + event + " 本日開催です!"
      case 1 => event + " いよいよ明日開催です!"
      case days if days > 0 => event + " 開催まであと " + days + "日です!"
      case _ => return ""
    }
    val tail = " http://www.scalaconf.jp"
    body + tail
  }

  val now = Calendar.getInstance
  val cal = now.clone.asInstanceOf[Calendar]
  cal.set(2013, 2, 2)
  val diff = (cal.getTime.getTime - now.getTime.getTime) / 24 / 60 / 60 / 1000
//  tweet(createMessage(diff))
  println(createMessage(diff))
}
