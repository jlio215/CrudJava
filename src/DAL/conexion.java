/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class conexion {
     // Configuración de la conexión
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String pass = "root1234";
        String bd = "funcionarioiu";
        String driver = "com.mysql.cj.jdbc.Driver";
        Connection cx = null;
        
      
        public Connection conectar(){
            try {
                Class.forName(driver);
                cx = DriverManager.getConnection(url+bd, user, pass);
                System.out.println("Conectado");
            } catch (Exception e) {
                System.out.println("No Conectado "+ e);
            }
            return cx;
        }
        
        public void desconectar() {
            try {
                cx.close();
            } catch (SQLException ex) {
                Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public int ejecutarSecuenciaSql( String sql){
            try {
                PreparedStatement psql = cx.prepareStatement(sql);
                psql.execute();
                return 1;
                
            } catch (SQLException e) {
                System.out.println(e);
                return 0;
            }
        }
        
        public ResultSet consultar ( String sql){
            try {
                PreparedStatement psql = cx.prepareStatement(sql);
                ResultSet respuesta = psql.executeQuery();
                return respuesta;
                
            } catch (SQLException e) {
                System.out.println(e);
                return null;
            }
        }
        
        public static void main(String[] args) {
        conexion conexion = new conexion();
        conexion.conectar();
    }
}
