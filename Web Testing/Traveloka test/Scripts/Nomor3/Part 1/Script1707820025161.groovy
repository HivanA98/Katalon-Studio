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

WebUI.openBrowser('https://www.traveloka.com/en-id')

WebUI.waitForElementPresent(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/01_Car_Rental'), 
    10)

WebUI.click(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/01_Car_Rental'))

WebUI.waitForElementVisible(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/02_Without_Driver'), 
    10)

WebUI.click(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/03_Lokasi_Peminjaman'))

WebUI.setText(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/03_Lokasi_Peminjaman'), 'Ragunan')

WebUI.waitForElementPresent(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/Terminal_Ragunan'), 
    10)

WebUI.click(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/Terminal_Ragunan'))

WebUI.click(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/04_Tanggal_Awal_Peminjaman'))

WebUI.click(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/Tanggal_15'))

WebUI.click(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/05_Jam_awal_Peminjaman'))

WebUI.waitForElementPresent(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/Jam_9'), 10)

WebUI.click(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/Jam_9'))

WebUI.click(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/Tombol_Done'))

WebUI.click(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/06_Tanggal_Akhir_Peminjaman'))

WebUI.click(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/Tanggal_20'))

WebUI.click(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/07_Jam_akhir_Peminjaman'))

WebUI.click(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/Jam_11'))

WebUI.click(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/Tombol_Done'))

WebUI.click(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/08_Tombol_Search'))

