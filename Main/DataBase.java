import java.sql.*;

public class DataBase {
    
    private Connection conn;
    private String url;

    public DataBase(String address, int ip,String dbname){
        String driver = "com.mysql.cj.jdbc.Driver";
        String username = "root";
        String password = "";
        this.url = "jdbc:mysql://"+ address + ":" + ip + "/" + dbname;
        this.conn = null;
    }

    public void open() throws Exception{
        this.conn = DriverManager.getConnection(this.url);
    }

    public void close() throws Exception{
        this.conn.close();
    }

    public void register(String name, String surname, String user, String password) throws Exception{
        PreparedStatement pst = this.conn.prepareStatement("insert into test(name,surname,user,pass) value(?,?,?,?)");    //Inserisci nella tabella test i campi... (tanti ? quanti i campi)
        pst.setString(1, name);
        pst.setString(2, surname);
        pst.setString(3, user);
        pst.setString(4, password);
        pst.executeUpdate();                //Inserisce tutti i campi nella tabella test
    }

    public boolean login(String user, String psw) throws Exception{
        PreparedStatement pst = this.conn.prepareStatement("select name from test where user = ? and pass = ?");   // SI USA QUANDO SI HANNO ELEMENTI IN INGRESSO, SE NO SOLO LO STATEMENT
        pst.setString(1, user);
        pst.setString(2, psw);
        ResultSet rs = pst.executeQuery();      //Perchè dobbiamo eseguire una select (query) non un insert
        return rs.next();                       
    }

    public String getName(String user) throws Exception{
        PreparedStatement pst = this.conn.prepareStatement("select name from test where user = ?");
        pst.setString(1, user);
        ResultSet rs = pst.executeQuery();
        rs.next();                              //prima riga intestazione, seconda quello che vogliamo
        return rs.getString("name");            //se c'è il risultato ritornerà ciò che contiene name se no ritorna null
    }

}
