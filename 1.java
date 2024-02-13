import java.net.*;

public class UDPServer {
    public static void main(String args[]) throws Exception {
        // Création d'une socket UDP
        DatagramSocket serverSocket = new DatagramSocket(9876);

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        while (true) {
            // Création d'un paquet pour recevoir les données
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            
            // Réception des données
            serverSocket.receive(receivePacket);
            
            // Extraction des données reçues
            String sentence = new String(receivePacket.getData());
            
            // Affichage des données reçues
            System.out.println("Received: " + sentence);
            
            // Extraction de l'adresse IP et du port du client
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            
            // Traitement des données (par exemple, convertir en majuscules)
            String capitalizedSentence = sentence.toUpperCase();
            
            // Conversion des données traitées en tableau de bytes
            sendData = capitalizedSentence.getBytes();
            
            // Création d'un paquet pour envoyer les données traitées
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            
            // Envoi des données traitées
            serverSocket.send(sendPacket);
        }
    }
}
