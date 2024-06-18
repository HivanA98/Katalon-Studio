import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Mobile.startApplication('MobileApp\\Happy Day Shopping v1.1.apk', true)

Mobile.tap(findTestObject('Object Repository/Shop/android.view.ViewGroup'), 0)

Mobile.tap(findTestObject('Object Repository/Shop/android.view.ViewGroup (1)'), 0)

Mobile.setText(findTestObject('Object Repository/Shop/android.widget.EditText - 1'), '5', 0)

Mobile.tap(findTestObject('Object Repository/Shop/android.widget.TextView - Add to Bag'), 0)

Mobile.tap(findTestObject('Object Repository/Shop/android.widget.ImageView'), 0)

Mobile.tap(findTestObject('Object Repository/Shop/android.view.ViewGroup (2)'), 0)

Mobile.tap(findTestObject('Object Repository/Shop/android.widget.Image - Block illustration'), 0)

Mobile.tap(findTestObject('Object Repository/Shop/android.widget.Image - Ponds Age Miracle Youth Boosting Whip 50g'), 0)

Mobile.tap(findTestObject('android.widget.Button - Go to Checkout'), 0)

Mobile.getText(findTestObject('Object Repository/Shop/android.view.View - If you have any inquiries or updates pls whatsap us at 94617563'), 
    0)

Mobile.closeApplication()

