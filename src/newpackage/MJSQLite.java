package newpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MJSQLite {
    
   Connection con = null;String Url = "jdbc:sqlite:ECENTRAL.db";
   
   public MJSQLite(){
                        try {
                            //establish connection with database
                            Class.forName("org.sqlite.JDBC");
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Main32.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                        try {
                            con = DriverManager.getConnection(Url);
                        } catch (SQLException ex) {
                            Logger.getLogger(Main32.class.getName()).log(Level.SEVERE, null, ex);
                        }
 
                            if(con!=null){
                                System.out.println("Connection established");
                            }
}
    
 public void onCreate() {


            try {
                Statement st=con.createStatement();
                st.executeUpdate("CREATE TABLE users(nome text,senha text,horae int,mine int,horas int,mins int)"); 
                st.executeUpdate("CREATE TABLE historico(id INTEGER PRIMARY KEY AUTOINCREMENT, nome text,dados text)");
            } catch (SQLException ex) {
                Logger.getLogger(Main32.class.getName()).log(Level.SEVERE, null, ex);
            }
}  
 
 public String[] getUsers() {
           Statement st=null;String [] nomesArray = null;
           try {
               st = con.createStatement();
           } catch (SQLException ex) {
               Logger.getLogger(MJSQLite.class.getName()).log(Level.SEVERE, null, ex);
           }
           System.out.println("getUsers...");
           ResultSet rs = null;
           try {
               rs = st.executeQuery("SELECT * FROM users");
           } catch (Exception ex) {
               
           }
           if(rs == null){return new String[] {}; }
           try {
               int i =0;
                while(rs.next()){
                   i++;
                }

                    nomesArray= new String[i];
                    i=0;
                    rs = st.executeQuery("SELECT * FROM users");
                    while(rs.next()){
                        nomesArray[i] =rs.getString("nome") ;
                        i++;
                    }

               return nomesArray;

               } catch (SQLException ex) {
               Logger.getLogger(MJSQLite.class.getName()).log(Level.SEVERE, null, ex);
           }
           return nomesArray;
   }
 
 
 
 
 
public void setUsers(String nome,String senha,int horaE,int minE,int horaS,int minS) {
       Statement st=null;


                        try {
                            con = DriverManager.getConnection(Url);
                        } catch (SQLException ex) {
                            Logger.getLogger(Main32.class.getName()).log(Level.SEVERE, null, ex);
                        }
       
       try {
           st = con.createStatement();
       } catch (SQLException ex) {
           Logger.getLogger(MJSQLite.class.getName()).log(Level.SEVERE, null, ex);
       }
       System.out.println("settUsers...");
       try {
           st.executeQuery("INSERT INTO users values('"+nome+"','"+senha+"','"+horaE+"','"+minE+"','"+horaS+"','"+minS+"' )");
       } catch (Exception ex) {
           //Logger.getLogger(MJSQLite.class.getName()).log(Level.SEVERE, null, ex);
       }
}                    
 

public void deleteUser(String nome){
       Statement st=null;
       try {
           st = con.createStatement();
       } catch (SQLException ex) {
           Logger.getLogger(MJSQLite.class.getName()).log(Level.SEVERE, null, ex);
       }
       System.out.println("Deleting Users...");
       try {
           st.executeUpdate("DELETE FROM users WHERE nome = '"+nome+"';");
       } catch (SQLException ex) {
           //Logger.getLogger(MJSQLite.class.getName()).log(Level.SEVERE, null, ex);
       }
}

public String getPass(String nome){
           Statement st=null;String [] nomesArray = null;
           try {
               st = con.createStatement();
           } catch (SQLException ex) {
               Logger.getLogger(MJSQLite.class.getName()).log(Level.SEVERE, null, ex);
           }
           ResultSet rs = null;
           try {
               rs = st.executeQuery("SELECT * FROM users WHERE nome = '"+nome+"'");
           } catch (Exception ex) {
               
           }
           try{
           if(rs == null){return ""; }
           else {return rs.getString("senha");}
           }catch(Exception e){}
           
           return "";
    
}


public String getDados(String nome){
           Statement st=null;String dados = "";
           try {
               st = con.createStatement();
           } catch (SQLException ex) {
               Logger.getLogger(MJSQLite.class.getName()).log(Level.SEVERE, null, ex);
           }
           ResultSet rs = null;
           try {
               rs = st.executeQuery("SELECT * FROM historico WHERE nome = '"+nome+"' ORDER BY id DESC;");
           } catch (Exception ex) {
               
           }
           try{
           if(rs == null){return ""; }
           else{
            while(rs.next()){
                  dados =dados + rs.getString("dados")+"\n";
            }
           
           
             return dados;
           }
           }catch(Exception e){}
           
           return "";
    
}

 
public void setDados(String nome,String dados) {
       Statement st=null;       
       try {
           st = con.createStatement();
       } catch (SQLException ex) {
           Logger.getLogger(MJSQLite.class.getName()).log(Level.SEVERE, null, ex);
       }
       System.out.println("settDados...");
       try {
           st.executeQuery("INSERT INTO historico values(null,'"+nome+"','"+dados+"')");
       } catch (Exception ex) {
           //Logger.getLogger(MJSQLite.class.getName()).log(Level.SEVERE, null, ex);
       }
}   




public boolean ifExistDB(){
      Statement st=null;
       try {
           st = con.createStatement();
       } catch (SQLException ex) {
           Logger.getLogger(MJSQLite.class.getName()).log(Level.SEVERE, null, ex);
       }
       ResultSet rs = null;
       try {
           rs = st.executeQuery("SELECT * FROM users");
       } catch (SQLException ex) {
       } 
       try {
           //System.out.println(rs.getString("nome"));
           if(rs.getString("nome").isEmpty()){return false;}
           else return true;
           
           
       } catch (Exception ex) {
           //Logger.getLogger(MJSQLite.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;
}
    
    
    
}
