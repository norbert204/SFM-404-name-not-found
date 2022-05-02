package hu.unideb.inf.notfound.model;

import hu.unideb.inf.notfound.model.Products;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvImporter {

    public static void CsvImporter()
    {
        List<Products> termekek = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("termekek.csv"));
            String line = reader.readLine();
            while (line != null) {

                System.out.println(line);
                String[] sordarab = line.split(";");

                Products product = new Products();

                product.setProduct_code(Integer.parseInt(sordarab[0]));
                product.setProduct_name(sordarab[1]);
                product.setUnit_price(Integer.parseInt(sordarab[2]));
                product.setCategory(sordarab[4]);
                product.setDescription(sordarab[5]);


                termekek.add(product);

                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
