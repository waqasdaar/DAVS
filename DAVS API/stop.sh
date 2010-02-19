#~/bin/bash


status=23
path=`dirname $0`
source $path/config

if [ $# -eq 0 ]
   then
      echo "Usage: `basename $0` [ -s Stream ID ] [ -h help ]"
      exit -1
   else


        cmdargument=$1
        if [ "${cmdargument:0:1}" != "-" ]
         then
             echo "Usage: `basename $0` [ -s Stream ID ] [ -h help ]"
             exit -1;
         else

             while getopts ":s:h" option
           do
              case $option in
                s )   StreamID=$OPTARG;;
                h )   echo "Usage: `basename $0` [ -s Stream ID ] [ -h help ]"
                      echo "  -s Notify the streaming engine to stop the stream."
                      echo "  -h for Help."
                      exit -1;;
                *)    echo "Usage: `basename $0` [ -s Stream ID ] [ -h help ]"
                      exit -1;;
                esac
          done

            
         while true;
              do
                 ProcessID=`ps -e x | grep "ffmpeg[[:space:]]-re[[:space:]]-i[[:space:]]$RTSP/$StreamID" | awk '{ print $1 }'`
                 if [ -z $ProcessID  ]
                  then
                      break;
                  else
                      kill -9 $ProcessID
                      status=0
                 fi
              done
            if [ $status -eq 0 ]
            then
                 exit 0;             
            else
                 exit 1;
            fi
     fi 
  fi
