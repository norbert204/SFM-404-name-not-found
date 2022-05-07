package hu.unideb.inf.notfound.model;

import hu.unideb.inf.notfound.model.Products;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvImporter {

    public static List<Products> CsvImporter(String csvlocation)
    {
        List<Products> termekek = new ArrayList<>();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(csvlocation));
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {

                System.out.println(line);
                String[] sordarab = line.split(";");

                Products product = new Products();

                product.setProduct_code(sordarab[0]);
                product.setProduct_name(sordarab[1]);
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
