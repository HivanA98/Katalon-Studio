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

WebUI.waitForElementPresent(findTestObject('Task2/Contact/12FullName'), 30)

WebUI.setText(findTestObject('Task2/Contact/12FullName'), GlobalVariable.FullName)

WebUI.setText(findTestObject('Task2/Contact/13Email'), GlobalVariable.Email)

WebUI.setText(findTestObject('Task2/Contact/16SingaporeNumber'), GlobalVariable.PhoneNumber)

WebUI.setText(findTestObject('Task2/Contact/17Addreas'), 'Jendral Sudirman')

WebUI.setText(findTestObject('Task2/Contact/18Country'), 'singapore')

WebUI.waitForElementPresent(findTestObject('Task2/Contact/19SingaporeCountry'), 30)

WebUI.click(findTestObject('Task2/Contact/19SingaporeCountry'))

WebUI.setText(findTestObject('Task2/Contact/20PostalCode'), '801111')

WebUI.waitForElementPresent(findTestObject('Task2/Contact/21ButtonCONFIRM'), 30)

WebUI.click(findTestObject('Task2/Contact/21ButtonCONFIRM'))

