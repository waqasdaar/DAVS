
package DAVSDatabaseServer;

import RPCClient.*;
import StreamingEngines.DAVSServer;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.acplt.oncrpc.OncRpcException;
import org.acplt.oncrpc.OncRpcProtocols;

/**
 *
 * @author waqasdaar
 */
public class DAVSDatabase implements DAVSServer{

    private davsClient obj_RPC_Client;
    private InetAddress objDAVS_Host;
    


    public DAVSDatabase() {
              try {
    
                objDAVS_Host = InetAddress.getByName(DAVSServer.DAVS_Server);
                obj_RPC_Client = new davsClient(objDAVS_Host,DAVSProgrameName , Version,OncRpcProtocols.ONCRPC_TCP);
                } catch (IOException ex) {
                     Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
                } catch (OncRpcException ex) {
                     Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
           }

    public int DAVS_Store_Broadcast (String MediaStreamName,String Description) {
        int IsStore=10;
        try {
            IsStore = obj_RPC_Client.davs_rtsp_broadcast_10(MediaStreamName, Description);
        } catch (OncRpcException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IsStore;
    }

    public int DAVS_Store_VOD (String MediaStreamName,String Description,String StreamID, String ImageURI, String URI) {
        int IsStore=10;
        try {
            IsStore = obj_RPC_Client.davs_rtsp_vod_10(MediaStreamName, Description, StreamID, ImageURI,URI);
        } catch (OncRpcException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IsStore;
    }

    public String DAVS_Get_VODStreams () {
        String VODStreams=null;
        try {
            VODStreams = obj_RPC_Client.davs_GetVodStreams_10();
        } catch (OncRpcException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return VODStreams;
    }

    public String DAVS_Get_LiveStreams () {
        String LiveStreams=null;
        try {
            LiveStreams = obj_RPC_Client.davs_GetBroadcast_Streams_10();
        } catch (OncRpcException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LiveStreams;
    }
    
    public int DAVS_RemoveVODStreams (String StreamID ) {
        int IsRemoved=10;
        try {
            IsRemoved = obj_RPC_Client.davs_rtsp_RemoveVODStream_10(StreamID);
        } catch (OncRpcException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IsRemoved;
    }

    public int DAVS_RemoveLiveStreams (String StreamID ) {
        int IsRemoved=10;
        try {
             IsRemoved = obj_RPC_Client.davs_rtsp_RemoveLiveStream_10(StreamID);
        } catch (OncRpcException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IsRemoved;
    }

    public String DAVS_GetURI (String StreamID ) {
        String URL=null;
        try {
            URL = obj_RPC_Client.davs_GetStreamURI_10(StreamID);
        } catch (OncRpcException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return URL;
    }

    public String DAVS_GetSnapshot (String StreamID) {
        String ImagePath=null;
        try {
            ImagePath = obj_RPC_Client.davs_GetSnapshot_10(StreamID);
        } catch (OncRpcException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ImagePath;
    }

    public String DAVS_GetLiveStreamDescription (String StreamID) {
          String Description=null;
        try {
            if(StreamID!=null)
            Description=obj_RPC_Client.davs_GetBroadcastStream_Description_10(StreamID);
        } catch (OncRpcException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Description;
    }

    public String DAVS_GetVODStreamDescription (String StreamID) {
         String Description=null;
        try {
            if(StreamID!=null)
            Description = obj_RPC_Client.davs_GetVodStream_Description_10(StreamID);
        } catch (OncRpcException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DAVSDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Description;
    }
}
