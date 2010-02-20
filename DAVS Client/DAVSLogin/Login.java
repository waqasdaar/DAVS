package DAVSLogin;

import StreamingEngines.DAVSServer;
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
public class Login implements DAVSServer {
    private davsClient obj_RPC_Client;
    private InetAddress objDAVS_Host; // to store the server address.
    public  Login ()
            {
             try {
                objDAVS_Host = InetAddress.getByName(DAVSServer.DAVS_Server);
                obj_RPC_Client = new davsClient(objDAVS_Host,DAVSProgrameName , Version,OncRpcProtocols.ONCRPC_TCP);
                } catch (OncRpcException ex) {
                   Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                   Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }

       public int CheckLogin (String username, String password) {
        int isSuccess=10;
            try {
                isSuccess = obj_RPC_Client.davs_login_10(username, password);
            } catch (OncRpcException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        return isSuccess;
       }

       public String GetRole (String username) {
           String strRole="";
            try {
                strRole = obj_RPC_Client.davs_getrole_10(username);
            } catch (OncRpcException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
           return strRole;
       }
}
