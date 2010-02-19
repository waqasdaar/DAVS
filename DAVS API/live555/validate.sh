#~ /bin/bash
# Validate the Video file for Agnostic Video Server.
# Version 0.1
# Created by Waqas Daar (daar@kth.se)
# Date 7 Jan 2009 1:56 AM



###################################### Variables #################################


video_file="";
command_results="";
v_codec_find='false'
a_codec_find='false'
Video_codec=""
Audio_codec=""
TakeSnapshot="false"
sdp_version='v=0'
temp_path=/tmp/temp_ffmpeg


IsVerbose="false";
FiletoValidate="";

path=`dirname $0`
source $path/config


if [ $# -eq 0 ]
then
  echo "Usage: `basename $0` [-v Enable verbose mode. ] [ -f /path/of/a/file/to/validate ] 
                   [ -s  Take snapshot of a media stream. ] [ -h for Help]"
else 
       while getopts ":vf:sh" opt;
         do

                case $opt in
                         v)IsVerbose="true" ;;
                         f)FiletoValidate=$OPTARG;;
                         s)TakeSnapshot="true";;
                         h)
                                  echo "Usage: `basename $0` [-v Enable verbose mode. ] [ -f /path/of/a/file/to/validate ] [ -s Take snapshot of a media stream. ] [ -h for Help]"
                                  echo "  -v :   Enable the verbose mode."
                                  echo "  -f :   Specify the path of a file to validate."
                                  echo "  -s :   Take snapshot of a video if file is validated."
                                  echo "  -h :   Help"
                                  exit
                                   ;;
                         *)
                                   echo "Usage: `basename $0` [-v Enable verbose mode.] [ -f /file/to/validate ]
                   [ -s Take snapshot of a media stream.] [ -h for Help ]" 
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
                                    FileExtension=${video_file##*.}
                                    if [ "$FileExtension" = "sdp" -o "$FileExtension" = "SDP" ]
                                       then
                                         SDPVersion=`head -n 1 $video_file`
                                         if [ $SDPVersion = $sdp_version ]
                                            then 
                                                 if [ "$IsVerbose" = "true" ]
                                                then
                                                     echo "Message: SDP file is validated."
                                                     exit 0;
                                                 else
                                                     exit 0;
                                                  fi # end if IsVerbose is true
                                           else
                                                if [ "$IsVerbose" = "true" ]
                                                then
                                                     echo "Error: SDP file is not in correct format."
                                                     exit 12;
                                                 else
                                                     exit 12;
                                                  fi # end if IsVerbose is true
                                         fi # end if SDP Version is Zero.  
                                    else
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
                                       if [ "$TakeSnapshot" = "true" ]
                                          then
	                                                 image_path=$ImagePath;

							 filename="$(echo $video_file|awk '{size=split($video_file,file,"/"); print file[size]}')"
						         imagename="$(echo $filename|awk '{split($filename,image,"."); print image[1]}')"

						         image_get="ffmpeg -i $video_file -r 1 -t 00:00:01 -f image2 $image_path/$imagename.jpeg"
						         $image_get 2>/dev/null

                                                    if [ "$IsVerbose" = "true" ]
                                                      then
					                echo "Message: Video file is streamable and snapshot of a video has been stored."
	                                                rm -rf $temp_path
                                                        exit 0
                                                    else
                                                        rm -rf $temp_path
                                                        exit 0
                                                    fi # end if IsVerbose if true
					else
                                            if [ "$IsVerbose" = "true" ]
                                             then
                                                echo "Message: Video file is streamable."
                                                rm -rf $temp_path
                                                exit 0
                                             else
                                                rm -rf $temp_path
                                                exit 0
                                          fi # end if IsVerbose is true
					fi # end if TakeSnapshot is "true".
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
                         fi # end if video codec match and audio codec match
                  fi # end if command_results is zero length.
               fi # end if file is SDP File Check.
                else
                       if [ "$IsVerbose"="true" ]
                        then
                          echo "Error: File does not exist or may be corrupted."
                          exit 12
                        else
                          exit 12
                        fi
                 fi ## end if file does not exist or not readable.
             fi # end if its directory
   fi # end if File is provided.
fi # end if Argument is greater the Zero. 
