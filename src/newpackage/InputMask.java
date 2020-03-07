package newpackage;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
public class InputMask extends JDialog implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JLabel prompt = null;
    private MaskFormatter msk = null;
    private JFormattedTextField text = null;
    private JButton ok, cancel;
    private String response, aprompt, mask;
    public InputMask(JFrame parent, String aprompt, String title, String mask){
        super(parent, title, true);
        this.aprompt = aprompt;
        this.mask = mask;
        setContentPane(getContainer());
        setResizable(false);
        setSize(400, 300);
        setLocationRelativeTo(this);
        setResponse("");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
    private JPanel getContainer(){
        JPanel panel = new JPanel(new BorderLayout());
        Font font = new Font("Tahoma",0,14);
        prompt = new JLabel(aprompt);
        prompt.setFont(font);
        if (!"".equals(mask)){
            try {
                msk = new MaskFormatter(mask);
                msk.setPlaceholderCharacter('_');
                text = new JFormattedTextField(msk);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        } else {
            text = new JFormattedTextField();
        }
        text.setFont(font);
        text.setColumns(15);
        JPanel textpanel = new JPanel(new GridLayout(2,1));
        textpanel.add(prompt);
        textpanel.add(text);
        panel.add(textpanel, BorderLayout.CENTER);
        ok = new JButton("OK");
        ok.addActionListener(this);
        cancel = new JButton("Cancelar");
        cancel.addActionListener(this);
        JPanel buttonspanel = new JPanel(new GridLayout(1,2));
        buttonspanel.add(ok);
        buttonspanel.add(cancel);
        panel.add(buttonspanel, BorderLayout.SOUTH);
        return panel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==ok){
            setResponse(text.getText());
        } else if (e.getSource()==cancel){
            setResponse("");
        }
        setVisible(false);
        dispose();
    }
    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }
}