#~ /bin/bash
# Remove the stream ID for Agnostic Video Server.
# Version 0.1
# Created by Waqas Daar (daar@kth.se)
# Date 21 Jan 2009 3:47 AM

StreamID="";
IsVerbose="flase";

path=`dirname $0`
source $path/config

  
 if [ $# -eq 0 ]
   then
      echo "Usage: `basename $0` [ -s Stream ID ] [ -v Enable Verbose mode. ] [ -h for help ]"
        exit 12
   else 
          
        cmdargument=$1
        if [ "${cmdargument:0:1}" != "-" ]
         then 
            echo "Usage: `basename $0` [ -s Stream ID ] [ -v Enable Verbose mode. ] [ -h for Help ]"
            exit 12
         else
            
           while getopts ":s:hv" option
           do 
              case $option in
                s )   StreamID=$OPTARG;;
                v )   IsVerbose="true";;
                h )   echo "Usage: `basename $0` [ -s Stream ID ] [-v Enable Verbose mode. ] [ -h for Help ]"
                      echo "  -s   Provided stream id will be removed from the engine."
                      echo "  -v   Enable verbose mode."
                      echo "  -h   For Help."
                      exit 12;;
                * )   echo "Usage: `basename $0` [ -s Stream ID ] [ -v Enable Verbose mode. ] [ -h for help. ]"
                      exit 12;;
                esac
          done    

   if [ "$StreamID" = "" ]
       then
           echo "Error: Please provide stream id."
           echo "     Example : `basename $0` -s streamid"
           exit 12            
       else
            StreamID=`basename $StreamID`
          
            if [ -e $LIVE555/$StreamID ]
              then
                  if [ "$IsVerbose" = "true" ]
                     then
                        rm -f $LIVE555/$StreamID
                        echo "Message: '$StreamID' has been removed from the engine."
                        exit 0
                     else
                        rm -f $LIVE555/$StreamID
                        exit 0
                  fi # end if IsVerbose mode is enable.
             elif [ -e $Video/$StreamID ]
                 then
                   if [ "$IsVerbose" = "true" ]
                     then
                        rm -f $Video/$StreamID
                        echo "Message: '$StreamID' has been removed from the engine."
                        exit 0
                     else
                        rm -f $Video/$StreamID
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
            fi # end if Stream ID is exist. 

      fi # end if Stream id is not provided.
     fi # end if argument does not start with '-' 
   fi # end if argument length is zero
  fi    
fi
