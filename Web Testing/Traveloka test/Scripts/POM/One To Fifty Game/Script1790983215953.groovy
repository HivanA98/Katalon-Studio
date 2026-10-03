import com.qa.core.Check
import com.zzzscore.pages.OneToFiftyPage

// Test case variable: maxSeconds
OneToFiftyPage game = OneToFiftyPage.open().clickNumbersInOrder(50)

Check.isTrue(game.finished(), 'Game finishes and the result page is shown')
BigDecimal seconds = game.score()
Check.isTrue(seconds > 0, "Score is recorded (${seconds}s)")
Check.isTrue(seconds <= (maxSeconds as BigDecimal), "Completed within ${maxSeconds}s (took ${seconds}s)")
