/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emppayroll;

/**
 *
 * @author sachith
 */
public class EmpPayroll {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Mainmenu mm=new Mainmenu();
        //mm.setVisible(true);
        
        
        splash ss= new splash();
        ss.setVisible(true);
        try {
            for (int i = 0; i <=100; i++) {
                Thread.sleep(10);
                ss.lblload.setText(Integer.toString(i)+"%");
                Login ll=new Login();
                //Mainmenu mm=new Mainmenu();
                if(i==100){
                    ss.dispose();
                    ll.show();
                    //mm.show();
                }
                
            }
        } catch (Exception e) {
        }
    }
    
}
