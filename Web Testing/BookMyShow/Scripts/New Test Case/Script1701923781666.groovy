import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
// Buka browser dan navigasi ke Saucedemo.com
// Masukkan kredensial dan klik tombol login
// Pilih produk dari halaman utama
// Lanjutkan ke keranjang belanja
// Lakukan checkout
// Masukkan informasi pengiriman
// Konfirmasi pesanan
// Verifikasi pesan berhasil
// Tutup browser
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
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('https://www.saucedemo.com/')

WebUI.setText(findTestObject('New Test Object'), 'standard_user')

WebUI.setText(findTestObject('//input[@placeholder="Password"]'), 'secret_sauce')

WebUI.click(findTestObject('//input[@value="LOGIN"]'))

WebUI.click(findTestObject('//div[@class="inventory_item"][1]//button[@class="btn_inventory"]'))

WebUI.click(findTestObject('//div[@class="inventory_item"][2]//button[@class="btn_inventory"]'))

WebUI.click(findTestObject('//a[@class="shopping_cart_link"]'))

WebUI.click(findTestObject('//button[@id="checkout"]'))

WebUI.setText(findTestObject('//input[@id="first-name"]'), 'John')

WebUI.setText(findTestObject('//input[@id="last-name"]'), 'Doe')

WebUI.setText(findTestObject('//input[@id="postal-code"]'), '12345')

WebUI.click(findTestObject('//input[@id="continue"]'))

WebUI.click(findTestObject('//input[@id="finish"]'))

WebUI.verifyElementVisible(findTestObject('//h2[text()="THANK YOU FOR YOUR ORDER"]'))

WebUI.closeBrowser()

