/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labhibernate;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author dell
 */
public class CSVReader {

    public BufferedReader Br;
    public CSVReader()
    {
        String csvFile = "C:\\Users\\dell\\Documents\\NetBeansProjects\\LabHibernate\\src\\GeoLiteCity-Location.csv";
        String line = "";
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            br.readLine();
            this.Br = br;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String csvFile = "C:\\Users\\dell\\Documents\\NetBeansProjects\\LabHibernate\\src\\GeoLiteCity-Location.csv";
        String line = "";
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] location = line.split(csvSplitBy);
                System.out.println(location.length);
                GeoLocation l1 = new GeoLocation(location[0], location[1],location[3],location[5], location[6]);
                l1.print();


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}