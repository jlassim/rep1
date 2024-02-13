# Ecrire un exemple UDP, Serveur et Client en Java.
## Serveur UDP :
`import java.net.*;

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
}`

## client UDP:
` import java.net.*;

public class UDPClient {
    public static void main(String args[]) throws Exception {
        // Création d'une socket UDP pour le client
        DatagramSocket clientSocket = new DatagramSocket();

        // Adresse IP du serveur
        InetAddress IPAddress = InetAddress.getByName("localhost");

        byte[] sendData;
        byte[] receiveData = new byte[1024];

        // Message à envoyer
        String sentence = "Hello, UDP Server!";
        
        // Conversion du message en tableau de bytes
        sendData = sentence.getBytes();

        // Création d'un paquet pour envoyer les données
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        
        // Envoi du paquet au serveur
        clientSocket.send(sendPacket);

        // Création d'un paquet pour recevoir les données
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        
        // Réception des données du serveur
        clientSocket.receive(receivePacket);
        
        // Extraction des données reçues
        String modifiedSentence = new String(receivePacket.getData());
        
        // Affichage des données reçues du serveur
        System.out.println("From Server: " + modifiedSentence);

        // Fermeture de la socket du client
        clientSocket.close();
    }
} `
# Ecrire un exemple TPC, Serveur et Client en Java. 
## serveur TPC:
`  import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String args[]) throws Exception {
        // Création d'une socket serveur TCP sur le port 6789
        ServerSocket welcomeSocket = new ServerSocket(6789);

        while (true) {
            // Accepter une connexion entrante
            Socket connectionSocket = welcomeSocket.accept();
            
            // Création de lecteurs et d'écrivains pour la communication avec le client
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            // Lecture de la chaîne envoyée par le client
            String clientSentence = inFromClient.readLine();
            
            // Affichage de la chaîne reçue du client
            System.out.println("Received from client: " + clientSentence);
            
            // Convertir la chaîne en majuscules
            String capitalizedSentence = clientSentence.toUpperCase() + '\n';
            
            // Envoyer la chaîne convertie en majuscules au client
            outToClient.writeBytes(capitalizedSentence);
        }
    }
} `
## client en TCP:
` import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String args[]) throws Exception {
        // Adresse IP du serveur et port auquel le client se connecte
        String serverAddress = "localhost";
        int serverPort = 6789;

        // Création d'une socket TCP pour le client
        Socket clientSocket = new Socket(serverAddress, serverPort);

        // Création de lecteurs et d'écrivains pour la communication avec le serveur
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Lecture de l'entrée utilisateur
        System.out.print("Enter a sentence to send to server: ");
        String sentence = inFromUser.readLine();

        // Envoi de la chaîne au serveur
        outToServer.writeBytes(sentence + '\n');

        // Lecture de la réponse du serveur
        String modifiedSentence = inFromServer.readLine();

        // Affichage de la réponse du serveur
        System.out.println("From Server: " + modifiedSentence);

        // Fermeture de la socket client
        clientSocket.close();
    }
} `
