package com.antonov.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.antonov.Message;

public class NetProcessor {

	private Socket socket;

	private Runnable runnable;
	private Thread thread;

	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;

	private OnMessageReceiveListener messageReceiveListener;

	public NetProcessor() {
		runnable = new Runnable() {
			@Override
			public void run() {
				while (socket.isConnected()) {
					try {
						Message receivedMessage = (Message) objectInputStream.readObject();
						if (messageReceiveListener != null)
							messageReceiveListener.messageReceived(receivedMessage);

					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};

	}

	public void connect(InetSocketAddress address) throws UnknownHostException, IOException {
		socket = new Socket(address.getAddress(), address.getPort());
		objectInputStream = new ObjectInputStream(socket.getInputStream());
		objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		thread = new Thread(runnable);
		thread.start();
	}
	
	public void close() throws IOException {
		socket.close();
	}
	
	public boolean isConnected() {
		return socket.isConnected();
	}

	public void send(Message message) throws IOException {
		objectOutputStream.writeObject(message);
	}

	public void setOnMessageReceivedListener(OnMessageReceiveListener messageReceiveListener) {
		this.messageReceiveListener = messageReceiveListener;
	}

	public interface OnMessageReceiveListener {
		public void messageReceived(Message message);
	}

}
