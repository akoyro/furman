/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewEditDeletePanel.java
 *
 * Created on 07.12.2009, 15:17:40
 */

package by.dak.swing.table;

import org.jdesktop.application.Application;

import javax.swing.*;
import java.beans.Beans;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author admin
 */
public class NewEditDeletePanel extends javax.swing.JPanel
{
    /**
     * Creates new form NewEditDeletePanel
     */
    public NewEditDeletePanel()
    {
        initComponents();
        if (!Beans.isDesignTime())
        {
            initEnvironment();
        }
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

        setButtonNew(new JButton());
        buttonOpen = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();

        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        getButtonNew().setText("Создать");
        getButtonNew().setName("buttonNew"); // NOI18N
        getButtonNew().addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                buttonNewActionPerformed(evt);
            }
        });
        add(getButtonNew());

        buttonOpen.setText("Открыть");
        buttonOpen.setName("buttonOpen"); // NOI18N
        add(buttonOpen);

        buttonDelete.setText("Удалить");
        buttonDelete.setName("buttonDelete"); // NOI18N
        add(buttonDelete);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonNewActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonNewActionPerformed
    {//GEN-HEADEREND:event_buttonNewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonNewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonNew;
    private javax.swing.JButton buttonOpen;
    // End of variables declaration//GEN-END:variables

    private NewEditDeleteActions newEditDeleteActions;


    private void initEnvironment()
    {
        if (Beans.isDesignTime())
            return;

        addPropertyChangeListener("newEditDeleteActions", new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {
                NewEditDeleteActions o = (NewEditDeleteActions) evt.getOldValue();
                NewEditDeleteActions n = (NewEditDeleteActions) evt.getNewValue();
                if (n != null)
                {
                    ActionMap actionMap = Application.getInstance().getContext().getActionMap(NewEditDeleteActions.class, n);
                    setActionTo(actionMap, "newValue", getButtonNew());
                    setActionTo(actionMap, "openValue", buttonOpen);
                    setActionTo(actionMap, "deleteValue", buttonDelete);
                }
                else
                {
                    getButtonNew().setAction(null);
                    buttonOpen.setAction(null);
                    buttonDelete.setAction(null);
                }
            }
        });
    }

    private void setActionTo(ActionMap actionMap, String actionName, JButton button)
    {
        Action action = actionMap.get(actionName);
        button.setAction(action);
        button.setEnabled(action != null);
    }

    public NewEditDeleteActions getNewEditDeleteActions()
    {
        return newEditDeleteActions;
    }

    public void setNewEditDeleteActions(NewEditDeleteActions newEditDeleteActions)
    {
        NewEditDeleteActions old = this.newEditDeleteActions;
        this.newEditDeleteActions = newEditDeleteActions;
        firePropertyChange("newEditDeleteActions", old, newEditDeleteActions);
    }

    public JButton getButtonNew()
    {
        return buttonNew;
    }

    public void setButtonNew(JButton buttonNew)
    {
        this.buttonNew = buttonNew;
    }
}
