package DAVSMediaCopy;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author waqasdaar
 */
public interface DAVS_SecureCopy
{

      final String UserName="waqasdaar";
      final String RemotrDirectory="/tmp/";
      final String DAVS_Host="192.168.188.41";
      final String ImageDirectory="/home/waqasdaar/Images/";
      final String PulseDirectory="/home/waqasdaar/DAVS/pulseStreams/";

      boolean SecureCopyFromLocalMachine (String strLocalFile);
      boolean SecureImageCopyFromDAVSServer(String ImagePath);
      boolean SecurePulseStreamFromDAVS(String PulseID);
      boolean CreateSnapshotDirectory ();
      boolean CreatePulseStreamDirectory();

      String ParsePath(String Path);
}
