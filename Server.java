import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Server{
    private ServerSocket serverSocket;
    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    public void startServer(){
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("A new Client has Connected!!");
                ClientHandler clientHandler = new ClientHandler(socket);
                
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }catch(IOException e){
            System.out.println("Error : "+e);
        }

    }

    public void closeServerSocket(){
        try{
            if(serverSocket != null){
                serverSocket.close();
            }
        }catch(IOException e){
            System.out.println("Error :- "+e);
        }
    }

    public static void main(String ar[]){
        try{
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startServer();
        }catch(Exception e){
            System.out.println("Error : "+e);
        }
    }
}