package trade_statistics

import org.scalatest.{Matchers, WordSpec}
import com.softwaremill.sttp._

class ApplicationTest extends WordSpec with Matchers {
  "Service" should {
    "response on target port" in {
      implicit val backend: SttpBackend[Id, Nothing] = HttpURLConnectionBackend()
      Application.start()
      sttp.get(uri"http://localhost:8081/status").send().code shouldBe 200
    }
  }
}
