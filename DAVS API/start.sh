#~/bin/bash

path=`dirname $0`
source $path/config


StreamID=""
ServerIP=""
IsVerbose="false"

function GetServerIP  {
IP=$('/sbin/ifconfig' | grep 'inet addr:'| grep -v '127.0.0.1' | cut -d: -f2 | awk '{ print $1}')
ServerIP=$IP
}

function CleanUP {
 rm -f $1
}

 if [ $# -eq 0 ]
   then
      echo "Usage: `basename $0` [ -s Stream ID ] [ -v Enable verbose mode.] [ -h help ]"
      exit -1
   else
 

        cmdargument=$1
        if [ "${cmdargument:0:1}" != "-" ]
         then
             echo "Usage: `basename $0` [ -s Stream ID ] [-v Enable verbose mode. ] [ -h help ]"
             exit -1;
         else
             
             while getopts ":s:hv" option
           do
              case $option in
                s )   StreamID=$OPTARG;;
                v )   IsVerbose="true";;
                h )   echo "Usage: `basename $0` [ -s Stream ID ] [ -v Enable verbose mode. ] [ -h help ]"
                      echo "  -s Notify the streaming engine to start the stream."
                      echo "  -v Enable verbose mode."
                      echo "  -h for Help."
                      exit -1;;
                *)    echo "Usage: `basename $0` [ -s Stream ID ] [ -v Enable verbose mode. ] [ -h help ]"
                      exit -1;;
                esac
          done

           if [[ $StreamID = "" ]]
              then
                echo "Error: Please provide Stream ID."
                echo "Usage: `basename $0` [ -s Stream ID ] [-v Enable verbose mode. ] [ -h help ]"
                exit -1;
             else

             StreamName=`basename $StreamID`
             StreamIDName=${StreamName%.*}
             TempFile=`mktemp -t $StreamIDName.XXXXXX`

             PATH=$PATH:/home/waqasdaar/bin/
              
             if [ -e $RTSP/$StreamID ]
               then
                     
                     GetServerIP # Function to retrive server IP address.
                     if [ "$IsVerbose" = "false" ]
                     then
			ffmpeg -re -i $RTSP/$StreamID -vcodec mpeg4 -an -f rtp rtp://$ServerIP:$RTP_Video -vn -acodec mp2 -f rtp rtp://$ServerIP:$RTP_Audio -newaudio 2>/dev/null 1>$TempFile &
                        sleep 5
                        sed -e '1d' $TempFile 1>$RTSP/$StreamIDName.sdp
                        echo "You can access the stream from the following URI"
                        echo  "  URI: 'rtsp://$ServerIP:$PORT/$StreamIDName.sdp'"
                        CleanUP $TempFile
                        exit 0
                     else
                        ffmpeg -re -i $RTSP/$StreamID -vcodec mpeg4 -an -f rtp rtp://$ServerIP:$RTP_Video -vn -acodec mp2 -f rtp rtp://$ServerIP:$RTP_Audio -newaudio 2>/dev/null 1>$TempFile &
                        sleep 5
                        sed -e '1d' $TempFile 1>$RTSP/$StreamIDName.sdp
                        cat $RTSP/$StreamIDName.sdp
                        echo "You can access the stream from the following URI"
                        echo  "  URI: 'rtsp://$ServerIP:$PORT/$StreamIDName.sdp'"
                        CleanUP $TempFile
                        exit 0
                    fi # end if IsVerbose mode is enable.
              else
                    if [ "$IsVerbose" = "true" ]
                     then
                        echo "Message: '$StreamID' is not available in the engine."
                        exit 1
                     else
                        exit 1
                   fi # end if IsVerbose mode is enable.
            fi # end if Stream exist.
          fi # end if stream id is not provided.
        fi
     fi  

