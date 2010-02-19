#~ /bin/bash
# Start PULSE in client mode for Distribution Agnostic Video Server.
# Version 0.1
# Created by Waqas Daar (daar@kth.se)
# Date 3 April 2009 6:30 PM




TCP_Port=
UDP_Port=
IsVerbose="false"
PulseStream=""

path=`dirname $0`
source $path/config



if [ $# -eq 0 ]
   then
        echo "Usage: `basename $0` [ -p Pulse stream file ] [ -h help ] [ -v Verbose mode ]"
        exit 256
   else

        cmdargument=$1
        if [ "${cmdargument:0:1}" != "-" ]
         then
            echo "Usage: `basename $0` [ -p Pulse stream file ] [ -h help ] [ -v Verbose mode ]"
            exit 256
         else

           while getopts ":p:hv" option
           do
              case $option in
                         v) IsVerbose="true" ;;
                         p) PulseStream=$OPTARG;;
                         h) echo "Usage: `basename $0` [-v Enable verbose mode. ] [ -p /Pulse/file/stream ] [ h for Help ]"
                            echo "  -v :   Enable the verbose mode."
                            echo "  -p :   Specify the PULSE P2P streame."
                            echo "  -h :   for Help"
                            exit 0;;
                        *)  echo "Usage: `basename $0` [-v Enable verbose mode.] [ -p /Pulse/file/stream ] [ -h for Help ]"
                            exit 0;;
              esac
          done
            
           if [[ $PulseStream = "" ]]
          then
             echo "Info: Please specify the PULSE Stream."
             echo "Usage: `basename $0` [ -p Pulse stream file ] [ -h Help. ] [ -v Verbose mode ]"
             exit 256
           else
                  if [ -d $PulseStream ]
                        then
                          echo "Error: It seems to be a directory."
                          echo "    example:  `basename $0` -p tune.avi "
                          exit 12
                     else
                             if [ "$IsVerbose"="true" ]
                             then                              
                               RandomNumber=`echo $RANDOM%900+1 | bc`
                               TCP_Port=$(($RandomNumber+$TCPPORT))
                               UDP_Port=$(($RandomNumber+1+$UDPPORT))
                               HTTP_Port=$(($RandomNumber+$RandomNumber+$HTTPPORT))
                               PulseSTREAM=`basename $PulseStream`
                               exec -a DAVS-PULSE-Client-$PulseSTREAM python2.4 $PULSE/pulse.py -m client $P2PStreams/${PulseSTREAM%.*}.out -c $PULSE/Config.ini --my-ip $IP --tcp_port=$TCP_Port --udp_port=$UDP_Port --output-http=$HTTP_Port &> /dev/null &
                               echo "Message: PULSE client is started succesfully."
                               echo "You can access the stream with following URL"
                               echo "URL 'http://$IP:$HTTP_Port/'"
                               exit 0
                             else 
                               RandomNumber=`echo $RANDOM%900+1 | bc`
                               TCP_Port=$(($RandomNumber+$TCPPORT))
                               UDP_Port=$(($RandomNumber+1+$UDPPORT))
                               HTTP_Port=$(($RandomNumber+$RandomNumber+$HTTPPORT))
                               PulseSTREAM=`basename $PulseStream`
                               exec -a DAVS-PULSE-Client-$PulseSTREAM python2.4 $PULSE/pulse.py -m client $P2PStreams/${PulseSTREAM%.*}.out -c $PULSE/Config.ini --my-ip $IP --tcp_port=$TCP_Port --udp_port=$UDP_Port --output-http=$HTTP_Port &> /dev/null &
                               echo "You can access the stream with following URL"
                               echo "URL 'http://$IP:$HTTP_Port/'"
                               exit 0
                          fi # end if verbose mode is off
                fi # end if output file is directory.
           fi # end if Pulse Stream is specified
   fi # end if argument start with '-'
 fi # end if argument is greater than zero length
