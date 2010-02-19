#~ /bin/bash
# Import the Video file for Agnostic Video Server.
# Version 0.1
# Created by Waqas Daar (daar@kth.se)
# Date 7 Jan 2009 1:56 AM



LiveStream=""
BroadcastStream=""
NonBroadcastStream=""
IsVerbose="false"
BitRate=""
VCodec=""
ACodec=""
VideoOnDemand=""

Video_Codec=mpeg1video
Audio_Codec=mp2

Exit_ChkInt_OK=66
Exit_ChkInt_NO=99

path=`dirname $0`
source $path/config

function Check_Integer {
    if [ "`echo $1 | egrep ^[[:digit:]]+$`" = "" ]; 
      then
        return $Exit_ChkInt_NO
      else
        return $Exit_ChkInt_OK
     fi
}


function PrintURI  {
IP=$('/sbin/ifconfig' | grep 'inet addr:'| grep -v '127.0.0.1' | cut -d: -f2 | awk '{ print $1}')
echo "You can access the stream from the following URI"
echo  "  URI: 'rtsp://$IP:$PORT/$1'"
}
  
 if [ $# -eq 0 ]
   then
      echo "Usage: `basename $0` [ -l Live streaming ] [ -b Broadcast type streaming ] [ -d Video on Demand ]
                 [ -t Bit Rate ] [ -v Enable Verbose mode.] [ -g Video codec.] [ -a Audio codec.] [ -h help ]"
        exit 256
   else 
          
        cmdargument=$1
        if [ "${cmdargument:0:1}" != "-" ]
         then 
            echo "Usage: `basename $0` [ -l Live streaming ] [ -b  Broadcast type streaming ] [ -d Video on Demand ]
                 [ -t Bit Rate ] [ -v Enable Verbose mode.] [ -g Video codec.] [ -a Audio codec.] [ -h help ]"
            exit 256
         else
            
           while getopts ":l:b:d:n:c:t:g:a:hv" option
           do 
              case $option in
                l )   LiveStream=$OPTARG;;
                b )   BroadcastStream=$OPTARG;;
                d )   VideoOnDemand=$OPTARG;;
                t )   BitRate=$OPTARG;;
                v )   IsVerbose="true";;
                g )   VCodec=$OPTARG;;
                a )   ACodec=$OPTARG;;
                h )   echo "Usage: `basename $0` [ -l Live Streaming ] [ -b Broadcast streaming ] [ -d Video on Demand ]
                 [ -t Bit Rate ] [ -g Video codec.] [ -a Audio codec.] [ -v Verbose mode on ] [ -h help ]"
                      echo "  -l for Broadcast like Live streaming."
                      echo "  -b for Broadcast streaming."
                      echo "  -d for video on demand."
                      echo "  -t Set the bit rate in Kbits/sec, for example '`basename $0` -t 200'"
                      echo "  -g Set the video codec, for example '`basename $0` -g mpeg1video'"
                      echo "  -a Set the audio codec, for example '`basename $0` -a mp3'"
                      echo "  -v Enable verbose mode."
                      echo "  -h for Help."
                      exit 0;;
                  *)   echo "Usage: `basename $0` [ -l Live streaming ] [ -b Broadcast streaming ] [ -d Video on Demand ]
                 [ -t Bit Rate ] [ -g Video codec.] [ -a Audio codec.] [ -v Enable Verbose mode.] [ -h help ]"
                      exit 256;;
                esac
          done    

         # setting the audio and video codec variables

         if [[ $VCodec == "" ]]
             then 
               VCodec=$Video_Codec                           
         fi 
         
         if [[ $ACodec == "" ]]
             then 
               ACodec=$Audio_Codec
         fi         

         if [[ $LiveStream = "" && $BroadcastStream = "" && $VideoOnDemand = "" ]] 
          then 
             echo "Info: Please specify the streaming type."
             echo "Usage: `basename $0` [ -l Live streaming ] [ -b Broadcast streaming ] [ -d Video on Demand ]
                 [ -t Bit Rate ] [ -g Video codec.] [ -a Audio codec.]"
             exit 256
          else
              if [[ $BroadcastStream != "" ]] 
               then 
                    if [ -d $BroadcastStream ]
                       then
                              echo "Error: It seems to be a directory."
                              echo "    Example: `basename $0` -b /home/jhon/tune.mpeg"
                              exit 256;
                           else 
                                  if [ -r $BroadcastStream ]
                                     then
                                         FileName=`basename $BroadcastStream`
                                         ID=`head -c4 /dev/urandom | od -N4 -tu4 | sed -ne '1s/.* //p'`
                                         StreamID=${FileName%.*}$ID
		                          if [[ $BitRate != "" ]]
                                              then   
                                                  Check_Integer $BitRate
                                                    if [ $? -eq 66 ]
                                                      then
					  	     	AudioBitRate=64000
                			             	VideoBitRate=$(($BitRate*1000-$BitRate*100-$AudioBitRate))
                        	                     	PATH=$PATH:/home/waqasdaar/bin
                                                       
                                                      if [ "$IsVerbose" = "false" ]
                                                        then
                                                        ffmpeg -y -i $BroadcastStream -f mpeg -vcodec $VCodec -b $VideoBitRate -maxrate $VideoBitRate -minrate $VideoBitRate -bufsize 256k -acodec $ACodec -ab $AudioBitRate $Video/$StreamID.mpg 2>/dev/null
							if [ $? -eq 0 ]
        	                                    	     then 
                                                                StreamID=$StreamID.mpg
                                                                PrintURI $StreamID
		                                                exit 0
                	                            	     else
                                                               exit 256
                                	                    fi # end if ffmpeg executed successfuly.
                                                      else 
							Status=`ffmpeg -v 0 -y -i $BroadcastStream -f mpeg -vcodec $VCodec -b $VideoBitRate -maxrate $VideoBitRate -minrate $VideoBitRate -bufsize 256k -acodec $ACodec -ab $AudioBitRate $Video/$StreamID.mpg 2>&1| grep "frame="`
                                                         if [ $? -eq 0 ]
                                                             then
                                                                echo $Status  
                                                                StreamID=$StreamID.mpg
                                                                PrintURI $StreamID
                                                                exit 0
                                                             else
                                                                exit 256
                                                            fi # end if ffmpeg executed successfuly.
                                                    fi # end if IsVerbose is set to false.
                                                   else
                                                        echo "Error: Please sepcify the Bit Rate in an integer format."
                                                        echo "     example: `basename $0` -t 200 "
                                                        exit 256
                                                   fi  # end if Bit Rate is not integer
                                               else
                                                   StreamID=$StreamID.${FileName##*.}
                                                   cp $BroadcastStream $Video/$StreamID
                                                   echo "Message: File is imported into the engine."
                                                   PrintURI $StreamID
                                                   exit 0
                                          fi # end if Bit rate is not provided.
                                      else 
                                        echo "Error: File does not exist or may be its corrupted."
                                        exit 256;
                                 fi # end if file is readable or exist
                    fi # end if its a directory.        
               elif [[ $VideoOnDemand != "" ]]
                   then
                      if [ -d $VideoOnDemand ]
                       then
                              echo "Error: It seems to be a directory."
                              echo "    Example: `basename $0` -b /home/jhon/tune.mpeg"
                              exit 256;
                           else
                                  if [ -r $VideoOnDemand ]
                                     then
                                         FileName=`basename $VideoOnDemand`
                                         ID=`head -c4 /dev/urandom | od -N4 -tu4 | sed -ne '1s/.* //p'`
                                         StreamID=${FileName%.*}$ID
                                          if [[ $BitRate != "" ]]
                                              then
                                                  Check_Integer $BitRate
                                                    if [ $? -eq 66 ]
                                                      then
                                                        AudioBitRate=64000
                                                        VideoBitRate=$(($BitRate*1000-$BitRate*100-$AudioBitRate))
                                                        PATH=$PATH:/home/waqasdaar/bin

                                                      if [ "$IsVerbose" = "false" ]
                                                        then
                                                          ffmpeg -y -i $VideoOnDemand -f mpeg -vcodec $VCodec -b $VideoBitRate -maxrate $VideoBitRate -minrate $VideoBitRate -bufsize 256k -acodec $ACodec -ab $AudioBitRate $Video/$StreamID.mpg 2>/dev/null
                                                        if [ $? -eq 0 ]
                                                             then
                                                                StreamID=$StreamID.mpg
                                                                PrintURI $StreamID
                                                                exit 0
                                                             else
                                                               exit 256
                                                            fi # end if ffmpeg executed successfuly.
                                                           else
                                                              ffmpeg -y -i $VideoOnDemand -f mpeg -vcodec $VCodec -b $VideoBitRate -maxrate $VideoBitRate -minrate $VideoBitRate -bufsize 256k -acodec $ACodec -ab $AudioBitRate $Video/$StreamID.mpg 2>/dev/null
                                                         if [ $? -eq 0 ]
                                                             then
                                                                echo $Status
                                                                StreamID=$StreamID.mpg
                                                                PrintURI $StreamID
                                                                exit 0
                                                             else
                                                                exit 256
                                                            fi # end if ffmpeg executed successfuly.
                                                    fi # end if IsVerbose is set to false.
                                                   else
                                                        echo "Error: Please sepcify the Bit Rate in an integer format."
                                                        echo "     example: `basename $0` -t 200 "
                                                        exit 256
                                                   fi  # end if Bit Rate is not integer
                                                 else
                                                   StreamID=$StreamID.${FileName##*.}
                                                   cp $VideoOnDemand $Video/$StreamID
                                                   echo "Message: File is imported into the engine."
                                                   PrintURI $StreamID
                                                   exit 0
                                          fi # end if Bit rate is not provided.
                                      else
                                        echo "Error: File does not exist or may be its corrupted."
                                        exit 256;
                                 fi # end if file is readable or exist
                    fi # end if its a directory.
               else
                  if [ -d $LiveStream ]
                       then
                              echo "Error: It seems to be a directory."
                              echo "    Example: `basename $0` -b /home/jhon/tune.mpeg"
                              exit 256;
                      else
                           if [ -r $LiveStream ]
                                     then
                                         LiveStreamID=`basename $LiveStream`
                                         FileExtension=${LiveStreamID##*.}
                                         if [ "$FileExtension" = "sdp" -o "$FileExtension" = "SDP" ]
			                   then
                     				    cp $LiveStream $RTSP/
				                    echo "Message: SDP file is imported into the engine."
				                    PrintURI $LiveStreamID
				                    exit 0
                                           else
                                                    FileName=`basename $LiveStream`
                                                    ID=`head -c4 /dev/urandom | od -N4 -tu4 | sed -ne '1s/.* //p'`
                                                    StreamID=${FileName%.*}$ID
                                           if [[ $BitRate != "" ]]
                                              then
                                                  Check_Integer $BitRate
                                                    if [ $? -eq 66 ]
                                                      then
                                                        AudioBitRate=64000
                                                        VideoBitRate=$(($BitRate*1000-$BitRate*100-$AudioBitRate))
                                                        PATH=$PATH:/home/waqasdaar/bin

                                                      if [ "$IsVerbose" = "false" ]
                                                        then
                                                          ffmpeg -y -i $LiveStream -f mpeg -vcodec $VCodec -b $VideoBitRate -maxrate $VideoBitRate -minrate $VideoBitRate -bufsize 256k -acodec $ACodec -ab $AudioBitRate $RTSP/$StreamID.mpg 2>/dev/null
                                                        if [ $? -eq 0 ]
                                                             then
                                                                StreamID=$StreamID.mpg
                                                                PrintURI $StreamID
                                                                exit 0
                                                             else
                                                               exit 256
                                                            fi # end if ffmpeg executed successfuly.
                                                       else
                                                              ffmpeg -y -i $LiveStream -f mpeg -vcodec $VCodec -b $VideoBitRate -maxrate $VideoBitRate -minrate $VideoBitRate -bufsize 256k -acodec $ACodec -ab $AudioBitRate $RTSP/$StreamID.mpg 2>/dev/null
                                                         if [ $? -eq 0 ]
                                                             then
                                                                echo $Status
                                                                StreamID=$StreamID.mpg
                                                                PrintURI $StreamID
                                                                exit 0
                                                             else
                                                                exit 256
                                                            fi # end if ffmpeg executed successfuly.
                                                    fi # end if IsVerbose is set to false.
                                                   else
                                                        echo "Error: Please sepcify the Bit Rate in an integer format."
                                                        echo "     example: `basename $0` -t 200 "
                                                        exit 256
                                                   fi  # end if Bit Rate is not integer
                                                 else
                                                   StreamID=$StreamID.${FileName##*.}
                                                   cp $LiveStream $RTSP/$StreamID
                                                   echo "Message: File is imported into the engine."
                                                   PrintURI $StreamID
                                                   exit 0
                                          fi # end if Bit rate is not provided.
                                        fi
                                      else
                                        echo "Error: File does not exist or may be its corrupted."
                                        exit 256;
                                fi # end if file is readable or exist
                       fi # end if file is not SDP
                     fi # end if Live Stream is a directory.
              fi # end if Live Stream
         fi  # end if any of the streaming method is mentioned.                             
     fi # end if argument does not start with '-' 
   fi # end if argument length is zero
    


