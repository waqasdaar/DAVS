
package DAVSPlayer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author waqasdaar
 */
public class PlayStreams {

    final String vlcCommand=System.getProperty("user.dir")+"/thirdparty/vlc/vlc ";
    final String RTSPURI="rtsp://192.168.188.41:5455/";

    public void DAVS_PlayVodStream (String streamID ) {
        try {
            Runtime.getRuntime().exec(vlcCommand.concat(streamID));
        } catch (IOException ex) {
            Logger.getLogger(PlayStreams.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void DAVS_PlayP2PStreams (String URI) {
        try {
            Runtime.getRuntime().exec(vlcCommand.concat(URI));
        } catch (IOException ex) {
            Logger.getLogger(PlayStreams.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void DAVS_PlayLiveStreams (String streamID ) {
        try {
            Runtime.getRuntime().exec(vlcCommand.concat(RTSPURI.concat(streamID)));
        } catch (IOException ex) {
            Logger.getLogger(PlayStreams.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
