/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
/**
 *
 * @author marqu
 */
public class Burgers extends javax.swing.JPanel {
    private Fast_Food mainPanel;
    public Burgers(Fast_Food mainPanel) {
        this.mainPanel = mainPanel;
        initComponents();
    }
    

    public void setBurgerTotal(double total){
        this.total = total;
    }
    public double getBurgerTotal(){
        return total;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        

        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jSpinner3 = new javax.swing.JSpinner();
        jSpinner4 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

// For jSpinner1
SpinnerNumberModel model1 = (SpinnerNumberModel) jSpinner1.getModel();
model1.setMinimum(0);

// For jSpinner2
SpinnerNumberModel model2 = (SpinnerNumberModel) jSpinner2.getModel();
model2.setMinimum(0);

// For jSpinner3
SpinnerNumberModel model3 = (SpinnerNumberModel) jSpinner3.getModel();
model3.setMinimum(0);

// For jSpinner4
SpinnerNumberModel model4 = (SpinnerNumberModel) jSpinner4.getModel();
model4.setMinimum(0);

        jLabel1.setText("Hamburger");

        jLabel2.setText("Cheeseburger");

        jLabel3.setText("impossible Burger");

        jLabel4.setText("Mushroom Burger");

        jLabel5.setText("Burgers");

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try{
                if ( (int) jSpinner1.getValue() == 0 && (int) jSpinner2.getValue()== 0 &&
                (int) jSpinner3.getValue()== 0 && (int) jSpinner4.getValue()== 0 ) {
                    try {
                        JOptionPane.showMessageDialog(null, "You must choose at least one option or go back");
            
                    } catch (Exception e) {
                        // Handle exceptions here
                        e.printStackTrace();
                    }
            }else{
            int Hamburger = (int) jSpinner1.getValue();
            int Cheeseburger = (int) jSpinner2.getValue();
            int Impossible = (int) jSpinner3.getValue();
            int Mushroom = (int) jSpinner4.getValue();
            double total = (Hamburger*2.14) + (Cheeseburger*2.14) + (Impossible*3.14) + (Mushroom*2.14);
            DecimalFormat df = new DecimalFormat("#.##");
            setBurgerTotal(Double.parseDouble(df.format(total)));
            JOptionPane.showMessageDialog(null, "Total price from burgers $" + df.format(total));
            mainPanel.switchToMainPanel();
            }
        } catch (Exception e) {
            // Handle the exception (e.g., print the stack trace)
            e.printStackTrace();
            // You can also show an error message to the user if needed
            JOptionPane.showMessageDialog(null, "An error occurred. Please try again.");
        }
            }
        });

        jButton3.setText("Go Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
                
            }
        });
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(126, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(116, 116, 116))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(44, 44, 44))
        );
    }// </editor-fold>               

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {  
            mainPanel.switchToMainPanel();
        
    }                             
    // Variables declaration - do not modify  
    private javax.swing.JButton jButton1;   
    private javax.swing.JButton jButton3;               
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private double total;
    // End of variables declaration       
    
    
}
