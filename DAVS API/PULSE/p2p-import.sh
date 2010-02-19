#~ /bin/bash
# Import the Video file for Agnostic Video Server.
# Version 0.1
# Created by Waqas Daar (daar@kth.se)
# Date 7 Jan 2009 1:56 AM



BroadcastStream=""
NonBroadcastStream=""
IsVerbose="false"
BitRate=""
VCodec=""
ACodec=""

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


function CreatePulseFile {

CHUNKS=8
CSIZE=$(($BitRate * 128 / $CHUNKS))
CRATE=$(($CHUNKS + 2))
WINDOW=$((CRATE * 4))
FNAME=`basename $1`
cat <<PULSEStreamEND> $P2PStreams/${FNAME%.*}.pulse
[PulseStream]
name=`basename $1`
author=DAVS
comment=DAVS P2P PULSE stream.
tags=DAVS P2P stream
chunk_size=$CSIZE
chunk_rate=$CRATE
max_packet_lost=$(($WINDOW - $CHUNKS * 4))
epoch=2
half_window=$WINDOW
data_input_rate=$CHUNKS
PULSEStreamEND
}


if [ $# -eq 0 ]
   then
      echo "Usage: `basename $0` [ -p Pulse media stream ] [ -t Bit Rate ] 
           [ -v Enable Verbose mode.] [ -g Video codec.] [ -a Audio codec.] [ -h help ]"
        exit 256
   else 
          
        cmdargument=$1
        if [ "${cmdargument:0:1}" != "-" ]
         then 
            echo "Usage: `basename $0` [ -p Pulse media stream ] [ -t Bit Rate ] 
            [ -v Enable Verbose mode.] [ -g Video codec.] [ -a Audio codec.] [ -h help ]"
            exit 256
         else
            
           while getopts ":p:n:c:t:g:a:hv" option
           do 
              case $option in
                p )   BroadcastStream=$OPTARG;;
                t )   BitRate=$OPTARG;;
                v )   IsVerbose="true";;
                g )   VCodec=$OPTARG;;
                a )   ACodec=$OPTARG;;
                h )   echo "Usage: `basename $0` [ -p Pulse media stream ] [ -t Bit Rate ] 
                           [ -g Video codec.] [ -a Audio codec.] [ -v Verbose mode on ] [ -h help ]"
                      echo "  -p for PULSE media stream."
                      echo "  -t Set the bit rate in Kbits/sec, for example '`basename $0` -t 200'"
                      echo "  -g Set the video codec, for example '`basename $0` -g mpeg1video'"
                      echo "  -a Set the audio codec, for example '`basename $0` -a mp3'"
                      echo "  -v Enable verbose mode."
                      echo "  -h for Help."
                      exit 0;;
                  *)   echo "Usage: `basename $0` [ -p Pulse media stream ] [ -t Bit Rate ]
               [ -g Video codec.] [ -a Audio codec.] [ -v Enable Verbose mode.] [ -h help ]"
                      exit 256;;
                esac
          done    

          if [[ $VCodec == "" ]]
             then 
               VCodec=$Video_Codec                           
         fi 
         
         if [[ $ACodec == "" ]]
             then 
               ACodec=$Audio_Codec
         fi         

          if [[ $BroadcastStream = "" ]] 
          then 
             echo "Info: Please specify the streaming type."
             echo "Usage: `basename $0` [ -b Broadcast streaming ] [ -t Bit Rate ] 
                     [ -g Video codec.] [ -a Audio codec.]"
             exit 256
           else 

                if [ -d $BroadcastStream ]
                       then
                              echo "Error: It seems to be a directory."
                              echo "    Example: `basename $0` -b /home/jhon/tune.mpeg"
                              exit 256
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
                                                    ffmpeg -y -i $BroadcastStream -f mpeg -vcodec $VCodec -b $VideoBitRate -maxrate $VideoBitRate -minrate $VideoBitRate -bufsize 256k -acodec $ACodec -ab $AudioBitRate $P2PStreams/$StreamID.mpg 2>/dev/null
                                                   if [ $? -eq 0 ]
        	                                     then 
                                                       CreatePulseFile $StreamID.mpg
                                                       echo "Media stream has been imported into the PULSE streaming engine."
                                                       echo "Pulse Stream ID: '$StreamID.mpg'"
                                                       exit 0
                	                             else
                                                       exit 256
                        	                    fi # end if ffmpeg executed successfuly.
                                                else 
                                                   Status=`ffmpeg -v 0 -y -i $BroadcastStream -f mpeg -vcodec $VCodec -b $VideoBitRate -maxrate $VideoBitRate -minrate $VideoBitRate -bufsize 256k -acodec $ACodec -ab $AudioBitRate $P2PStreams/$StreamID.mpg 2>&1| grep "frame="`
                                                   if [ $? -eq 0 ]
                                                     then

                                                       CreatePulseFile $StreamID.mpg
                                                       echo "Media stream has been imported into the PULSE streaming engine." 
                                                       echo "Pulse Stream ID: '$StreamID.mpg'"
                                                       exit 0
                                                   else
                                                      echo "Error: There is some problem in importing the media stream."
                                                      exit 256
                                                  fi # end if ffmpeg executed successfuly
                                              fi # end if Verbose mode is off.
                                           else 
                                             echo "Error: Please sepcify the Bit Rate in an integer format."
                                             echo "     example: `basename $0` -t 200 "
                                             exit 256
                                         fi # end if BitRate is not in integer format                                          
                                    else 
                                             StreamID=$StreamID.${FileName##*.}
                                             cp $BroadcastStream $P2PStreams/$StreamID
                                             BitRate=200
                                             CreatePulseFile $StreamID
                                             echo "Media stream has been imported into the PULSE streaming engine."
                                             echo "Pulse Stream ID: '$StreamID'"
                                             exit 0
                                 fi # end if BitRate is specified.
                       else 
                          echo "Error: File does not exist or may be its corrupted."
                          exit 256;
                      fi # end if file is readable or exist
                   fi
             fi
        fi 
    fi


  
