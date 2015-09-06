/*
 * SChromosomeView.java
 *
 * Created on 10. listopad 2006, 17:55
 */

package by.dak.cutting.cut.gui.chromosome;

import by.dak.cutting.cut.genetics.Chromosome;

/**
 * @author Peca
 */
public class ChromosomeView extends javax.swing.JPanel
{
    Chromosome chrom;

    /**
     * Creates new form SChromosomeView
     */
    public ChromosomeView()
    {
        initComponents();
    }

    public void setChromosome(Chromosome chrom)
    {
        this.chrom = chrom;
        update();
    }

    public void update()
    {
        jTextField1.setText(chrom.toString());
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <draw-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        jTextField1 = new javax.swing.JTextField();
        buttUpdate1 = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));

        jTextField1.setText("SChromosome");
        jTextField1.setMaximumSize(new java.awt.Dimension(2147483647, 20));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                jTextField1KeyPressed(evt);
            }
        });

        add(jTextField1);

        buttUpdate1.setText("Update");
        buttUpdate1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                buttUpdate1MouseClicked(evt);
            }
        });

        add(buttUpdate1);

    }// </draw-fold>//GEN-END:initComponents

    private void buttUpdate1MouseClicked(java.awt.event.MouseEvent evt)
    {//GEN-FIRST:event_buttUpdate1MouseClicked
        chrom.fromString(jTextField1.getText());
    }//GEN-LAST:event_buttUpdate1MouseClicked

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt)
    {//GEN-FIRST:event_jTextField1KeyPressed

    }//GEN-LAST:event_jTextField1KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttUpdate1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
