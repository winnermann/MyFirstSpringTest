package ru.springstudy.testcases;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.Configuration;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Condition.text;


public class CurrencyRate {

    @Test(description = "Cценарий: Сравнение курсов валют", priority = 1, timeOut = 60000 * 10)
    @Step("Step1: Открыть браузер Chrome")
    public void openWebsite(){
        //Открыть браузер
        Configuration.browser = "chrome";
        open("https://www.google.com");
    }

    @Test(description = "Cценарий: Сравнение курсов валют", priority = 2, timeOut = 60000 * 10)
    @Step("Step2: Осуществить поиск по слову Открытие и перейти на сайт")
    public void searchInGoogle() {

        //Осуществить поиск по слову "Открытие"
        $(By.name("q")).setValue("Открытие").pressEnter();
        //кликнуть по результату поиска
        $("#rso div.r").shouldHave(text("Открытие")).click();
    }

    @Test(description = "Cценарий: Сравнение курсов валют", priority = 3, timeOut = 60000 * 10)
    @Step("Step3: Открыть главное меню")
    public void openMainMenu() {
        //открыть главное меню
        $("#main div.main-page-header__main-nav-hamburger.main-page-header__main-nav-item.mobile-md-display-none > button").click();
    }

    @Test(description = "Cценарий: Сравнение курсов валют", priority = 4, timeOut = 60000 * 10)
    @Step("Step4: Кликнуть на меню Обмен валюты")
    public void openCurrencyExMenu() {

        //Кликнуть на меню "Обмен валюты"
        $(By.xpath("//div[7]//div[1]//span[1]//a[1]//span[1]")).shouldBe(visible).getValue();
        String a1 = $(By.xpath("//div[7]//div[1]//span[1]//a[1]//span[1]")).text();
        System.out.println(a1);
        $(By.xpath("//div[7]//div[1]//span[1]//a[1]//span[1]")).shouldHave(text("Обмен валюты")).click();
    }

    @Test(description = "Cценарий: Сравнение курсов валют", priority = 5, timeOut = 60000 * 10)
    @Step("Step5: Проверить что курс покупки USD дороже курса продажи USD")
    public void compareDollarRates() {

        //Положить значение курса покупки доллара в переменную dollarByStroka
        System.out.println($(By.xpath("//span[contains(text(),' RUB = 1 USD')]")).text());
        String dollarByStroka = $(By.xpath("//span[contains(text(),' RUB = 1 USD')]")).waitUntil(visible, 10000).getText();
        System.out.println(dollarByStroka + " dollarByStroka");

        //отсечение лишних символов при покупке доллара
        String str = dollarByStroka;
        String newStr = str.replace(" RUB = 1 USD", "");
        System.out.println(newStr + " newStr");

        String str2 = newStr;
        String newStr2 = str2.replace(",", ".");
        System.out.println(newStr2 + " newStr2");

        //первод строки в число double
        Double d1 = Double.valueOf(newStr2);
        System.out.println(d1 + " d1");

        //Нажать на кнопку смены купли-продажи
        $(By.xpath("//div[@class='exchange-section__swap-icon']//img")).click();

        //Положить значение курса продажи доллара в переенную dollarSellStroka
        System.out.println($(By.xpath("//span[contains(text(),'1 USD = ')]")).text());
        String dollarSellStroka = $(By.xpath("//span[contains(text(),'1 USD =')]")).waitUntil(visible, 10000).getText();
        System.out.println(dollarSellStroka + " dollarSellStroka");

        //отсечение лишних символов при продаже доллара
        String str3 = dollarSellStroka;
        String newStr3 = str3.replace(" RUB", "");
        System.out.println(newStr3 + " newStr3");

        String str4 = newStr3;
        String newStr4 = str4.replace("1 USD = ", "");
        System.out.println(newStr4 + " newStr4");

        String str5 = newStr4;
        String newStr5 = str5.replace(",", ".");
        System.out.println(newStr5 + " newStr5");

        //первод строки в число double
        Double d2 = Double.valueOf(newStr5);
        System.out.println(d2 + " d2");

        //Проверить что курс покупки USD дороже курса продажи USD
        Assert.assertTrue(d1 > d2, "Курс покупки меньше курса продажи - у банка большие проблемы!!!");
    }

    @Test(description = "Cценарий: Сравнение курсов валют", priority = 6, timeOut = 60000 * 10)
    @Step("Step6: Проверить что курс покупки EUR дороже курса продажи EUR")
    public void compareEuroRates() {

        //scroll
        Selenide.executeJavaScript("window.scrollBy(0,300)");

        //выбрать Евро из выпадающего списка
        $(By.xpath("//div[@id='currency-select-online-purchase']")).click();
        $(By.xpath("//span[contains(text(),'EUR')]")).click();

        //Положить значение курса покупки Евро в переменную euroByStroka
        System.out.println($(By.xpath("//span[contains(text(),' RUB = 1 EUR')]")).text());
        $(By.xpath("//span[contains(text(),' RUB = 1 EUR')]")).text();
        String euroByStroka = $(By.xpath("//span[contains(text(),' RUB = 1 EUR')]")).text();
        System.out.println(euroByStroka + " euroByStroka");

        //отсечение лишних символов при покупке Евро
        String str6 = euroByStroka;
        String newStr6 = str6.replace(" RUB = 1 EUR", "");
        System.out.println(newStr6 + " newStr6");

        String str7 = newStr6;
        String newStr7 = str7.replace(",", ".");
        System.out.println(newStr7 + " newStr7");

        //первод строки в число double
        Double d3 = Double.valueOf(newStr7);
        System.out.println(d3 + " d3");

        //Нажать на кнопку смены купли-продажи
        $(By.xpath("//div[@class='exchange-section__swap-icon']//img")).click();

        //Положить значение курса продажи Евро в переенную euroSellStroka
        System.out.println($(By.xpath("//span[contains(text(),'1 EUR = ')]")).text());
        $(By.xpath("//span[contains(text(),'1 EUR = ')]")).text();
        String euroSellStroka = $(By.xpath("//span[contains(text(),'1 EUR = ')]")).text();
        System.out.println(euroSellStroka + " euroSellStroka");

        //отсечение лишних символов при продаже Евро
        String str8 = euroSellStroka;
        String newStr8 = str8.replace(" RUB", "");
        System.out.println(newStr8 + " newStr8");

        String str9 = newStr8;
        String newStr9 = str9.replace("1 EUR = ", "");
        System.out.println(newStr9 + " newStr9");

        String str10 = newStr9;
        String newStr10 = str10.replace(",", ".");
        System.out.println(newStr10 + " newStr10");

        //первод строки в число double
        Double d4 = Double.valueOf(newStr10);
        System.out.println(d4 + " d4");

        //Проверить что курс покупки EUR дороже курса продажи EUR
        Assert.assertTrue(d3 > d4, "Курс покупки меньше курса продажи - у банка большие проблемы!!!");

    }
}
