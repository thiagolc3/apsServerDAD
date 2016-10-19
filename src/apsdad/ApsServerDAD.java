/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apsdad;

import aps.HibernateUtil;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author lsfo
 */
public class ApsServerDAD {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    private static final String QUERY = "from Mensagens";
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        int serverPort = 8000;
        ServerSocket listenSocket = new ServerSocket(serverPort);

        while (true) {
            Socket clientSocket = listenSocket.accept();
            
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            String data = (String) in.readObject();
            System.out.println(data);
            
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            out.writeUTF("mensagem recebida pelo servidor");
            
            ObjectOutputStream outObj = new ObjectOutputStream(clientSocket.getOutputStream());
            List lista = executeHQLQuery(QUERY);
            outObj.writeObject(lista);
        }
        
    }
//    private static void runQuery() {
//        executeHQLQuery(QUERY);
//    }

    private static List executeHQLQuery(String QUERY) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery(QUERY);
            List resultList = q.list();
            session.getTransaction().commit();
            return resultList;
    }
    
}
