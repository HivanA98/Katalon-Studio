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

WebUI.openBrowser('')

WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')

WebUI.verifyElementText(findTestObject('Object Repository/Hero Ku App/h1_CURA Healthcare Service'), 'CURA Healthcare Service')

WebUI.click(findTestObject('Object Repository/Hero Ku App/a_Make Appointment'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Hero Ku App/h2_Login'), 0)

WebUI.setText(findTestObject('Object Repository/Hero Ku App/input_Username_username'), Username)

WebUI.setEncryptedText(findTestObject('Object Repository/Hero Ku App/input_Password_password'), Password)

WebUI.verifyElementClickable(findTestObject('Object Repository/Hero Ku App/button_Login'))

WebUI.click(findTestObject('Object Repository/Hero Ku App/button_Login'))

WebUI.selectOptionByValue(findTestObject('Object Repository/Hero Ku App/select_Tokyo CURA Healthcare Center        _5b4107'), 
    'Tokyo CURA Healthcare Center', true)

WebUI.click(findTestObject('Hero Ku App/input_Apply for hospital readmission_hospital_readmission'))

WebUI.click(findTestObject('Object Repository/Hero Ku App/input_Medicare_programs'))

WebUI.setText(findTestObject('Object Repository/Hero Ku App/input_Visit Date (Required)_visit_date'), '22/10/2022')

WebUI.setText(findTestObject('Object Repository/Hero Ku App/textarea_Comment_comment'), Commentar)

WebUI.verifyElementClickable(findTestObject('Object Repository/Hero Ku App/button_Book Appointment'))

WebUI.click(findTestObject('Object Repository/Hero Ku App/button_Book Appointment'))

WebUI.verifyElementText(findTestObject('Object Repository/Hero Ku App/h2_Appointment Confirmation'), 'Appointment Confirmation')

WebUI.takeFullPageScreenshotAsCheckpoint('C:\\\\Screenshot.jpg')

WebUI.closeBrowser()

