import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private BufferedReader socketIn;
    private PrintWriter socketOut;
    private Socket aSocket;

    public Server(){
        try {
            serverSocket = new ServerSocket(8099);
            aSocket = serverSocket.accept();
            System.out.println("Server is now running.");
            socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            socketOut = new PrintWriter(aSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void runServer(){
        String line ="";

        try {

            while (true){
                line = socketIn.readLine();
                if (line == null){
                    break;
                }
                if (isPalindrome(line)){
                    line = line + " is a palindrome";
                }else{
                    line = line + " is not a palindrome";
                }
                socketOut.println(line);
            }
            socketIn.close();
            socketOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean isPalindrome(String str){
        // Pointers pointing to the beginning
        // and the end of the string
        int i = 0, j = str.length() - 1;

        // While there are characters toc compare
        while (i < j) {

            // If there is a mismatch
            if (str.charAt(i) != str.charAt(j))
                return false;

            // Increment first pointer and
            // decrement the other
            i++;
            j--;
        }

        // Given string is a palindrome
        return true;
    }

    public static void main(String [] args){
       Server myServer = new Server();
       myServer.runServer();
    }

}
