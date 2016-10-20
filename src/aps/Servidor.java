/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps;

import aps.HibernateUtil;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author lsfo
 */
public class Servidor extends Thread {

    private static final String QUERY = "from Mensagens";
    Socket clientSocket;

    public Servidor(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        ObjectInputStream in = null;
        try {
            
            in = new ObjectInputStream(clientSocket.getInputStream());
            String data = (String) in.readObject();
            System.out.println(data);
            
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            out.writeUTF("mensagem recebida pelo servidor");
            
            ObjectOutputStream outObj = new ObjectOutputStream(clientSocket.getOutputStream());
            List lista = executeHQLQuery(QUERY);
            outObj.writeObject(lista);
        
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
        private static List executeHQLQuery(String QUERY) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery(QUERY);
            List resultList = q.list();
            session.getTransaction().commit();
            return resultList;
    }
}
