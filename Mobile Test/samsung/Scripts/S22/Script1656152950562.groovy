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

Mobile.startApplication('MobileApp\\Shop Samsung.apk', true)

Mobile.scrollToText('GET STARTED')

Mobile.tap(findTestObject('Object Repository/SAMSUNG S22 ULTRA/android.widget.TextView - GET STARTED'), 0)

Mobile.tap(findTestObject('Object Repository/SAMSUNG S22 ULTRA/android.widget.ImageView'), 0)

Mobile.tap(findTestObject('Object Repository/SAMSUNG S22 ULTRA/android.widget.TextView - SHOP'), 0)

Mobile.tap(findTestObject('Object Repository/SAMSUNG S22 ULTRA/android.widget.ImageView (1)'), 0)

Mobile.tap(findTestObject('Object Repository/SAMSUNG S22 ULTRA/android.widget.ImageView (2)'), 0)

Mobile.tap(findTestObject('Object Repository/SAMSUNG S22 ULTRA/android.widget.RelativeLayout'), 0)

Mobile.tap(findTestObject('Object Repository/SAMSUNG S22 ULTRA/android.widget.TextView - Galaxy S22 Ultra'), 0)

Mobile.tap(findTestObject(''), 0)

Mobile.tap(findTestObject('Object Repository/SAMSUNG S22 ULTRA/android.widget.TextView - BUY NOW'), 0)

Mobile.tap(findTestObject('Object Repository/SAMSUNG S22 ULTRA/android.view.View - Galaxy S22  S22'), 0)

Mobile.closeApplication()

