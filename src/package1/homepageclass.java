package package1;
import java.io.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import corbamodule.common;
import corbamodule.commonHelper;
import package2.*;
import package3.*;
import package4.*;

import java.lang.*;

public class homepageclass {
	static common commonimpl1,commonimpl2,commonimpl3;
	 public static void main(String args[]) throws Exception 
	{
		String ID;
		char a;
		int i;
		studentclass s;
		adminclass ad;	
		ORB orb = ORB.init(args, null);
        // get the root naming context
       org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
       // Use NamingContextExt instead of NamingContext. This is part of the Interoperable naming        	Service.  
       NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
       String name1 = "Hello1";
       commonimpl1 = commonHelper.narrow(ncRef.resolve_str(name1));
    
       String name2 = "Hello2";
       commonimpl2 = commonHelper.narrow(ncRef.resolve_str(name2));
       String name3 = "Hello3";
       commonimpl3 = commonHelper.narrow(ncRef.resolve_str(name3));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        while(true)
        {	
        	System.out.println("Enter 1 to use the Application Enter 2 to exit");
        	
			i=Integer.parseInt(br.readLine());
           if((i==1||i==2))
           {
        	  
        	   
        	 
           
			switch(i)
			{    
				case 1:
				  
				{
					System.out.println("Enter the User ID");
		
					ID=br.readLine();
					if((ID.length()==9) &&((ID.substring(0,3).equals(new String("DVL")) || ID.substring(0,3).equals(new String("KKL")) || ID.substring(0,3).equals(new String("WST")))) && (ID.substring(4,9).matches("[0-9][0-9][0-9][0-9][0-9]")))
					{
							if(ID.charAt(3)=='S')
							{
						
								s=new studentclass();
								s.student(ID,commonimpl1,commonimpl2,commonimpl3);
							}

							else if(ID.charAt(3)=='A')
							{
									ad=new adminclass();
									ad.admin(ID,commonimpl1,commonimpl2,commonimpl3);
							}
							else 
							{
								System.out.println("Enter a valid User ID");
							}
					}
							 else
				   {
						System.out.println("please provide a valid user id ");
				        
				   }
					break;
				}
				case 2:
				
				{	System.exit(0);
					break;
				}
			}
        }
           else
           {
        	   System.out.println("enter the correct input");
           }
	}
	
}
}