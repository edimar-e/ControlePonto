package newpackage;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main32 extends JFrame {

	private JPanel JPanel_Host;
        private JPanel JPanel_TabA;
        private JPanel JPanel_TabB;
        private JPanel JPanel_WC;
        private JPanel JPanel_Novo;
        private JPanel JPanel_Deletar;
        private JPanel JPanel_VerDados;
        
        boolean isloged = false;
        Webcam webcam =null;
        String senhaAdmin = "32ad";
        int janelaY = 662,janelaX=410;
        int PanelHostX = janelaX -10;
        int PanelHostY = janelaY -10;
        int TabX =  PanelHostX - 35 ;
        int TabY =  PanelHostY - 60 ;
        int PanelTabX = TabX - 30;
        int PanelTabY = TabY - 30;

        
        
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main32 frame = new Main32();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main32() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("lib/favicon.png"));
		this.setTitle("Ponto EC");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setSize(janelaX, janelaY);
                this.setLocationRelativeTo(null);
                this.setResizable(false);
		JPanel_Host = new JPanel();
                JPanel_Host.setSize(PanelHostX, PanelHostY);
		JPanel_Host.setBorder( BorderFactory.createTitledBorder("Ponto Funcionários Estacionamento Central"));
		this.setContentPane(JPanel_Host);
		JPanel_Host.setLayout(null);
                
                //try {
                //    UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
                //} catch (Exception e) {
                //        e.printStackTrace();
                // }
		
                //============================
               
		//if(sq.ifExistDB()){File file = new File("C:\\Users\\Daniel\\Documents\\Faculdade\\arquivo.txt");
                final MJSQLite sq ;
                File file = new File("ECENTRAL.db");
                if(file.exists()){
                    sq = new MJSQLite();
                }else{
                    sq = new MJSQLite();
                    sq.onCreate();
                }
                //===========================

                JPanel_TabA = new JPanel();
		JPanel_TabA.setSize(PanelTabX, PanelTabY);
                JPanel_TabB = new JPanel();
		JPanel_TabB.setSize(PanelTabX, PanelTabY);

                
		JTabbedPane JTabbedPane1 = new JTabbedPane();
                JTabbedPane1.setBounds(20, 20, TabX, TabY);
                JTabbedPane1.addTab("Ponto Funcionarios",JPanel_TabA );
                JTabbedPane1.addTab("Administrador",JPanel_TabB );
		JPanel_Host.add(JTabbedPane1);

                
                try{
                webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.QVGA.getSize());
                WebcamPanel wcpanel = new WebcamPanel(webcam);                          
                JPanel_WC = new JPanel();
                JPanel_WC.setBounds(20,10,320, 240);
                JPanel_WC.add(wcpanel);
                JPanel_TabA.add(JPanel_WC);
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Não foi possível abrir a Webcam");
                }
		
                
                
                
                
                
                //
                ///////////////////////////////////////////////////////////////////////////////////
                JComboBox JComboBoxLogin = new JComboBox();
                JComboBoxLogin.setBounds(80, 350, 200, 32);              
                
                
                JPanel_TabA.add(JComboBoxLogin);
                
                
                JPasswordField JPasswordField1 = new JPasswordField();
		JPasswordField1.setBounds(80,400,200, 32);
		JPanel_TabA.add(JPasswordField1);
                JPasswordField1.setText("");
		JPasswordField1.setColumns(10);
                
                
		JButton btLogin = new JButton("Ok");
                btLogin.setBounds(130, 440, 100, 32);
		JPanel_TabA.add(btLogin);
                
                JPanel_TabA.setLayout(null);
                ///////////////////////////
                
                //////TAB B////
                //////Panel Novo/////////////////////////////////////////////////////////
                JPanel_Novo = new JPanel();
                JPanel_Novo.setBounds(10,25,340, 127);
		JPanel_Novo.setBorder( BorderFactory.createTitledBorder("Cadastrar Funcionário"));
		            
                JLabel JlabelNome = new JLabel("Nome");
                JlabelNome.setBounds(15, 20, 100, 20);
                JPanel_Novo.add(JlabelNome);
                
                JLabel JlabelSenha = new JLabel("Senha");
                JlabelSenha.setBounds(15, 45, 100, 20);
                JPanel_Novo.add(JlabelSenha);
                
                JLabel JlabelSenhaR = new JLabel("Repetir senha");
                JlabelSenhaR.setBounds(15, 70, 100, 20);
                JPanel_Novo.add(JlabelSenhaR);               
                
                JTextField JTextField1 = new JTextField();
                JTextField1.setBounds(110, 20, 129, 20);
                JPanel_Novo.add(JTextField1);
                
                JPasswordField JPasswordField2 = new JPasswordField();
		JPasswordField2.setBounds(110,45,129, 20);
                JPanel_Novo.add(JPasswordField2);
                
                JPasswordField JPasswordField3 = new JPasswordField();
		JPasswordField3.setBounds(110,70,129, 20);
                JPanel_Novo.add(JPasswordField3);
                
                JPanel JPanelHora = new JPanel();
                JPanelHora.setBounds(245, 15, 85, 100);
                JPanelHora.setBorder(BorderFactory.createTitledBorder("Horário"));
                JLabel AA = new JLabel("Entrada");
                AA.setBounds(12, 15, 60, 20);
                JPanelHora.add(AA);
                JLabel BB = new JLabel("Saída");
                BB.setBounds(12, 53, 45, 20);
                JPanelHora.add(BB);
                JTextField JTextFieldH = new JTextField();
                JTextFieldH.setBounds(12,35, 20, 20);
                JTextFieldH.setUI(new HintTextFieldUI("H",true,Color.BLACK));
                JPanelHora.add(JTextFieldH);
                JTextField JTextFieldM = new JTextField();
                JTextFieldM.setBounds(35,35, 20, 20);
                JTextFieldM.setUI(new HintTextFieldUI("M",true,Color.BLACK));
                JPanelHora.add(JTextFieldM);
                JTextField JTextFieldH2 = new JTextField();
                JTextFieldH2.setBounds(12,72, 20, 20);
                JTextFieldH2.setUI(new HintTextFieldUI("H",true,Color.BLACK));
                JPanelHora.add(JTextFieldH2);
                JTextField JTextFieldM2 = new JTextField();
                JTextFieldM2.setBounds(35,72, 20, 20);
                JTextFieldM2.setUI(new HintTextFieldUI("M",true,Color.BLACK));
                JPanelHora.add(JTextFieldM2);
                
                JPanelHora.setLayout(null);
                JPanel_Novo.add(JPanelHora);
                
		JButton btCriar = new JButton("Criar");
                btCriar.setBounds(110, 95, 129, 23);
                btCriar.setEnabled(false);
		JPanel_Novo.add(btCriar);

                JPanel_Novo.setLayout(null);
                JPanel_TabB.add(JPanel_Novo);
                
                ////Panel Deletar///////////////////////////////////////////////////////////////////////
                JPanel_Deletar = new JPanel();
                JPanel_Deletar.setBounds(10,155,340, 85);
		JPanel_Deletar.setBorder( BorderFactory.createTitledBorder("Deletar Funcionário"));
                
                JComboBox JComboBoxDel = new JComboBox();
                JComboBoxDel.setBounds(80, 20, 200, 23);
                JComboBoxDel.setEnabled(false);
                JPanel_Deletar.add(JComboBoxDel);
                
                JButton btDel = new JButton("Deletar");
                btDel.setBounds(110, 50, 129, 23);
                btDel.setEnabled(false);
		JPanel_Deletar.add(btDel);
		
               JPanel_Deletar.setLayout(null);
               JPanel_TabB.add(JPanel_Deletar);
               
               //ver dados
               JPanel_VerDados = new JPanel();
               JPanel_VerDados.setBounds(10, 244, 340, 312);
               JPanel_VerDados.setBorder(BorderFactory.createTitledBorder("Visualizar Dados"));

               JComboBox JComboBoxVer = new JComboBox();
               JComboBoxVer.setBounds(80, 20, 200, 23);
               JComboBoxVer.setEnabled(false);
               JPanel_VerDados.add(JComboBoxVer);
               
                JTextArea JTextAreaVD = new JTextArea();
                JTextAreaVD.setEditable(false);
               //JTextAreaVD.setBounds(10, 50, 319, 272);
               //JTextAreaVD.setBounds(0, 0, 319, 272);
               
               // String[] data = {"one", "two", "three", "four"};
               // JList<String> myList = new JList<String>(data);
 
               JScrollPane jScrollPane1;
               jScrollPane1 = new JScrollPane(JTextAreaVD);
               jScrollPane1.setBounds(10, 50, 319, 252);
               jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	       jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
               
               JPanel_VerDados.add(jScrollPane1);
               JPanel_VerDados.setLayout(null);
               JPanel_TabB.add(JPanel_VerDados);

               JButton JBAtivar = new JButton("Ativar Controles");
               JBAtivar.setBounds(105, 3, 159, 22);
               JPanel_TabB.add(JBAtivar);
               
               JPanel_TabB.setLayout(null);

               ///////////////////////////////////////////////////////////////////////////////		
               ///////////OPRAÇÕES//////////////////
               //////////////
               ////////
               //
               
               JBAtivar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!isloged){
                          if(vefyifadmin()){
                               isloged = true;
                               btCriar.setEnabled(true);
                               JComboBoxDel.setEnabled(true);
                               btDel.setEnabled(true);
                               JComboBoxVer.setEnabled(true);
                               JBAtivar.setText("Desativar Controles");
                          }else{
                              JOptionPane.showMessageDialog(null,"Senha incorreta.");
                              isloged = false;
                          }
                        }else{
                               btCriar.setEnabled(false);
                               JComboBoxDel.setEnabled(false);
                               btDel.setEnabled(false);
                               JComboBoxVer.setEnabled(false);
                               JBAtivar.setText("Ativar Controles");
                               isloged = false;
                        }
                    
                    }
                });
               
               
               
               
               
               //////criar///
               ///
               btCriar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                     
                        String letras = JTextFieldH.getText()+ JTextFieldH2.getText()+ JTextFieldM.getText()+ JTextFieldM2.getText();
                        for (int i = 0; i < letras.length(); i++) {
                            if (Character.isLetter(letras.charAt(i))==true){
                                JOptionPane.showMessageDialog(null,"Insira somente números para Horas e Minutos");
                                break;
                            }
                        }
                        if((JTextField1.getText().isEmpty() || new String(JPasswordField2.getPassword()).isEmpty())){
                                   JOptionPane.showMessageDialog(null,"Preencha os campos obrigatórios");
                            }else if(! new String(JPasswordField2.getPassword()).equals(new String(JPasswordField3.getPassword()))){
                                    JOptionPane.showMessageDialog(null,"Senhas não conferem");
                                }else if(JTextFieldH.getText().isEmpty() | JTextFieldH2.getText().isEmpty() | JTextFieldM.getText().isEmpty() | JTextFieldM2.getText().isEmpty()  ){
                                        int resposta;
                                        resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente adicionar um usuário sem horário de entrada e saída?\nNão será possível calcular horas extras e atrasos.", "Atenção!", JOptionPane.YES_NO_OPTION);
                                        if (resposta == JOptionPane.YES_OPTION) {
                                            //Usuário clicou em Sim. Executar o código correspondente.
                                             sq.setUsers(JTextField1.getText(),criptografia(new String(JPasswordField2.getPassword())), 0, 0, 0,0);
                                        } else if (resposta == JOptionPane.NO_OPTION) {
                                        //Usuário clicou em não. Executar o código correspondente.
                                        }
                                        
                                    }
                                    else{
                                        sq.setUsers(JTextField1.getText(),criptografia(new String(JPasswordField2.getPassword())), Integer.parseInt(JTextFieldH.getText()), Integer.parseInt(JTextFieldM.getText()), Integer.parseInt(JTextFieldH2.getText()), Integer.parseInt(JTextFieldM2.getText()));
                                        
                                    }

                            int j =  sq.getUsers().length;
                            String[] users = new String[j] ;
                            users = sq.getUsers();
                            
                            JComboBoxDel.removeAllItems();
                            JComboBoxDel.addItem( "---------------Selecione--------------");
                            for (int i = 0; i < users.length; i++) {
                                JComboBoxDel.addItem(users[i]);
                            }
                            
                            JComboBoxVer.removeAllItems();
                            JComboBoxVer.addItem( "---------------Selecione--------------");
                            for (int i = 0; i < users.length; i++) {
                                JComboBoxVer.addItem(users[i]);
                            } 
                            
                            JComboBoxLogin.removeAllItems();
                            JComboBoxLogin.addItem( "---------------Selecione--------------");
                            for (int i = 0; i < users.length; i++) {
                                JComboBoxLogin.addItem(users[i]);
                            }
                            

                          
                        
                        }	
		});
               
               
               ////////////set fas combos ao niciar
               int j =  sq.getUsers().length;
               String[] users = new String[j] ;
               users = sq.getUsers();
               
               JComboBoxDel.addItem( "---------------Selecione--------------");
               for (int i = 0; i < users.length; i++) {
                    JComboBoxDel.addItem(users[i]);
               } 

                JComboBoxLogin.removeAllItems();
                JComboBoxLogin.addItem( "---------------Selecione--------------");
                for (int i = 0; i < users.length; i++) {
                    JComboBoxLogin.addItem(users[i]);
                }
                JComboBoxVer.removeAllItems();
                JComboBoxVer.addItem( "---------------Selecione--------------");
                for (int i = 0; i < users.length; i++) {
                    JComboBoxVer.addItem(users[i]);
                } 
              


                //////////////////DELETAR////////////////////////////////////////////////////

               btDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                           
                            String tmp;
                            tmp = JComboBoxDel.getSelectedItem().toString();
                            sq.deleteUser(tmp);
                            
                            int j =  sq.getUsers().length;
                            String[] users = new String[j] ;
                            users = sq.getUsers();
                            
                            JComboBoxDel.removeAllItems();
                            JComboBoxDel.addItem( "---------------Selecione--------------");
                            for (int i = 0; i < users.length; i++) {
                                JComboBoxDel.addItem(users[i]);
                            }
                            
                            JComboBoxLogin.removeAllItems();
                            JComboBoxLogin.addItem( "---------------Selecione--------------");
                            for (int i = 0; i < users.length; i++) {
                                JComboBoxLogin.addItem(users[i]);
                            }
                            
                            JComboBoxVer.removeAllItems();
                            JComboBoxVer.addItem( "---------------Selecione--------------");
                            for (int i = 0; i < users.length; i++) {
                                JComboBoxVer.addItem(users[i]);
                            } 
                            
                         
			}
		});

               /////////////////////////////////////////////////////////////////////////
               /////////LOGIN
               /////
               //
               btLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                         
                            String tmp,pass,passinserted;String dataFormatada = "";
                            passinserted = new String(JPasswordField1.getPassword());
                            tmp = JComboBoxLogin.getSelectedItem().toString();
                            pass = sq.getPass(tmp);
                            if(passinserted.equals("")){
                                JOptionPane.showMessageDialog(null,"Senha inválida.");
                            }else if(tmp.equals("---------------Selecione--------------")){
                                JOptionPane.showMessageDialog(null,"Selecione o nome.");
                            }
                            else if(pass.equals( criptografia(passinserted))){
                                SimpleDateFormat sdf = new SimpleDateFormat("EEEE  dd/MM/yyyy  HH:mm");
                                Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
                                dataFormatada = sdf.format(hora);
                                final String concluido = "Registrado "+tmp+" "+dataFormatada;
                                
                                SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE dd-MM-yyyy HH-mm");
                                Date hora2 = Calendar.getInstance().getTime();
                                String dataFormatada2 = sdf2.format(hora2);
                                
                                String dirPath ="Camera/"+tmp;
                                Path dirPathObj = Paths.get(dirPath);
                                boolean dirExists = Files.exists(dirPathObj);
                                if(dirExists) {}
                                else{
                                     
                                    try {
                                        Files.createDirectories(dirPathObj);
                                    } catch (IOException ex) {
                                        //Logger.getLogger(Main32.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                        //System.out.println("! New Directory Successfully Created !");
                                }
                               
                                    JOptionPane.showMessageDialog(null,concluido);JPasswordField1.setText("");
                                    sq.setDados(tmp,dataFormatada);
                                    try {
                                         ImageIO.write(webcam.getImage(), "JPG", new File(dirPath+"/"+dataFormatada2+".jpg"));
                                    } catch (Exception ex) {
                                         System.out.println("Webcam indisponivel");
                                         try{
                                                File dir = new File(dirPath, dataFormatada2+".txt");
                                                dir.createNewFile();
                                                BufferedWriter writer = new BufferedWriter(new FileWriter(dir));
                                                writer.write("Webcam estava indisponivel no momento");
                                                writer.newLine();
                                                writer.write("Possívelmente desconectada ou sendo usada por outro programa");
                                                writer.flush();
                                                writer.close();
                                                
                                                
                                         }catch(Exception ew){}
                                    }                                   
                            }else{
                                JOptionPane.showMessageDialog(null,"Senha incorreta.");
                            }

                   
                        }
		});
               ///////////////////////////////////////////////////////////////////////////////////////
               //////////////////////////////////////////////////////
               ///////////////////////////
               //////////Ver dados
               ////
               JComboBoxVer.addActionListener (new ActionListener () {
                public void actionPerformed(ActionEvent e) {
                    
               
                    
                    try{
                    String dados = sq.getDados(JComboBoxVer.getSelectedItem().toString());
                    JTextAreaVD.setText(dados);
                    }catch(Exception er){}
                    }
                
                     
                });

                
               

        
        }////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
String criptografia(String msg){
  try{
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(msg.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
          hexString.append(String.format("%02X", 0xFF & b));
        }
        String senha = hexString.toString();
        //System.out.println(senha);
        return senha;
}catch(Exception ew){}   
return "";
}



boolean vefyifadmin(){

               JPasswordField password = new JPasswordField(10);
               JPanel entUsuario = new JPanel();
               entUsuario.add(password);
               JOptionPane.showMessageDialog(null, entUsuario, "Acesso restrito", JOptionPane.PLAIN_MESSAGE);
               String pass = new String(password.getPassword());
               if(pass.equals(senhaAdmin)){
                   return true;
               }

    return false;
}







}


/*
MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
byte messageDigest[] = algorithm.digest(original.getBytes("UTF-8"));
 
StringBuilder hexString = new StringBuilder();
for (byte b : messageDigest) {
  hexString.append(String.format("%02X", 0xFF & b));
}
String senha = hexString.toString();
*/

