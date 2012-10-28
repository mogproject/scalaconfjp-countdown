import scalaj.http.{Token, Http}
import scalaj.http.HttpOptions._
import java.util.Calendar

object ScalaConfJPCountDown extends App {
  def tweet(msg: String) {
    if (msg.isEmpty) return
    val consumer = Token(System.getenv("CONSUMER_KEY"), System.getenv("CONSUMER_SECRET"))
    val accessToken = Token(System.getenv("ACCESS_TOKEN"), System.getenv("ACCESS_SECRET"))
    Http.post("https://api.twitter.com/1/statuses/update.xml")
      .options(connTimeout(1000), readTimeout(5000))
      .params("status" -> msg)
      .oauth(consumer, accessToken)
      .asXml
  }

  def createMessage(dateDiff: Long): String = {
    val event = "Scala Conference in Japan"
    val foot = " http://www.scalaconf.jp"
    val body = dateDiff match {
      case -2 => "本サービスは本日をもって終了いたします。ご利用ありがとうございました。"
      case -1 => event + " 本日はアンカンファレンス開催日です!"
      case 0 => "お待たせしました!! " + event + " 本日開催です!"
      case 1 => event + " いよいよ明日開催です!"
//      case days if days > 0 => event + " 開催まであと " + days + "日です!"
      case days if days > 0 => event + " Coming in " + days + "days!"
      case _ => return ""
    }
    body + foot
  }

  val now = Calendar.getInstance
  val cal = now.clone.asInstanceOf[Calendar]
  cal.set(2013, 2, 2)  // 2013-03-02
  val diff = (cal.getTime.getTime - now.getTime.getTime) / 24 / 60 / 60 / 1000
  tweet(createMessage(diff))
}
