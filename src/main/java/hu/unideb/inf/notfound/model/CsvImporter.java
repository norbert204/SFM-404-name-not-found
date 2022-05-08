package hu.unideb.inf.notfound.model;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvImporter {

    public static List<Product> CsvImporter(String csvlocation) {
        List<Product> termekek = new ArrayList<>();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(csvlocation));
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {

                System.out.println(line);
                String[] sordarab = line.split(";");

                Product product = new Product();

                product.setProductCode(sordarab[0]);
                product.setProductName(sordarab[1]);
                product.setUnit_price(Integer.parseInt(sordarab[2]));
                product.setCategory(sordarab[4]);
                product.setDescription(sordarab[5]);
                product.setQuantity(Integer.parseInt(sordarab[6]));
                product.setLink(sordarab[7]);


                termekek.add(product);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return termekek;
    }
}