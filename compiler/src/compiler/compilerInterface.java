/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Orion
 */
public class compilerInterface extends javax.swing.JFrame {
    public static Compiler cmp = new Compiler();
    
    public compilerInterface() {
        initComponents();
    }
    public static short convert(String n) {
      return Short.valueOf(n, 16);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Comic Sans MS", 0, 9)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("org 200\nLDA B\nCMA\nINC\nADD A\nSNA\nBUN Y1\nLDA B\nBUN Y2\nY1: LDA A\nY2: RET\norg 2F0\nA equ A2\nB equ 8C\nSTOP");
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Ad", "Adres"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jButton1)))
                .addContainerGap(201, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String[] commands =  jTextArea1.getText().split("\n");
        String[] pcStart = commands[0].split(" ");
        int symbolCounter = -1;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("AD");
        model.addColumn("ADRES");
        for(int i = 0;i<commands.length;i++){
            String[] command = commands[i].split(" ");
            if(command[0].toUpperCase().equals("ORG"))
            {
                cmp.setPC(convert(command[1]));
                symbolCounter = -1;
            }
            if(cmp.command.get(command[0]) == null && !command[0].toUpperCase().equals("STOP") && !command[0].toUpperCase().equals("ORG"))
            {
                cmp.memoryMap.put(command[0].replace(":",""), cmp.getPC() + (symbolCounter) );
                String[] tmp = {command[0].replace(":",""),Integer.toHexString(Integer.valueOf((Integer)cmp.memoryMap.get(command[0].replace(":","")))).toUpperCase()};
                model.addRow(tmp);
            }
            
            symbolCounter++;
        }
        jTable1.setModel(model);
        DefaultTableModel opcode = new DefaultTableModel();
        opcode.addColumn("ADRES");
        opcode.addColumn("OPCODE");
        for(int i = 0;i<commands.length;i++){
            String[] command = commands[i].split(" ");
            if(command[0].toUpperCase().equals("ORG"))
            {
                cmp.setPC(convert(command[1]));
                symbolCounter = -1;
                continue;
            }
            if(command[0].toUpperCase().equals("STOP"))
            {
                break;
            }
            if(cmp.command.get(command[0]) != null)
            {
                int op = 0;
                if(command.length==1){
                    op =  (Integer)(cmp.command.get(command[0]));
                }else if(command.length==2){
                    op =  (Integer)(cmp.command.get(command[0])) + (Integer)cmp.memoryMap.get(command[1]);//
                }
                String[] tmp = {Integer.toHexString(cmp.getPC()).toUpperCase(),Integer.toHexString( 0x10000 | op).substring(1).toUpperCase()};
                opcode.addRow(tmp);
                cmp.setPC(cmp.getPC()+1 );
            }else
            {
                int op = 0;
                //System.out.println(command[1]);
                
                if(command.length==2){
                    System.out.println(command[1]);
                    op =  (Integer)(cmp.command.get(command[1]));
                }else if(command.length==3){
                    if(command[1].equalsIgnoreCase("equ"))
                    {
                        op = (Integer.valueOf(command[2],16));
                    }else{
                        op =  (Integer)(cmp.command.get(command[1])) + (Integer)cmp.memoryMap.get(command[2]);
                    }
                }
                String[] tmp = {Integer.toHexString(cmp.getPC()).toUpperCase(),Integer.toHexString( 0x10000 | op).substring(1).toUpperCase()};//Integer.toHexString(op)
                
                opcode.addRow(tmp);
                cmp.setPC(cmp.getPC()+1 );
                
            }
            
            symbolCounter++;
        }
        jTable2.setModel(opcode);
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(compilerInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(compilerInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(compilerInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(compilerInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new compilerInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
