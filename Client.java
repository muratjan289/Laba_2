
import java.io.*;
import java.net.Socket;


public class Client
{
    public static void main(String[] args)
    {
        try
        {
            Socket socket = new Socket("127.0.0.1", 9999);
            System.out.println("Клиент: Вы подключены к серверу! Данные синхронизированы! (000)");

            BufferedReader readData = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writeData = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader rewriteData = new BufferedReader(new InputStreamReader(System.in));
            String dataParser;

            do
            {
                dataParser = readData.readLine();

                if (dataParser != null)
                    System.out.println(dataParser);

                dataParser = rewriteData.readLine();
                writeData.println(dataParser);
            }
            while (!dataParser.trim().equals("exit") );
            System.out.println("Клиент: Вы отключены от сервера! (000)");
        }
        catch (Exception errorInfo)
        {
            System.out.println("Сревер: Ошибка синхронизации! (" + errorInfo + ")");
        }
    }
}
