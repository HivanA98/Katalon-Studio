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

WebUI.waitForElementPresent(findTestObject('11-20/17_Full_Name'), 90)

WebUI.click(findTestObject('11-20/17_Full_Name'))

WebUI.setText(findTestObject('11-20/17_Full_Name'), Nama)

WebUI.click(findTestObject('11-20/18_Mobile_Number'))

WebUI.setText(findTestObject('11-20/18_Mobile_Number'), Nomor)

WebUI.click(findTestObject('11-20/19_Email'))

WebUI.setText(findTestObject('11-20/19_Email'), 'hivanarmadi@gmail.com')

WebUI.click(findTestObject('11-20/20_Save_Contact'))

WebUI.click(findTestObject('21-33/21_select_Mr.Mrs.Ms'))

WebUI.click(findTestObject('21-33/22_Mr'))

WebUI.click(findTestObject('21-33/23_Full_Name_Driver'))

WebUI.setText(findTestObject('21-33/23_Full_Name_Driver'), Nama)

WebUI.click(findTestObject('21-33/24_Mobile_Number_Driver'))

WebUI.setText(findTestObject('21-33/24_Mobile_Number_Driver'), Nomor)

WebUI.click(findTestObject('21-33/25_Save_Contact_Driver'))

WebUI.click(findTestObject('21-33/26_Confirm_Booking'))

