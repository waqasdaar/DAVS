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

public class Live555 implements DAVSServer {

    private davsClient obj_RPC_Client;
    private InetAddress objDAVS_Host; // to store the server address.
    private ScriptResult objRPC_Result; // which stores the command result

  public Live555() {
             try {
                objDAVS_Host = InetAddress.getByName(DAVSServer.DAVS_Server);
                obj_RPC_Client = new davsClient(objDAVS_Host,DAVSProgrameName , Version,OncRpcProtocols.ONCRPC_TCP);
                } catch (OncRpcException ex) {
                   Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                   Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

  public int Live555_Validate (String MediaStream, int IsSnapshot) {
        int IsValidate = 10;
        try {
             IsValidate = obj_RPC_Client.davs_live555_validate_10(MediaStream,IsSnapshot);
        } catch (OncRpcException ex) {
            Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IsValidate;
     }

  public ScriptResult Live555_Import (int StreamingType, String MediaStream, int iBitRate,String VideoCodec, String AudioCodec) {
        try {
            objRPC_Result = obj_RPC_Client.davs_live555_import_10(StreamingType, MediaStream, iBitRate, VideoCodec, AudioCodec);
        } catch (OncRpcException ex) {
            Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RTSP.class.getName()).log(Level.SEVERE, null, ex);
        }
       return objRPC_Result;
    }

  public  int Live555_Deport (String StreamID) {
        int IsDeport=10;
        try {
            IsDeport = obj_RPC_Client.davs_rtsp_deport_10(StreamID);
        } catch (OncRpcException ex) {
            Logger.getLogger(Live555.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Live555.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IsDeport;
    }
 }
