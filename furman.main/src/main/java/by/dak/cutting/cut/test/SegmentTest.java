/*
 * SegmentTest.java
 *
 * Created on 25.jen 2007, 17:41
 */

package by.dak.cutting.cut.test;

import by.dak.cutting.cut.common.Common;
import by.dak.cutting.cut.graphics.Draw;
import by.dak.cutting.cut.guillotine.Segment;

import javax.swing.*;
import java.awt.*;

/**
 * @author Peca
 */
public class SegmentTest extends javax.swing.JFrame
{

    private Segment segment;
    private Segment segment2;

    /**
     * Creates new form SegmentTest
     */
    public SegmentTest()
    {
        initComponents();
        segment = new Segment(400, 200);
        segment.setFixedLength(true);
        segment.setFixedWidth(true);
        Segment seg;
        seg = new Segment(100, 100);
        seg.addSegment(new Segment(50, 50));
        segment.addSegment(seg);
        segment2 = seg;
        //seg.addSegment(new Segment(80, 130));

        //seg.addSegment(new Segment(100, 20));
        //segment.addSegment(new Segment(20, 20));
        jLabel1.setText(String.valueOf(seg.canSetWidth(1000)));

        JPanel panel;
        panel = new JPanel()
        {
            public void paint(Graphics g)
            {
                draw(g);
            }
        };
        jPanel1.add(panel);

        panel = new JPanel()
        {
            public void paint(Graphics g)
            {
                drawSegment(0, 0, segment2, g);
            }
        };
        jPanel2.add(panel);
    }


    private void draw(Graphics g)
    {
        g.clearRect(0, 0, (int) g.getClipBounds().getWidth(), (int) g.getClipBounds().getHeight());
        drawSegment(0, 0, segment, g);
        //drawSegmentCut(0, 0, individual.getSegment(), individual.getSegment().getMaxLevel(), g);
    }


    private void drawSegment(int x, int y, Segment segment, Graphics g)
    {

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1));

        int s = 4 - segment.getLevel();
        s = 0;
        int w, h, w2, h2;
        Color c;
        if ((segment.getLevel() % 2) == 0)
        {
            w = segment.getLength();
            //w2 = segment.length;
            h = segment.getWidth();
            //h2 = segment.width;
        }
        else
        {
            w = segment.getWidth();
            // w2 = segment.width;
            h = segment.getLength();
            //h2 = segment.length;
        }
        if (segment.getLevel() == 0) c = Color.lightGray;
        else c = Draw.getColor(segment.getLevel());

        g.setColor(c);
        g.fillRect(x, y, w + s, h + s);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, w + s, h + s);
        //g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {10, 5}, 0.0f));
        //if(segment.getLevel()== 4)g2.drawRect(x, y, w2+s, h2+s);


        int y2 = y;
        int x2 = x;
        for (Segment seg : segment.getItems())
        {
            drawSegment(x2, y2, seg, g);
            if ((segment.getLevel() % 2) == 0) x2 += seg.getWidth();
            else y2 += seg.getWidth();
        }
        /*  if(segment.getElement() != null){
            Element el = segment.getElement();
            g.setColor(Color.ORANGE);
            //g.setColor(Draw.getColor(el.index));
            g.fillRect(x2, y2, el.getWidth(), el.getHeight());
            g.setColor(Color.BLACK);
            g.drawRect(x2, y2, el.getWidth(), el.getHeight());
            int sw = g.getFontMetrics().stringWidth(String.valueOf(el.index));
            int sh = g.getFontMetrics().getHeight();
            g.drawString(String.valueOf(el.index), x2+(el.getWidth()-sw)/2, y2+(el.getHeight()+sh)/2);
            
        }*/

    }


    private boolean tryCut(Segment parent, Segment child, int level)
    {

        if (parent.getItemsCount() >= 3) return false;
        if (parent.canAddSegment(child))
        {
            parent.addSegment(child);
            return true;
        }
        return false;
    }

    private boolean cut(Segment parent, Segment child, int level)
    {
        if (parent.getLevel() == level)
        {
            return tryCut(parent, child, level);
        }
        for (Segment seg : parent.getItems())
        {
            if (cut(seg, child, level)) return true;
        }
        //nenalezeno

        Segment seg = new Segment();
        seg.addSegment(child);
        return cut(parent, seg, --level);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <draw-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnLevel0 = new javax.swing.JButton();
        btnLevel1 = new javax.swing.JButton();
        btnLevel2 = new javax.swing.JButton();
        btnLevel3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnLevel4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        btnLevel0.setText("level0");
        btnLevel0.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                addSegmentClicked(evt);
            }
        });

        btnLevel1.setText("level1");
        btnLevel1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                addSegmentClicked(evt);
            }
        });

        btnLevel2.setText("level2");
        btnLevel2.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                addSegmentClicked(evt);
            }
        });

        btnLevel3.setText("level3");
        btnLevel3.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                addSegmentClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());

        btnLevel4.setText("level4");
        btnLevel4.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                addSegmentClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                        .add(jLabel2)
                                                        .add(layout.createSequentialGroup()
                                                                .add(jLabel1)
                                                                .add(107, 107, 107)
                                                                .add(btnLevel0)
                                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                                                .add(btnLevel1)
                                                                .add(18, 18, 18)
                                                                .add(btnLevel2)
                                                                .add(18, 18, 18)
                                                                .add(btnLevel3)
                                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                                .add(btnLevel4))))
                                        .add(layout.createSequentialGroup()
                                                .add(21, 21, 21)
                                                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 251, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                                .addContainerGap()
                                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 240, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                        .add(jLabel1)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                                .add(btnLevel0)
                                                .add(btnLevel1)
                                                .add(btnLevel2)
                                                .add(btnLevel3)
                                                .add(btnLevel4)))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel2)
                                .add(18, 18, 18)
                                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 197, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </draw-fold>//GEN-END:initComponents

    private void addSegmentClicked(java.awt.event.MouseEvent evt)
    {//GEN-FIRST:event_addSegmentClicked
        Segment seg = segment2;

        if (evt.getSource() == btnLevel0) cut(this.segment, seg, 0);
        if (evt.getSource() == btnLevel1) cut(this.segment, seg, 1);
        if (evt.getSource() == btnLevel2) cut(this.segment, seg, 2);
        if (evt.getSource() == btnLevel3) cut(this.segment, seg, 3);
        if (evt.getSource() == btnLevel4)
        {
            seg.setFixedLength(true);
            cut(this.segment, seg, 4);
        }
        this.repaint();
        segment2 = new Segment(Common.nextInt(60) + 20, Common.nextInt(60) + 20);
    }//GEN-LAST:event_addSegmentClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new SegmentTest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLevel0;
    private javax.swing.JButton btnLevel1;
    private javax.swing.JButton btnLevel2;
    private javax.swing.JButton btnLevel3;
    private javax.swing.JButton btnLevel4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}
