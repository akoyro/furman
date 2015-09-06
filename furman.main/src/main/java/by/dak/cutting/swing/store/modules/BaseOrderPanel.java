/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ADeliveryOrderPanel.java
 *
 * Created on 02.02.2010, 16:27:36
 */

package by.dak.cutting.swing.store.modules;

import by.dak.cutting.swing.store.helpers.AEntityNEDActions;
import by.dak.swing.table.ListNaviTable;
import by.dak.swing.table.ListUpdater;
import by.dak.swing.table.NewEditDeletePanel;

import javax.swing.*;
import java.beans.Beans;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 * @author user0
 */
public class BaseOrderPanel extends javax.swing.JPanel
{
    private PropertyChangeListener updateGuiListener;

    /**
     * Creates new form ADeliveryOrderPanel
     */
    public BaseOrderPanel()
    {
        initComponents();
        if (!Beans.isDesignTime())
        {
            initEnvironment();
        }
    }

    private void initEnvironment()
    {
        setUpdateGuiListener(new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {
                listNaviTable1.reload();
            }
        });

    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        listNaviTable1 = new by.dak.swing.table.ListNaviTable();
        newEditDeletePanel1 = new by.dak.swing.table.NewEditDeletePanel();

        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N

        listNaviTable1.setName("listNaviTable1"); // NOI18N

        newEditDeletePanel1.setName("newEditDeletePanel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(listNaviTable1, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                                .addComponent(newEditDeletePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(listNaviTable1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newEditDeletePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private by.dak.swing.table.ListNaviTable listNaviTable1;
    private by.dak.swing.table.NewEditDeletePanel newEditDeletePanel1;
    // End of variables declaration//GEN-END:variables

    public ListNaviTable getTableCtrl()
    {
        return listNaviTable1;
    }

    public NewEditDeletePanel getNewEditDeletePanel1()
    {
        return newEditDeletePanel1;
    }

    public PropertyChangeListener getUpdateGuiListener()
    {
        return updateGuiListener;
    }

    public void setUpdateGuiListener(PropertyChangeListener updateGuiListener)
    {
        this.updateGuiListener = updateGuiListener;
    }

    public void init()
    {
        ListUpdater listUpdater = getTableCtrl().getListUpdater();
        if (listUpdater != null)
        {
            getTableCtrl().init();
            getNewEditDeletePanel1().setNewEditDeleteActions(listUpdater.getNewEditDeleteActions());
            getNewEditDeletePanel1().setVisible(listUpdater.getNewEditDeleteActions() != null);
            if (listUpdater.getNewEditDeleteActions() != null)
            {
                listUpdater.getNewEditDeleteActions().addPropertyChangeListener(AEntityNEDActions.PROPERTY_updateGui, getUpdateGuiListener());
            }
        }

        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                JRootPane rootPane = getRootPane();
                if (rootPane != null)
                {
                    rootPane.setDefaultButton(getNewEditDeletePanel1().getButtonNew());
                }
            }
        };
        SwingUtilities.invokeLater(runnable);
    }


}
