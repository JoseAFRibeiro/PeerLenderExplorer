package com.jose.html;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TableHandeler {
    
    WebDriver driver;
    String path;
    List<WebElement> tableList;
    List<Row> rowList;

    public TableHandeler(WebDriver d, String tablePath)
    {
        driver = d;
        path = tablePath;
        tableList = new ArrayList<>();
        rowList = new ArrayList<>();
    }

    public List<Row> getTableList()
    {

        tableList = driver.findElements(By.tagName("tr"));

        int b;
        int size = tableList.size();
        for(b = 0; b <= size; b += 2)
        {   
            rowList.add(new Row(b, tableList.get(b), tableList.get(b+1)));
            b++;
        }

        return rowList;
        
    }
}

class Row{

    final int rowNum;
    WebElement row[] = new WebElement[2];

    Row(int num, WebElement c1, WebElement c2)
    {
        rowNum = num;
        row[0] = c1;
        row[1] = c2;
    }
}
