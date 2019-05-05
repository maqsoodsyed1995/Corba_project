package package2;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import corbamodule.*;
import package2.*;

public class registryclassforserver1
{
	public static void main(String args[])
	{
		listernerforserver1 tl1=new listernerforserver1(4000);
  		Thread t1=new Thread(tl1);
  		t1.start();
  		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run()
			  {
				  if(server1impl.a.isEmpty())
				  {
					  
				  }
				  else
				  {
					  
					Set<String> setd= server1impl.a.keySet();
					Iterator itd=setd.iterator();
					while(itd.hasNext())
					{
						String s1=(String)itd.next();
							Set<String> setr =	server1impl.a.get(s1).keySet();
							Iterator itr =setr.iterator();
							while(itr.hasNext())
							{
								String s2=(String)itr.next();
									Set<String> sett =	server1impl.a.get(s1).get(s2).keySet();
									Iterator itt =sett.iterator();
									while(itt.hasNext())
									{
										String s3=(String)itt.next();
										server1impl.a.get(s1).get(s2).put(s3,"Available");
									}
							}
					}
					Set<String> seta = server1impl.d.keySet();
					Iterator ita=seta.iterator();
					while(ita.hasNext())
					{
						String ss1=(String)ita.next();
						server1impl.d.remove(ss1);
					}
					System.out.println("Database reset");
				  }
			  }
			}, 50*60*1000, 50*60*1000);
	
		   try{
		      // create and initialize the ORB
		      ORB orb = ORB.init(args, null);
		      // get reference to rootpoa & activate the POAManager
		      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		      rootpoa.the_POAManager().activate();
		      // create servant and register it with the ORB
		      server1impl helloImpl = new server1impl();
		      helloImpl.setORB(orb);
		      // get object reference from the servant
		       org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
		      common href = commonHelper.narrow(ref);
		      // get the root naming context
		      // NameService invokes the name service
		      org.omg.CORBA.Object objRef =
		          orb.resolve_initial_references("NameService");
		      // Use NamingContextExt which is part of the Interoperable Naming Service (INS) specification.
		      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

		      String name = "Hello1";
		      NameComponent path[] = ncRef.to_name( name );
		      ncRef.rebind(path, href);
		         System.out.println("HelloServer ready and waiting ...");
		        // wait for invocations from clients
		      orb.run();
		      
		    } 
		        catch (Exception e)
		   		{
		        System.err.println("ERROR: " + e);
		        e.printStackTrace(System.out);
		   		}
		          System.out.println("HelloServer Exiting ...");
		          
		                    
	} 
}



