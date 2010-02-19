#~ /bin/bash
# Validate the media file for PULSE streaming engine for Distribution Agnostic Video Server.
# Version 0.1
# Created by Waqas Daar (daar@kth.se)
# Date 3 April 2009 6:30 PM



###################################### Variables #################################


video_file="";
command_results="";
v_codec_find='false'
a_codec_find='false'
Video_codec=""
Audio_codec=""
image_path=""
sdp_version='v=0'
temp_path=/tmp/temp_ffmpeg


IsVerbose="false";
FiletoValidate="";


path=`dirname $0`
source $path/config


if [ $# -eq 0 ]
then
  echo "Usage: `basename $0` [-v Enable verbose mode. ] [ -p /pulse/stream/to/validate ] [ -h for Help]"
else 
       while getopts ":vp:h" opt;
         do

                case $opt in
                         v)IsVerbose="true" ;;
                         p)FiletoValidate=$OPTARG;;
                         h)
                                  echo "Usage: `basename $0` [-v Enable verbose mode. ] [ -p /pulse/stream/to/validate ] [ -h for Help]"
                                  echo "  -v :   Enable the verbose mode."
                                  echo "  -p :   Specify the PULSE stream to validate."
                                  echo "  -h :   Help"
                                  exit
                                   ;;
                         *)
                                   echo "Usage: `basename $0` [-v Enable verbose mode.] [ -p /pulse/stream/to/validate ] [ -h for Help ]" 
                                   exit ;;

                esac
        done
        shift $(($OPTIND-1))


                if [ -z $FiletoValidate ]
                 then
	        	    echo "Error: file name must be specified."
		            echo "     example: `basename $0` -f /home/videos/jhon/song.mpeg"
		            exit 2
 		 else
                      video_file=$FiletoValidate
                      if [ -d $video_file ]
                        then
                          echo "Error: It seems to be a directory."
                          echo "    example:  `basename $0` -f /home/jhon/videos/tune.mpeg "
                          exit -1
                     else
                       if [ -r $video_file ]
                          then
                                    PATH=$PATH:/home/waqasdaar/bin
                                    validation_command='ffmpeg -i '$video_file
                                    $validation_command 2>$temp_path
                                    command_results="$(sed -n '/Stream/p' $temp_path)"
                                    
				     if [ -z "$command_results" ];
                                       then
                                          if [ "$IsVerbose" = "true" ]
                                            then
                                               echo "Message: File is not recognised."
                                               exit 12;
                                          else
                                               exit 12
                                          fi # end if IsVerbose is true            
                                     else
                                        video_stream="$(sed -n '/Video:/p' $temp_path)"
                                        audio_stream="$(sed -n '/Audio:/p' $temp_path)"

                                        Video_codec=`echo $video_stream | cut -d' ' -f4`
                                        Audio_codec=`echo $audio_stream | cut -d' ' -f4`

				        v_codec="$(echo $Video_codec|awk '{split($Video_codec,codec,","); print codec[1]}')"
				        a_codec="$(echo $Audio_codec|awk '{split($Audio_codec,codec,","); print codec[1]}')"
						
						for v_codec_list in $supported_video_codecs
                                                    do
                                                            if [ "$v_codec" = "$v_codec_list" ];
                                                            then
                                                                  v_codec_find="true";
                                                            break
                                                            fi # end if
                                                 done # end for loop for Video codecs.
						
						for a_codec_list in $supported_audio_codecs
                                                    do
                                                            if [ "$a_codec" = "$a_codec_list" ];
                                                            then
                                                                    a_codec_find="true";
                                                            break
                                                            fi # end if
                                                 done # end for loop for Audio codecs.
                             if ( ("$v_codec_find" = "true") && ("$a_codec_find" ="true"))
                                   then
                                      if [ "$IsVerbose" = "true" ]
                                             then
                                                echo "Message: Video file is streamable."
                                                rm -rf $temp_path
                                                exit 0
                                             else
                                                rm -rf $temp_path
                                                exit 0
                                      fi # end if IsVerbose is true
                              else 
                                    if [ "$IsVerbose" = "true" ]
                                     then
			               echo "Message: Video file is recognised but needs transcoding."
                                       rm -rf $temp_path
                                       exit 1
                                     else
                                       rm -rf $temp_path
                                       exit 1
                                    fi # end if IsVerbose is true
                             fi
                          fi
                        else
                           if [ "$IsVerbose"="true" ]
                             then
                                echo "Error: File does not exist or may be corrupted."
                                exit 12
                             else
                               exit 12
                           fi
                       fi
                  fi
             fi
         fi

