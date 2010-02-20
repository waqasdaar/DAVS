package DAVSMediaCopy;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author waqasdaar
 *  DAVS Version 0.1
 */
public class SecureMediaTransfer implements DAVS_SecureCopy{

    String PulseStreamDirectory=null;
    String DAVSImageDirectory=null;
    
    public boolean CreateSnapshotDirectory ()
    {
       String path=System.getProperty("user.dir");
       DAVSImageDirectory=path.concat("\\DAVSSnapshot");
       File Imagedirectory= new File(DAVSImageDirectory);
       boolean IsCreated =Imagedirectory.mkdir();
       if(IsCreated) { return true;
       }  else { return false;}
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

    public boolean CreatePulseStreamDirectory()
    {
       String path=System.getProperty("user.dir");
       PulseStreamDirectory=path+"\\DAVSPulseStreams";
       File Imagedirectory= new File(PulseStreamDirectory);
       boolean IsCreated =Imagedirectory.mkdir();
       if(IsCreated) { return true;
       }  else { return false;}
    }
    
    public boolean SecureImageCopyFromDAVSServer (String StreamSnapshot)
    {
      boolean IsCopied=false;
      try {
         //\\thirdpartyscp\\
         String strPath=System.getProperty("user.dir")+"/lib/thirdpartyscp/pscp.exe ";
         String ImageFile=StreamSnapshot.substring(StreamSnapshot.lastIndexOf("/")+1,StreamSnapshot.length());
         File objFile= new File(DAVSImageDirectory+"/"+ImageFile);
         if(!objFile.exists()) {
             Runtime.getRuntime().exec(strPath+"-pw MasterThesis2oo9 -q waqasdaar@192.168.188.41:"+StreamSnapshot+" "+ParsePath(DAVSImageDirectory));
           //Runtime.getRuntime().exec("lib\\pscp.exe -pw MasterThesis2oo9 -q waqasdaar@192.168.188.41:"+StreamSnapshot+" "+ParsePath(DAVSImageDirectory));
           Thread.sleep(2000);
           IsCopied=true;
         }
	    } catch (InterruptedException ex) {
            Logger.getLogger(SecureMediaTransfer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
	     JOptionPane.showMessageDialog(null,e.toString(),"DAVS Error Message",JOptionPane.OK_OPTION);
	   }
        return IsCopied;
    }

    public boolean SecurePulseStreamFromDAVS(String PulseStream) {
        boolean IsCopied=false;
      try {
         String strPath=System.getProperty("user.dir")+"/lib/thirdpartyscp/pscp.exe ";
         String PulseName=PulseStream.substring(0,PulseStream.lastIndexOf("."));
         String PulseID=DAVS_SecureCopy.PulseDirectory.concat(PulseName.concat(".out"));
         Runtime.getRuntime().exec(strPath+"-pw MasterThesis2oo9 -q waqasdaar@192.168.188.41:"+PulseID+" "+ParsePath(PulseStreamDirectory));
         //Runtime.getRuntime().exec("lib\\pscp.exe -pw MasterThesis2oo9 -q waqasdaar@192.168.188.41:"+PulseID+" "+ParsePath(PulseStreamDirectory));
         Thread.sleep(2000);
         IsCopied=true;
	    } catch (InterruptedException ex) {
            Logger.getLogger(SecureMediaTransfer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
	     JOptionPane.showMessageDialog(null,e.toString(),"DAVS Error Message",JOptionPane.OK_OPTION);
	   }
        return IsCopied;
    }
    
    public boolean SecureCopyFromLocalMachine (String strLocalFile) {

         boolean IsSuccess=true;
       FileInputStream fis=null;
       try
       {
        JSch jsch=new JSch();
        Session session=jsch.getSession(DAVS_SecureCopy.UserName,DAVS_SecureCopy.DAVS_Host,22);
        UserInfo DAVS_UI= new DAVS_UserInfo();
        session.setUserInfo(DAVS_UI);
        session.connect();
        String command="scp -p -t "+DAVS_SecureCopy.RemotrDirectory.concat(GetRemoteFile(strLocalFile));
        Channel channel=session.openChannel("exec");
        ((ChannelExec)channel).setCommand(command);
        OutputStream out=channel.getOutputStream();
        InputStream in=channel.getInputStream();
        channel.connect();
        if(checkAck(in)!=0){
	      IsSuccess=false;
         }
        // send "C0644 filesize filename", where filename should not include '/'
        long filesize=(new File(strLocalFile)).length();
        command="C0644 "+filesize+" ";
        if(strLocalFile.lastIndexOf('/')>0){
        command+=strLocalFile.substring(strLocalFile.lastIndexOf('/')+1);
        }
        else{
         command+=strLocalFile;
        }
        command+="\n";
        out.write(command.getBytes()); out.flush();
        if(checkAck(in)!=0){
	     IsSuccess=false;
        }
        fis=new FileInputStream(strLocalFile);
        byte[] buf=new byte[1024];
        while(true){
         int len=fis.read(buf, 0, buf.length);
	     if(len<=0) break;
         out.write(buf, 0, len); //out.flush();
        }
       fis.close();
       fis=null;
       buf[0]=0;
       out.write(buf, 0, 1); out.flush();
       if(checkAck(in)!=0){
   	    IsSuccess=false;
       }
        out.close();
       }
       catch(Exception e){
          JOptionPane.showMessageDialog(null, e.toString());
        }
        return IsSuccess;
    }

    static int checkAck(InputStream in) throws IOException{
    int b=in.read();
    // b may be 0 for success,
    //          1 for error,
    //          2 for fatal error,
    //          -1
    if(b==0) return b;
    if(b==-1) return b;

    if(b==1 || b==2){
      StringBuffer sb=new StringBuffer();
      int c;
      do {
	c=in.read();
	sb.append((char)c);
      }
      while(c!='\n');
      if(b==1){ // error
	System.out.print(sb.toString());
      }
      if(b==2){ // fatal error
	System.out.print(sb.toString());
      }
    }
    return b;
  }

    private String GetRemoteFile  (String strLocastrLocalFile)  {
        File f = new File (strLocastrLocalFile);
        String strFileName=f.getName();
        return strFileName;
   }
 }
