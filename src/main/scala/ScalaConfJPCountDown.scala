import java.util.Calendar
import twitter4j.{Twitter, TwitterFactory}
import twitter4j.auth.AccessToken

object ScalaConfJPCountDown extends App {
  def tweet(msg: String) {
    if (msg.isEmpty) return
    val twitter: Twitter = new TwitterFactory().getInstance()
    twitter.setOAuthConsumer(System.getenv("CONSUMER_KEY"),
      System.getenv("CONSUMER_SECRET"))
    twitter.setOAuthAccessToken(new AccessToken(System.getenv("ACCESS_TOKEN"),
      System.getenv("ACCESS_TOKEN_SECRET")))
    twitter.updateStatus(msg)
  }

  def createMessage(dateDiff: Long): String = {
    val event = "Scala Conference in Japan"
    val foot = " http://www.scalaconf.jp"
    val body = dateDiff match {
      case -2 => "本サービスは本日をもって終了いたします。ご利用ありがとうございました。"
      case -1 => event + " 本日はアンカンファレンス開催日です!"
      case 0 => "お待たせしました!! " + event + " 本日開催です!"
      case 1 => event + " いよいよ明日開催です!"
      case days if days > 0 => event + " 開催まであと " + days + "日です!"
      case _ => return ""
    }
    body + foot
  }

  val now = Calendar.getInstance
  val cal = now.clone.asInstanceOf[Calendar]
  cal.clear
  cal.set(2013, 2, 2, 0, 0, 0)  // 2013-03-02 00:00:00
  val diff = (cal.getTime.getTime - now.getTime.getTime) / 24 / 60 / 60 / 1000
  tweet(createMessage(diff))
}
