package com.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class client4150 extends Activity implements OnClickListener{
	int ServerPort = 4150;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(this);
        
        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
	        case R.id.button1:
	          sendMessage("boxee");
	          break;
	        case R.id.button2:
	          sendMessage("utorr");
	          break;
	         default:
	        	 break;
      }
	}
	
	public void sendMessage(String msg){
		
		TextView t1 = (TextView) findViewById(R.id.textView1);
		t1.setText(msg);
		
		DatagramSocket s;
		try {
			s = new DatagramSocket(ServerPort);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			t1.setText("error1");
			return;
			
		}
		
		InetAddress local;
		try {
			local = InetAddress.getByName("192.168.10.58");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			t1.setText("error2");
			return;
		}
		
		int msg_length=msg.length();
		byte[] messageBytes = msg.getBytes();
		DatagramPacket p = new DatagramPacket(messageBytes, msg_length, local, ServerPort);
		
		try {
			s.send(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			t1.setText("error3");
			return;
		}
	}
}