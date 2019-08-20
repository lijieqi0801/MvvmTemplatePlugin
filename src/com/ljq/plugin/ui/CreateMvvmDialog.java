package com.ljq.plugin.ui;

import com.ljq.plugin.TemplateEnum;
import org.apache.http.util.TextUtils;

import javax.swing.*;
import java.awt.event.*;

public class CreateMvvmDialog extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField;
    private JRadioButton btnAct;
    private JRadioButton btnFragment;
    private JRadioButton btnDialog;

    private CreateCallBack callBackListener;

    public void setCallBackListener(CreateCallBack call) {
        this.callBackListener = call;
    }

    public CreateMvvmDialog() {
        setTitle("input module name");
        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        String moduleName = textField.getText().trim();
        boolean isAct = btnAct.isSelected();
        boolean isFragemnt = btnFragment.isSelected();
        boolean isDialog = btnDialog.isSelected();
        TemplateEnum type = TemplateEnum.Act;
        if (!TextUtils.isEmpty(moduleName)) {
            if (isAct) type = TemplateEnum.Act;
            if (isFragemnt) type = TemplateEnum.Fragment;
            if (isDialog) type = TemplateEnum.Dialog;
            dispose();
            if (callBackListener != null) {
                callBackListener.onCall(moduleName, type);
            }
        } else {
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CreateMvvmDialog dialog = new CreateMvvmDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public interface CreateCallBack {
        void onCall(String name, TemplateEnum type);
    }
}