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

WebUI.waitForElementClickable(findTestObject('Task1/Addres/14Confirm_Details'), 30)

WebUI.click(findTestObject('Task1/Addres/14Confirm_Details'))

WebUI.click(findTestObject('Task1/Paid/15Payment_Visa'))

WebUI.setText(findTestObject('Task1/Paid/16Cardholder_Name'), GlobalVariable.CardName)

WebUI.setText(findTestObject('Task1/Paid/17Card_Number'), GlobalVariable.VisaCardNumber)

WebUI.setText(findTestObject('Task1/Paid/18Ex_Month'), GlobalVariable.EXMonth)

WebUI.setText(findTestObject('Task1/Paid/19Ex_Year'), GlobalVariable.VisaEXYear)

WebUI.setText(findTestObject('Task1/Paid/20CVV'), GlobalVariable.CVV)

WebUI.waitForElementPresent(findTestObject('Task1/Paid/21Pay_Button'), 30)

WebUI.click(findTestObject('Task1/Paid/21Pay_Button'))

WebUI.click(findTestObject('Task1/Paid/22_Back_To_Home'))

WebUI.closeBrowser()

