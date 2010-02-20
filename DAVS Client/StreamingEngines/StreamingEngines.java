package StreamingEngines;

import RPCClient.davsClient;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.acplt.oncrpc.*;

/**
 *
 * @author waqasdaar
 */
public class StreamingEngines implements DAVSServer {

     private davsClient  obj_DAVSServer;
     private InetAddress objDAVS_Host; // to store the server address.
     public  String      DAVSStreamingEngines;

    public StreamingEngines() {
            try {
                 objDAVS_Host = InetAddress.getByName(DAVSServer.DAVS_Server);
                 obj_DAVSServer = new davsClient(objDAVS_Host,DAVSProgrameName , Version,OncRpcProtocols.ONCRPC_TCP);
                } catch (OncRpcException ex) {
                   Logger.getLogger(StreamingEngines.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                   Logger.getLogger(StreamingEngines.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

public String GetStreamingEngines () {
        try {
            DAVSStreamingEngines = obj_DAVSServer.davs_GetStreamingEngines_10();
        } catch (OncRpcException ex) {
            Logger.getLogger(StreamingEngines.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StreamingEngines.class.getName()).log(Level.SEVERE, null, ex);
        }
         return DAVSStreamingEngines;
     }

 public int StreamAvailable (String StreamID ) {
        int IsAvailable=10;
        try {
            IsAvailable = obj_DAVSServer.davs_streamavailable_10(StreamID);
        } catch (OncRpcException ex) {
            Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IsAvailable;
    }
}
