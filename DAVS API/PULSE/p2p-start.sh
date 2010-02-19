#~/bin/bash
# Start PULSE in source mode for Distribution Agnostic Video Server.
# Version 0.1
# Created by Waqas Daar (daar@kth.se)
# Date 3 April 2009 6:30 PM


PulseStream=""
IsVerbose="false"

path=`dirname $0`
source $path/config

if [ $# -eq 0 ]
then
  echo "Usage: `basename $0` [-v Enable verbose mode. ] [ -p /Pulse/file/stream ] [ -h for Help]"
else
        cmdargument=$1
        if [ "${cmdargument:0:1}" != "-" ]
         then
            echo "Usage: `basename $0` [-v Enable verbose mode. ] [ -p /Pulse/file/stream ] [ -h for Help]"
            exit 256
         else

          while getopts ":vp:h" option;
           do

                case $option in
                         v) IsVerbose="true" ;;
                         p) PulseStream=$OPTARG;;
                         h) echo "Usage: `basename $0` [-v Enable verbose mode. ] [ -p /Pulse/file/stream ] [ h for Help ]"
                            echo "  -v :   Enable the verbose mode."
                            echo "  -p :   Specify the PULSE P2P media streame path."
                            echo "  -h :   for Help"
                            exit 0;;
                        *)  echo "Usage: `basename $0` [-v Enable verbose mode.] [ -p /Pulse/file/stream ] [ -h for Help ]"
                            exit 0;;

                esac
        done
        shift $(($OPTIND-1))


               if [ -z $PulseStream ]
                 then
                            echo "Error: P2P PULSE stream  must be specified."
                            echo "     example: `basename $0` -f /home/videos/jhon/song.mpeg"
                            exit 2
                 else
                      
                      if [ -d $PulseStream ]
                        then
                          echo "Error: It seems to be a directory."
                          echo "    example:  `basename $0` -f /home/jhon/videos/tune.mpeg "
                          exit -1
                     else
                           if [ "$IsVerbose" = "true" ]
                             then
                               PULSESTREAM=`basename $PulseStream`
                               exec -a DAVS-PULSE-Source-$PULSESTREAM python2.4 $PULSE/pulse.py -f $P2PStreams/$PULSESTREAM $P2PStreams/${PULSESTREAM%.*}.pulse -c $PULSE/Config.ini --my-ip $IP --pulse_file_out=$P2PStreams/${PULSESTREAM%.*}.out &> /dev/null &
                               echo "Message: PULSE source is started successfully."
                               exit 0
                             else
	                          PULSESTREAM=`basename $PulseStream`
				  exec -a DAVS-PULSE-Source-$PULSESTREAM python2.4 $PULSE/pulse.py -f $P2PStreams/$PULSESTREAM $P2PStreams/${PULSESTREAM%.*}.pulse -c $PULSE/Config.ini  --my-ip $IP --pulse_file_out=$P2PStreams/${PULSESTREAM%.*}.out &> /dev/null &
                                  exit 0
                         fi # end if verbose mode is true
     fi # end if P2P stream is directory
   fi # end if P2P stream is not specified
  fi # if argument start with '-'
fi # end if arguemtn length is greater than zero.


