/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardlayout;

/**
 *
 * @author jarno
 */
public class Credit {
    int geldOpnemen;
    AccountsConnection con;
    public Credit(AccountsConnection a) {
        con = a;
    }
    
    public int getCredit(){
        return geldOpnemen;
    }
    public void setCredit(int x){
      geldOpnemen = con.getCredit()-x;
    }
}
