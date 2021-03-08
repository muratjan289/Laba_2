package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server
{
    private ServerSocket server;

    public Server(int port)
    {
        try
        {
            server = new ServerSocket(port);
            System.out.println("Сервер: Сервер запущен! (000)");
        }
        catch (Exception errorInfo)
        {
            System.out.println("Сревер: Сервер не запущен! (" + errorInfo + ")");
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void serverLogic()
    {
        try
        {
            while (true) {
                Socket client = server.accept();
                System.out.println("Сервер: Пользователь подключен! Порт: " + server.getLocalPort() + ".");

                BufferedReader readData = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter writeData = new PrintWriter(client.getOutputStream(), true);
                String dataParser;
                // String dataA;

                writeData.println("Сервер: Приветствуем на JavaEchoServer. Введите help для ознакомления с командами.");

                do {
                    dataParser = readData.readLine();

                    System.out.println("Клиент: Обращение к серверу: " + dataParser + ".");

                    if (!"".equals(dataParser) && !dataParser.contains("sendmsg")) {
                        switch (dataParser) {
                            case ("help") -> {
                                writeData.println("Сервер: Доступные команды: serverip, serverport, serverstatus, sendmsg, exit.");
                                dataParser = "";
                            }
                            case ("serverip") -> {
                                writeData.println("Сервер: Подключено к: " + client.getLocalAddress() + ".");
                                dataParser = "";
                            }
                            case ("serverport") -> {
                                writeData.println("Сервер: Порт подключения: " + client.getLocalPort() + ".");
                                dataParser = "";
                            }
                            case ("serverstatus") -> {
                                writeData.println("Сервер: Статус сервера: Подключено.");
                                dataParser = "";
                            }
                            default -> writeData.println("Сервер: Ошибка. Команда не найдена!");
                        }
                    } else if (dataParser.contains("sendmsg")) {
                        writeData.println(dataParser.replace("sendmsg", "Сервер: "));
                        dataParser = "";
                    } else {
                        writeData.println("Сервер: Вы ничего не ввели!");
                    }
                }
                while (!dataParser.trim().equals("exit"));
                client.close();
                System.out.println("Сервер: Пользователь отключен! (000)");
            }
        }
        catch (Exception errorInfo)
        {
            System.out.println("Сревер: Ошибка синхронизации! (" + errorInfo + ")");
        }
    }

    public static void main(String[] args)
    {
        Server serverActivate = new Server(9999);
        serverActivate.serverLogic();
    }
}
