#~/bin/bash


MediaStream=
IsVerbose="flase"

path=`dirname $0`
source $path/config


if [ $# -eq 0 ]
then
  echo "Usage: `basename $0` [-v Enable verbose mode. ] [ -s /Media/stream ] [ -h for Help]"
else
        cmdargument=$1
        if [ "${cmdargument:0:1}" != "-" ]
         then
            echo "Usage: `basename $0` [-v Enable verbose mode. ] [ -s /Media/stream ] [ -h for Help]"
            exit 256
         else

          while getopts ":vs:h" option;
           do

            case $option in
                         v) IsVerbose="true" ;;
                         s) MediaStream=$OPTARG;;
                         h) echo "Usage: `basename $0` [-v Enable verbose mode. ] [ -p /Media/stream ] [ h for Help ]"
                            echo "  -v :   Enable the verbose mode."
                            echo "  -p :   Specify the media streame path."
                            echo "  -h :   for Help"
                            exit 0;;
                        *)  echo "Usage: `basename $0` [-v Enable verbose mode.] [ -p /Media/stream ] [ -h for Help ]"
                            exit 0;;

                esac
        done
        shift $(($OPTIND-1))

                 if [ -z $MediaStream ]
                 then
                            echo "Error: Media stream  must be specified."
                            echo "     example: `basename $0` -s /home/videos/jhon/song.mpeg"
                            exit 2
                 else
                         Stream=`basename $MediaStream`
                          if [ -r $RTSP/$Stream ]
                            then 
                              exit 0
                          elif [ -r $PULSE/$Stream ]
                            then
                              exit 2
                          else
                              exit 1
                          fi
                fi # end if Stream is specified
  fi  # end if argument does not start with '-'
fi  # end if argument length is greater than zero
