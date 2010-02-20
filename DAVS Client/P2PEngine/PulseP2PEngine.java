package P2PEngine;

import DAVSPlayer.PlayStreams;
import RPCClient.ScriptResult;
import StreamingEngines.DAVSServer;
import RPCClient.davsClient;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import org.acplt.oncrpc.*;
/**
 *
 * @author waqasdaar
 */
public class PulseP2PEngine implements DAVSServer{

    private davsClient obj_RPC_Client;
    private InetAddress objDAVS_Host; // to store the server address.
    private ScriptResult objRPC_Result; // which stores the command result
    private PlayStreams objPlay;

     public PulseP2PEngine() {
             try {
                objDAVS_Host = InetAddress.getByName(DAVSServer.DAVS_Server);
                objPlay = new PlayStreams();
                obj_RPC_Client = new davsClient(objDAVS_Host,DAVSProgrameName , Version,OncRpcProtocols.ONCRPC_TCP);
                } catch (OncRpcException ex) {
                   Logger.getLogger(PulseP2PEngine.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                   Logger.getLogger(PulseP2PEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
     } 

     public int P2P_Validate (String MediaStream ) {
         int IsValidate=10;
        try {
            IsValidate = obj_RPC_Client.davs_p2p_validate_10(MediaStream);
        } catch (OncRpcException ex) {
            Logger.getLogger(PulseP2PEngine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PulseP2PEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
         return IsValidate;
     }

     public ScriptResult P2P_Import ( String MediaStream, int iBitRate,String VideoCodec, String AudioCodec) {
        try {
            objRPC_Result = obj_RPC_Client.davs_p2p_import_10(MediaStream, iBitRate, VideoCodec, AudioCodec);
        } catch (OncRpcException ex) {
            Logger.getLogger(PulseP2PEngine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PulseP2PEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objRPC_Result;
     }


     public int P2P_Start(String P2PMediaFile) {
         int IsStarted=10;
        try {
           IsStarted = obj_RPC_Client.davs_p2p_start_10(P2PMediaFile);
        } catch (OncRpcException ex) {
            Logger.getLogger(PulseP2PEngine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PulseP2PEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
         return IsStarted;
     }

     public String ParsePath (String Path) {
              StringTokenizer token= new StringTokenizer(Path,"\\");
			  StringBuffer newPath = new StringBuffer("");
			  String str;
	         while(token.hasMoreTokens())  {
             	 str=token.nextToken();
				  if(str.contains(" ")) {
					str = "\\\""+str+"\"\\";
  			       }  else{
			  	    str = str +"\\";
			  	   }
	     		  newPath.append(str);
	       }
       return newPath.toString();
     }

     public void P2P_Play (String P2PMediaStream) {
        try {

            String PulseOutFile=P2PMediaStream.substring(0,P2PMediaStream.lastIndexOf("."));
            PulseOutFile=PulseOutFile+".out";
            String Pulse=System.getProperty("user.dir")+"\\thirdparty\\pulse";
            String PulseStreamDirectory=ParsePath(System.getProperty("user.dir"))+"DAVSPulseStreams\\"+PulseOutFile;
            InetAddress ClientIp =InetAddress.getLocalHost();
            File objFile=new File(Pulse);
            int PORT=GetHttpPort();

            Runtime.getRuntime().exec("python.exe pulse.py -m client "+PulseStreamDirectory+" -c Config.ini --my-ip "+ClientIp.getHostAddress()+" --output-http="+Integer.toString(PORT)
                    ,null,objFile);
            Thread.sleep(2000);
            JOptionPane.showMessageDialog(null,"Now you can access the stream with \n"+"http://"+ClientIp.getHostAddress()+":"+PORT,
             "DAVS Message",JOptionPane.INFORMATION_MESSAGE);
            objPlay.DAVS_PlayP2PStreams("http://"+ClientIp.getHostAddress()+":"+PORT);
        } catch (InterruptedException ex) {
            Logger.getLogger(PulseP2PEngine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PulseP2PEngine.class.getName()).log(Level.SEVERE, null, ex);
        } 
}


     public int GetHttpPort () {
         int PORT=1044;
         int minPort=6000;
         int maxPort=10000;
         PORT=(int) (Math.random() * (maxPort - minPort + 1) ) + minPort;
         return PORT;
     }


     public int P2P_PulseStarted (String PulseStream) {
            int IsStarted = 10;
         try {
             String PulseOutFile=PulseStream.substring(0,PulseStream.indexOf("."));
             PulseOutFile=PulseOutFile+".out";
             IsStarted = obj_RPC_Client.davs_pulseOutAvailable_10(PulseOutFile);
        } catch (OncRpcException ex) {
            Logger.getLogger(PulseP2PEngine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PulseP2PEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IsStarted;
     }
     public int P2P_Stop (String MediaStream, String PulseMode) {
         int IsStop=10;
        try {
            IsStop = obj_RPC_Client.davs_p2p_stop_10(MediaStream, PulseMode);
        } catch (OncRpcException ex) {
            Logger.getLogger(PulseP2PEngine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PulseP2PEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
         return IsStop;
     }

}
