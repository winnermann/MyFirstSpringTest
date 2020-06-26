package ru.springstudy.testcases;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class MainTest {
//    private CurrencyRate steps = new CurrencyRate(); так бы мы создали объект без Spring(га)

    //этот класс ClassPathXmlApplicationContext считывает инфу из файла applicationContext.xml и помещает все бины в context
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    //достаем Bean из applicationContext.xml по его id="steps", вызываем через метод getBean()
    //используем два аргумента id="steps" и объкт какого класса хотим получить CurrencyRate.class
    //Объект класса context.getBean("steps", CurrencyRate.class); помещаем в переменную steps
    CurrencyRate steps = context.getBean("steps", CurrencyRate.class);

    @Test
    @Description(value = "Cценарий: Сравнение курсов валют")
    @Epic("Функциональные тесты")
    @Severity(value = SeverityLevel.NORMAL)
    public void openWebsite (){
        // step 1
        steps.openWebsite();
        // step 2
        steps.searchInGoogle();
        // step 3
        steps.openMainMenu();
        // step 4
        steps.openCurrencyExMenu();
        // step 5
        steps.compareDollarRates();
        // step 6
        steps.compareEuroRates();
        //закрыть context
        context.close();
    }

}
