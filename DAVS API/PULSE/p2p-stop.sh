#~/bin/bash



status=23
PulseStream=""
PulseSourceMode="false"
PulseClientMode="false"
Mode=""

path=`dirname $0`
source $path/config

if [ $# -eq 0 ]
   then
      echo "Usage: `basename $0` [ -p Pulse Stream ID ] [ -s Pulse source mode ] [ -c Pulse client mode ] [ -h help ]"
      exit -1
   else


        cmdargument=$1
        if [ "${cmdargument:0:1}" != "-" ]
         then
             echo "Usage: `basename $0` [ -p Pulse Stream ID ] [ -s Pulse source mode ] [ -c Pulse client mode ] [ -h help ]"
             exit -1;
         else

             while getopts ":p:sch" option
           do
              case $option in
                p )   PulseStream=$OPTARG;;
                s )   PulseSourceMode="true";;
                c )   PulseClientMode="true";;
                h )   echo "Usage: `basename $0` [ -p Pulse Stream ID ] [ -s Pulse source mode ] [ -c Pulse client mode ] [ -h help ]"
                      echo "  -p Specify the PULSE stream to stop."
                      echo "  -s Stop the PULSE in source mode."
                      echo "  -c Stop the PULSE in client mode."
                      echo "  -h for Help."
                      exit 10;;
                *)    echo "Usage: `basename $0` [ -p Pulse Stream ID ] [ -s Pulse source mode ] [ -c Pulse client mode ] [ -h help ]"
                      exit 10;;
                esac
          done
        
          if [[ $PulseStream = "" ]]
            then 
              echo "Info: Please specify the PULSE stream."
              echo "     Example: `basename $0` -p /home/jhon/tune.avi"
              exit 256
            else
              if [ -d $PulseStream ]
                 then 
                        echo "Error: It seems to be a directory."
                        echo "    Example: `basename $0` -p /home/jhon/tune.mpeg"
                        exit 256;                   
                else 
	                 if [[ $PulseSourceMode = "false" && $PulseClientMode = "false" ]]
        	             then 
                	         echo "Info: Please specify the PULSE mode."
                        	 echo "      Example: `basename $0` -p /home/jhon/tune.avi -s"
	                         exit 256
        	             else
                	       if [ $PulseSourceMode = "true" ]
                        	  then
	                            Mode="DAVS-PULSE-Source-"
        	                  else 
                	            Mode="DAVS-PULSE-Client-"
	                       fi # Set the PULSE Mode
                               PulseStream=`basename $PulseStream`
                                    ProcessID=`ps -e x | grep "$Mode$PulseStream" | awk '{ print $1 }'`
                                    kill -9 $ProcessID 2>/dev/null
                              if [ $? -eq 0 ]
			        then
			          exit 0;
		             else
			          exit 1;
                              fi
                   fi # end if PULSE mode is not specified.
          fi # if PULSE stream is specified/
    fi # if its directory 
 fi # end if argument does not start with '-'
fi # end if argument is greater than zero length
