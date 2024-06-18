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

WebUI.waitForElementPresent(findTestObject('Task2/Payment/22MasterCard'), 30)

WebUI.click(findTestObject('Task2/Payment/22MasterCard'))

WebUI.setText(findTestObject('Task2/Payment/23CardNumber'), GlobalVariable.MastercardNumber)

WebUI.setText(findTestObject('Task2/Payment/24CardHolderName'), GlobalVariable.CardName)

WebUI.setText(findTestObject('Task2/Payment/25EXMonth'), GlobalVariable.EXMonth)

WebUI.setText(findTestObject('Task2/Payment/25EXYear'), GlobalVariable.MasterCardEXYear)

WebUI.setText(findTestObject('Task2/Payment/26CVV'), GlobalVariable.CVV)

WebUI.waitForElementClickable(findTestObject('Task2/Payment/27buttonCONFIRM'), 30)

WebUI.click(findTestObject('Task2/Payment/27buttonCONFIRM'))

WebUI.waitForElementPresent(findTestObject('Task2/Payment/27Tickets successfully purchased'), 30)

String bookingId = WebUI.getText(findTestObject('Task2/Payment/28BookingID'))

println('Booking ID: ' + bookingId)

WebUI.click(findTestObject('Task2/Payment/29button_BACK TO HOME'))

WebUI.closeBrowser()

