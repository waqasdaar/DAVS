package StreamingEngines;

import RPCClient.ScriptResult;
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
public class RTSP implements DAVSServer {

    private davsClient obj_DAVSServer;
    private InetAddress objDAVS_Host; // to store the server address.
    private ScriptResult objRPC_Result; // which stores the command result

public RTSP () {
               try {
                objDAVS_Host = InetAddress.getByName(DAVSServer.DAVS_Server);
                obj_DAVSServer = new davsClient(objDAVS_Host,DAVSProgrameName , Version,OncRpcProtocols.ONCRPC_TCP);
                } catch (OncRpcException ex) {
                   Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                   Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
public int RTSP_Validate (String MediaStream, int IsSnapshot) {
        int IsValidate = 10;
        try {
             IsValidate = obj_DAVSServer.davs_rtsp_validate_10(MediaStream, IsSnapshot);
        } catch (OncRpcException ex) {
            Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IsValidate;
     }
    
public  ScriptResult RTSP_Import (int StreamingType, String MediaStream, int iBitRate,String VideoCodec, String AudioCodec) {
        try {
            objRPC_Result = obj_DAVSServer.davs_rtsp_import_10(StreamingType, MediaStream, iBitRate, VideoCodec, AudioCodec);
        } catch (OncRpcException ex) {
            Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
        }
       return objRPC_Result;
    }
    
public ScriptResult RTSP_Live_Start (String StreamID) {
        try {
            objRPC_Result=obj_DAVSServer.davs_rtsp_start_10(StreamID);
            } catch (OncRpcException ex) {
            Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objRPC_Result;
      }
    
public int RTSP_Live_Stop (String StreamID ) {
        int IsStop=10;
        try {
            IsStop = obj_DAVSServer.davs_rtsp_stop_10(StreamID);
        } catch (OncRpcException ex) {
            Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IsStop;
    }
    
public int RTSP_Deport (String StreamID) {
        int IsDeport=10;
        try {
            IsDeport = obj_DAVSServer.davs_rtsp_deport_10(StreamID);
        } catch (OncRpcException ex) {
            Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IsDeport;
    }
   
}
