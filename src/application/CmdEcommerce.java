package application;

import java.util.Vector;

public class CmdEcommerce {

    private Vector<Object> productsList;


    // tracked -> staged
    public CmdEcommerce() {
        productsList = new Vector<Object>();
    }

    public int main_loop_program() {

        System.out.println(productsList.isEmpty());
        return 0;
    }

}
