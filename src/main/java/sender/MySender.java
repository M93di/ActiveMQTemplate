package sender;

import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.QueueConnectionFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySender {

	public static void main(String[] args) {
		
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			
			Queue queue = (Queue) applicationContext.getBean("queue");
			

			// Create a connection
			QueueConnection connection = factory.createQueueConnection();
			// Open a session without transaction and acknowledge automatic
			QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			// Start the connection
			connection.start();
			// Create a sender
			QueueSender sender = session.createSender(queue);
			// Create a message
			TextMessage msg = session.createTextMessage("TEST ENVOI");
			//System.out.println(msg);
			// Send the message
			sender.send(msg);
			// Close the session
			session.close();
			// Close the connection
			connection.close();


		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
