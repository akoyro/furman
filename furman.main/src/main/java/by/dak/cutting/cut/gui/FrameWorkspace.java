/*
 * DrawFrame.java
 *
 * Created on 25. jen 2006, 22:37
 */

package by.dak.cutting.cut.gui;

import by.dak.cutting.cut.base.CuttedElement;
import by.dak.cutting.cut.base.WorkSpace;
import by.dak.cutting.cut.graphics.Draw;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * @author Peca
 */
public class FrameWorkspace extends javax.swing.JFrame
{

    public float rating = 0;
    public WorkSpace workSpace;
    public CuttedElement[] cuttedElements;

    /**
     * Creates new form DrawFrame
     */


    public FrameWorkspace(WorkSpace workSpace, CuttedElement[] cuttedElements)
    {
        initComponents();
        this.workSpace = workSpace;
        this.cuttedElements = cuttedElements;
        setSize(500, 400);
        updateLabels();
    }

    private void paint()
    {
        if (workSpace == null) return;
        Graphics g = jPanel1.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g.clearRect(0, 0, jPanel1.getWidth(), jPanel1.getHeight());
        float sx = (float) jPanel1.getWidth() / (float) workSpace.getPlateSize().getWidth();
        float sy = (float) jPanel1.getHeight() / (float) workSpace.getPlateSize().getHeight();
        sx = Math.min(sx, sy);//*0.95f;
        g2.setTransform(AffineTransform.getScaleInstance(sx, sx));

        g.setColor(new Color(128, 64, 0));
        Draw.fillRect(workSpace.getPlateSize().toRect(), g);
        g.setColor(Color.black);
        Draw.drawRect(workSpace.getPlateSize().toRect(), g);
        if (cbShowWorkareas1.isSelected())
        {
            g.setColor(Color.white);
            Draw.drawWorkSpace(workSpace, g);
            Integer index = new Integer(jSpinner1.getValue().toString());
            if (index < workSpace.workAreas.size() && index >= 0)
            {
                Draw.drawWorkArea(workSpace.workAreas.get(index), 0.0f, g);
            }
        }

        if (cuttedElements != null) Draw.drawCuttedElements(cuttedElements, g);
    }

    public void hideControls()
    {
        jPanel2.setVisible(false);
    }

    public void normalizeSize()
    {
//        this.getContentPane().setSize(factory.getWorkSpace().getPlateSize().getWidth(), factory.getWorkSpace().getPlateSize().getHeight());
        this.setSize(workSpace.getPlateSize().getWidth(), workSpace.getPlateSize().getHeight() + 35);
    }

    public void updateLabels()
    {
        if (workSpace == null) return;
        int cnt = workSpace.workAreas.size();
        lblWorkAreasCount1.setText(String.valueOf(cnt));
        lblFreeArea1.setText(String.valueOf(workSpace.getFreeArea()));
        //lblElementCount1.setText(String.valueOf(factory.getElementCount())+"/"+String.valueOf(factory.getUncuttedElementCount()));
        //lblRightSpace1.setText(String.valueOf(factory.getRightEmptySpace()));
        lblRating1.setText(String.valueOf(rating));
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        paint();
    }

    public void update()
    {
        updateLabels();
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
        jPanel2 = new javax.swing.JPanel();
        buttRedraw1 = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        lblWorkAreasCount1 = new javax.swing.JLabel();
        cbShowWorkareas1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblFreeArea1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblElementCount1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblRightSpace1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblRating1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFocusCycleRoot(false);
        addComponentListener(new java.awt.event.ComponentAdapter()
        {
            public void componentShown(java.awt.event.ComponentEvent evt)
            {
                formComponentShown(evt);
            }
        });

        jPanel2.setMaximumSize(new java.awt.Dimension(32767, 200));
        buttRedraw1.setText("Redraw");
        buttRedraw1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                buttRedraw1MouseClicked(evt);
            }
        });

        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                jSpinner1StateChanged(evt);
            }
        });

        lblWorkAreasCount1.setText("jLabel1");

        cbShowWorkareas1.setText("Show workareas");
        cbShowWorkareas1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbShowWorkareas1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cbShowWorkareas1.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                cbShowWorkareas1StateChanged(evt);
            }
        });

        jLabel1.setText("Workareas:");

        jLabel2.setText("Free area:");

        lblFreeArea1.setText("jLabel3");

        jLabel3.setText("Elements:");

        lblElementCount1.setText("0");

        jLabel5.setText("Right space:");

        lblRightSpace1.setText("jLabel6");

        jLabel4.setText("Rating:");

        lblRating1.setText("jLabel6");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jPanel2Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                        .add(buttRedraw1)
                                                        .add(cbShowWorkareas1)))
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .add(jSpinner1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(68, 68, 68)))
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                        .add(jPanel2Layout.createSequentialGroup()
                                                .add(jLabel3)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(lblElementCount1))
                                        .add(jPanel2Layout.createSequentialGroup()
                                                .add(jLabel2)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(lblFreeArea1))
                                        .add(jPanel2Layout.createSequentialGroup()
                                                .add(jLabel1)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(lblWorkAreasCount1))
                                        .add(jPanel2Layout.createSequentialGroup()
                                                .add(jLabel5)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(lblRightSpace1)))
                                .add(83, 83, 83)
                                .add(jLabel4)
                                .add(29, 29, 29)
                                .add(lblRating1)
                                .add(92, 92, 92))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel2Layout.createSequentialGroup()
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(buttRedraw1)
                                        .add(jLabel1)
                                        .add(lblWorkAreasCount1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(jLabel4)
                                        .add(lblRating1))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(lblFreeArea1))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(jLabel3)
                                        .add(jSpinner1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(lblElementCount1))
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jPanel2Layout.createSequentialGroup()
                                                .add(12, 12, 12)
                                                .add(cbShowWorkareas1))
                                        .add(jPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                                        .add(jLabel5)
                                                        .add(lblRightSpace1))))
                                .addContainerGap())
        );
        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter()
        {
            public void componentResized(java.awt.event.ComponentEvent evt)
            {
                jPanel1ComponentResized(evt);
            }
        });

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </draw-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt)
    {//GEN-FIRST:event_formComponentShown
        updateLabels();
    }//GEN-LAST:event_formComponentShown

    private void cbShowWorkareas1StateChanged(javax.swing.event.ChangeEvent evt)
    {//GEN-FIRST:event_cbShowWorkareas1StateChanged
        paint();
    }//GEN-LAST:event_cbShowWorkareas1StateChanged

    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt)
    {//GEN-FIRST:event_jPanel1ComponentResized
        paint();
    }//GEN-LAST:event_jPanel1ComponentResized

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt)
    {//GEN-FIRST:event_jSpinner1StateChanged
        paint();
    }//GEN-LAST:event_jSpinner1StateChanged


    private void buttRedraw1MouseClicked(java.awt.event.MouseEvent evt)
    {//GEN-FIRST:event_buttRedraw1MouseClicked
        paint();
    }//GEN-LAST:event_buttRedraw1MouseClicked

    /**
     * @param args the command line arguments
     */
    /* public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameWorkspace().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttRedraw1;
    private javax.swing.JCheckBox cbShowWorkareas1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel lblElementCount1;
    private javax.swing.JLabel lblFreeArea1;
    private javax.swing.JLabel lblRating1;
    private javax.swing.JLabel lblRightSpace1;
    private javax.swing.JLabel lblWorkAreasCount1;
    // End of variables declaration//GEN-END:variables

}
