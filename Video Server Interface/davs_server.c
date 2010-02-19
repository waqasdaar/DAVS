#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

#include "/usr/include/mysql/mysql.h"
#include "davs.h"


int *
davs_rtsp_validate_10_svc(char *VideoFile, int iTakeSnapshot,  struct svc_req *rqstp)
{
        static int  result;

        char BashPath[]="bash /home/waqasdaar/DAVS/bin/rtsp/validate.sh -f ";
        char TakeSnapshot[]=" -s";
        int BashExit;
        int length=strlen(BashPath)+strlen(TakeSnapshot);

        char *DAVS_Validate_Script;


        DAVS_Validate_Script=(char *)malloc(strlen(VideoFile)+length*sizeof(char));

        // Build the bash Validate command.
         strcpy(DAVS_Validate_Script,BashPath);
         strcat(DAVS_Validate_Script,VideoFile);

        //If user is interested to take snapshot of media stream.
        if (iTakeSnapshot==1) {
         strcat(DAVS_Validate_Script,TakeSnapshot);
        }

        BashExit=system(DAVS_Validate_Script);
        wait(&BashExit);

        /* If validate.sh scripts exits normally, we return
           the exit status of a 'validate.sh' script and store
           snapshot of a media stream in the predefined path, else
           we return the status of a system command exit status.*/

        if(WIFEXITED(BashExit)) {
           result=WEXITSTATUS(BashExit);
        }
        else {
            result=BashExit;
        }
        return &result;
}

int *
davs_rtsp_stop_10_svc(char *StreamID,  struct svc_req *rqstp)
{
        static int  result;

        char BashPath[]="bash /home/waqasdaar/DAVS/bin/rtsp/stop.sh -s ";
        int BashExit;
        int length=strlen(BashPath);

        char *DAVS_Stop_Script;

        DAVS_Stop_Script=(char *)malloc(strlen(StreamID)+length*sizeof(char));

        // Build the bash stop command.
         strcpy(DAVS_Stop_Script,BashPath);
         strcat(DAVS_Stop_Script,StreamID);

        BashExit=system(DAVS_Stop_Script);
        wait(&BashExit);

        if(WIFEXITED(BashExit)) {
         result=WEXITSTATUS(BashExit);
        }
        else {
         result=BashExit;
        }

	return &result;
}

int *
davs_rtsp_deport_10_svc(char *StreamID,  struct svc_req *rqstp)
{
        static int  result;

        char BashPath[]="bash /home/waqasdaar/DAVS/bin/rtsp/deport.sh -s ";
        int BashExit;
        int length=strlen(BashPath);

        char *DAVS_Deport_Script;

        DAVS_Deport_Script=(char *)malloc(strlen(StreamID)+length*sizeof(char));

        // Build the bash deport command.
         strcpy(DAVS_Deport_Script,BashPath);
         strcat(DAVS_Deport_Script,StreamID);

        BashExit=system(DAVS_Deport_Script);
        wait(&BashExit);

        if(WIFEXITED(BashExit)) {
         result=WEXITSTATUS(BashExit);
        }
        else {
         result=BashExit;
        }
	return &result;
}

int *
davs_rtsp_vod_10_svc(char *StreamName, char *Description, char *StreamID, char *ImagePath, char *StreamingURI,  struct 
svc_req *rqstp)
{

    static int  result;
    MYSQL *DAVS_Conn;
    DAVS_Conn = mysql_init(NULL);

    char *DAVS_Server="localhost";
    char *UserName="waqasdaar";
    char *Password="davs";
    char *DAVS_Database="DAVS";
    char  *Insert_VOD=NULL;

  char  VOD[]="INSERT INTO vod_streams (stream_name,description,stream_id,image_path,URI) VALUES ('%s','%s','%s','%s','%s')";

  int length=strlen(VOD)+strlen(StreamName)+strlen(Description)+strlen(StreamID)+strlen(ImagePath)+strlen(StreamingURI);

  Insert_VOD=(char*)malloc(sizeof(char)*length);     
  
  snprintf(Insert_VOD,length,VOD,StreamName,Description,StreamID,ImagePath,StreamingURI);

     if(!mysql_real_connect(DAVS_Conn,DAVS_Server,UserName,Password,DAVS_Database,0,NULL,0))
      {
         fprintf(stderr, "%s\n", mysql_error(DAVS_Conn));
         exit(1);
      }
        if(mysql_query(DAVS_Conn,Insert_VOD)==0) 
          {
              if(mysql_affected_rows(DAVS_Conn)>0) 
                {
                     result=0;
                }
               else {
                     result=1;
                }
          }
        else { 
           result=1;
         }         
  
        mysql_close(DAVS_Conn);
        free(Insert_VOD);
	return &result;
}

int *
davs_rtsp_broadcast_10_svc(char *StreamID, char *Description,  struct svc_req *rqstp)
{
    static int  result;

    MYSQL *DAVS_Conn;
    DAVS_Conn = mysql_init(NULL);

    char *DAVS_Server="localhost";
    char *UserName="waqasdaar";
    char *Password="davs";
    char *DAVS_Database="DAVS";
    char *Insert_Broadcast;
    
    
    char  Broadcast[]="INSERT INTO broadcast_streams (stream_id,description) VALUES ('%s','%s')";

    int length=strlen(Broadcast)+strlen(StreamID)+strlen(Description);

    Insert_Broadcast=(char*)malloc(sizeof(char)*length);

    snprintf(Insert_Broadcast,length,Broadcast,StreamID,Description);

    if(!mysql_real_connect(DAVS_Conn,DAVS_Server,UserName,Password,DAVS_Database,0,NULL,0))
      {
         fprintf(stderr, "%s\n", mysql_error(DAVS_Conn));
         exit(1);
      }

         if(mysql_query(DAVS_Conn,Insert_Broadcast)==0) {
           result=0;
         }
        else {
           result=1;
         }
         mysql_close(DAVS_Conn);
         free(Insert_Broadcast);
         return &result;
}


int *
davs_rtsp_removevodstream_10_svc(char *StreamID,  struct svc_req *rqstp)
{
	static int IsRemoved=-1;
        MYSQL *DAVS_Conn;
   
    DAVS_Conn = mysql_init(NULL);

    char *DAVS_Server="localhost";
    char *UserName="waqasdaar";
    char *Password="davs";
    char *DAVS_Database="DAVS";
    char  CommandEnd[]="'";
    char *Remove_VOD_Streams=NULL;

    char  Remove_Streams[]="DELETE FROM vod_streams WHERE stream_id = '%s'";
    int length=strlen(Remove_Streams)+strlen(StreamID);
    Remove_VOD_Streams=(char*)malloc(sizeof(char)*length);
    snprintf(Remove_VOD_Streams,length,Remove_Streams,StreamID);

     if(!mysql_real_connect(DAVS_Conn,DAVS_Server,UserName,Password,DAVS_Database,0,NULL,0))
      {
         fprintf(stderr, "%s\n", mysql_error(DAVS_Conn));
         exit(1);
      }

      mysql_query(DAVS_Conn,Remove_VOD_Streams);

      if(mysql_affected_rows(DAVS_Conn)<0) {
         IsRemoved=1;
       }
       else {
         IsRemoved=0;
      }
      mysql_close(DAVS_Conn);
      return &IsRemoved;
}

int *
davs_rtsp_removelivestream_10_svc(char *StreamID,  struct svc_req *rqstp)
{
        static int IsRemoved=-1;
        MYSQL *DAVS_Conn;

    DAVS_Conn = mysql_init(NULL);

    char *DAVS_Server="localhost";
    char *UserName="waqasdaar";
    char *Password="davs";
    char *DAVS_Database="DAVS";
    char *Remove_Live_Streams;  

    char  Remove_Streams[]="DELETE FROM broadcast_streams WHERE stream_id = '%s'";
    int length=strlen(Remove_Streams)+strlen(StreamID);
    Remove_Live_Streams=(char*)malloc(length*sizeof(char));
    snprintf(Remove_Live_Streams,length,Remove_Streams,StreamID);

     if(!mysql_real_connect(DAVS_Conn,DAVS_Server,UserName,Password,DAVS_Database,0,NULL,0))
      {
         fprintf(stderr, "%s\n", mysql_error(DAVS_Conn));
         exit(1);
      }

      mysql_query(DAVS_Conn,Remove_Live_Streams);

      if(mysql_affected_rows(DAVS_Conn)<0) {
         IsRemoved=1;
       }
       else {
         IsRemoved=0;
      }
    return &IsRemoved;
}

char **
davs_getstreamingengines_10_svc(struct svc_req *rqstp)
{
	static char * Engines;

        char GetEngines[]="bash /home/waqasdaar/DAVS/engines/engines.sh";

        FILE *fp;
        char ScriptOutPut[1024];
        char Results[1024];
        

         fp=popen(GetEngines,"r");


        while(fgets(ScriptOutPut,sizeof(ScriptOutPut),fp)) {
           strcpy(Results,ScriptOutPut);
        }
        
        Engines=(char*)malloc(strlen(Results)*sizeof(char));

        while(fgets(ScriptOutPut,sizeof(ScriptOutPut),fp)) {
          strcpy(Results,ScriptOutPut);
         }
        strcpy(Engines,Results);
        fclose(fp);
	return &Engines;
}

char **
davs_getvodstreams_10_svc(struct svc_req *rqstp)
{
	static char *Vod_Streams=NULL;

        MYSQL *DAVS_Conn;
        MYSQL_RES *DAVS_Res;
        MYSQL_ROW  DAVS_Row;

        char *DAVS_Server="localhost";
        char *UserName="waqasdaar";
        char *Password="davs";
        char *DAVS_Database="DAVS";
        long TotalStreams;
        int i=0;
        char strQuery[]="select  stream_id from  vod_streams";

        DAVS_Conn = mysql_init(NULL);
        if(!mysql_real_connect(DAVS_Conn,DAVS_Server,UserName,Password,DAVS_Database,0,NULL,0) ) {
         fprintf(stderr, "%s\n", mysql_error(DAVS_Conn));
         exit(1);
        }
        if(mysql_query(DAVS_Conn,strQuery)) {
          fprintf(stderr,"%s\n",mysql_error(DAVS_Conn));
        }
 
        DAVS_Res=mysql_store_result(DAVS_Conn);
        if(mysql_num_rows(DAVS_Res)>0) 
        {
          TotalStreams=mysql_num_rows(DAVS_Res);
          Vod_Streams=(char *)malloc(sizeof(char)*TotalStreams*50);
          while (DAVS_Row=mysql_fetch_row(DAVS_Res)) {
              if(i==0){
               strcpy(Vod_Streams,DAVS_Row[0]);
               strcat(Vod_Streams,"=");
               i=1;
              }
              else {
               strcat(Vod_Streams,DAVS_Row[0]);
               strcat(Vod_Streams,"=");
              }
           } // end while loop
       }
       else {
         Vod_Streams="";
       }
        mysql_close(DAVS_Conn);
	return &Vod_Streams;
}

char **
davs_getbroadcast_streams_10_svc(struct svc_req *rqstp)
{
	static char * Broadcast_Streams=NULL;

        MYSQL *DAVS_Conn;
        MYSQL_RES *DAVS_Res;
        MYSQL_ROW  DAVS_Row;

       char *DAVS_Server="localhost";
       char *UserName="waqasdaar";
       char *Password="davs";
       char *DAVS_Database="DAVS";
       long TotalStreams;
       int i=0;
       char strQuery[]="select  stream_id from broadcast_streams";

       DAVS_Conn = mysql_init(NULL);
      if(!mysql_real_connect(DAVS_Conn,DAVS_Server,UserName,Password,DAVS_Database,0,NULL,0) ) {
         fprintf(stderr, "%s\n", mysql_error(DAVS_Conn));
         exit(1);
      }

        if(mysql_query(DAVS_Conn,strQuery)) {
         fprintf(stderr,"%s\n",mysql_error(DAVS_Conn));
      }

      DAVS_Res=mysql_store_result(DAVS_Conn);
      if(mysql_num_rows(DAVS_Res)>0) 
      {
         TotalStreams=mysql_num_rows(DAVS_Res);
         Broadcast_Streams=(char *)malloc(sizeof(char)*TotalStreams*50);
         while (DAVS_Row=mysql_fetch_row(DAVS_Res)) {
             if(i==0){
              strcpy(Broadcast_Streams,DAVS_Row[0]);
              strcat(Broadcast_Streams,"=");
              i=1;
             }
             else {
              strcat(Broadcast_Streams,DAVS_Row[0]);
              strcat(Broadcast_Streams,"=");
             }
          } // end while loop
       }
       else {
           Broadcast_Streams="";
        }
         mysql_close(DAVS_Conn);
     	return &Broadcast_Streams;
}

char **
davs_getvodstream_description_10_svc(char *StreamID,  struct svc_req *rqstp)
{
        static char * Stream_description=NULL;

        MYSQL *DAVS_Conn;
        MYSQL_RES *DAVS_Res;
        MYSQL_ROW  DAVS_Row;

       char *DAVS_Server="localhost";
       char *UserName="waqasdaar";
       char *Password="davs";
       char *DAVS_Database="DAVS";
       char *Query;


       char strQuery[]="select Description from vod_streams where stream_id= '%s'";
       int length=strlen(strQuery)+strlen(StreamID);
       Query=(char*)malloc(length*sizeof(char));

       snprintf(Query,length,strQuery,StreamID);

       DAVS_Conn = mysql_init(NULL);

       if(!mysql_real_connect(DAVS_Conn,DAVS_Server,UserName,Password,DAVS_Database,0,NULL,0) ) {
         fprintf(stderr, "%s\n", mysql_error(DAVS_Conn));
         exit(1);
      }

      if(mysql_query(DAVS_Conn,Query)) {
         fprintf(stderr,"%s\n",mysql_error(DAVS_Conn));
      }

          DAVS_Res=mysql_store_result(DAVS_Conn);
          Stream_description=(char*)malloc(sizeof(char)*1024);

         while (DAVS_Row=mysql_fetch_row(DAVS_Res)) {
              strcpy(Stream_description,DAVS_Row[0]);
        } // end while loop
        mysql_close(DAVS_Conn);
        free(Query);
        return &Stream_description;
  }


char **
davs_getbroadcaststream_description_10_svc(char *StreamID,  struct svc_req *rqstp)
{
        static char * Stream_description=NULL;

        MYSQL *DAVS_Conn;
        MYSQL_RES *DAVS_Res;
        MYSQL_ROW  DAVS_Row;

       char *DAVS_Server="localhost";
       char *UserName="waqasdaar";
       char *Password="davs";
       char *DAVS_Database="DAVS";
       char *Query;


       char strQuery[]="select description from broadcast_streams where stream_id= '%s'";
       int length=strlen(strQuery)+strlen(StreamID);
       Query=(char*)malloc(length*sizeof(char));

       snprintf(Query,length,strQuery,StreamID);

       DAVS_Conn = mysql_init(NULL);
       if(!mysql_real_connect(DAVS_Conn,DAVS_Server,UserName,Password,DAVS_Database,0,NULL,0) ) {
         fprintf(stderr, "%s\n", mysql_error(DAVS_Conn));
         exit(1);
      }

      if(mysql_query(DAVS_Conn,Query)) {
         fprintf(stderr,"%s\n",mysql_error(DAVS_Conn));
      }

          DAVS_Res=mysql_store_result(DAVS_Conn);
          Stream_description=(char*)malloc(sizeof(char)*1024);

         while (DAVS_Row=mysql_fetch_row(DAVS_Res)) {
              strcpy(Stream_description,DAVS_Row[0]);
        } // end while loop
        mysql_close(DAVS_Conn);
        free(Query);
        return &Stream_description;
}


char **
davs_getsnapshot_10_svc(char *StreamID,  struct svc_req *rqstp)
{
 	 static    char *ImageData;

         MYSQL *DAVS_Conn;
	 MYSQL_RES *DAVS_Res;
	 MYSQL_ROW  DAVS_Row;

	 FILE *Image;
         unsigned long ImageLen;

         DAVS_Conn = mysql_init(NULL);

         char *DAVS_Server="localhost";
         char *UserName="waqasdaar";
         char *Password="davs";
         char *DAVS_Database="DAVS";
         char ImagePath[100];
         char *Query=NULL;

         char  Get_Path[]="select image_path from vod_streams where stream_id= '%s'";
         int length=strlen(StreamID)+strlen(Get_Path);
         Query=(char*)malloc(length*sizeof(char));
        
         snprintf(Query,length,Get_Path,StreamID);

        if(!mysql_real_connect(DAVS_Conn,DAVS_Server,UserName,Password,DAVS_Database,0,NULL,0))
        {
          fprintf(stderr, "%s\n", mysql_error(DAVS_Conn));
          exit(1);
        }

      mysql_query(DAVS_Conn,Query);
      DAVS_Res=mysql_store_result(DAVS_Conn);

      if(mysql_num_rows(DAVS_Res)>0) 
      {

         while ((DAVS_Row=mysql_fetch_row(DAVS_Res))!=NULL) 
            {
             strcpy(ImagePath,DAVS_Row[0]);
            }

      mysql_free_result(DAVS_Res);
      mysql_close(DAVS_Conn);
      free(Query);

      ImagePath[strlen(ImagePath)+1]='\0';
     
      if(!strcmp(ImagePath,"")) {
          ImageData="No Image";
        }
      else {
      Image=fopen(ImagePath,"rb");

       if(!Image) {
         fprintf (stderr, "Unable to open file '%s'\n",ImagePath);
         exit(1);
        }

        fseek (Image, 0, SEEK_END);
        ImageLen = ftell (Image);
        fseek (Image, 0, SEEK_SET);

        //Allocate memory for Snapshot
        ImageData   = (char *) malloc (ImageLen*sizeof(char));

        if(!ImageData) {
           fprintf(stderr,"DAVS Error: Memory error.");
           fclose(Image);
           exit(1);
         }
           length=fread(ImageData,1,ImageLen,Image);
           fclose(Image);
         }
        }
       else {
          ImageData="No Image";
        } 
        /*  
        long i=0;
        while (i <=ImageLen){
           printf("%02X ",(unsigned char)ImageData[i]);
           i++;
           if( ! (i % 16) ) printf( "\n" );
       }
       */
     return &ImageData;
 }

ScriptResult *
davs_rtsp_import_10_svc(int StreamingType, char *MediaStream, int iBitRate, char *VideoCodec, char *AudioCodec ,struct 
svc_req *rqstp)
{
	static ScriptResult  result;

        char *DAVS_Import_Script=NULL;
        char BashPath[]="bash /home/waqasdaar/DAVS/bin/rtsp/import.sh ";
        char LiveStreaming[]=" -l ";
        char BroadCastStreaming[]=" -b ";
        char VideoOnDemandStream[]=" -d ";
        char BitRateOption[25]=" -t ";
        char AudioOption[25]=" -a ";
        char VideoOption[25]=" -g ";
        char BitRate[30]="\0";
        char ScriptOutPut[1024];
        int BashExit;
        int length=0;
        FILE *fp;
        

  if(StreamingType==1) { // Live Streaming

             if(iBitRate>1) {
                       if(!strcmp(VideoCodec,"") && !strcmp(AudioCodec,""))
                            {
                             char *LiveStreamOptions=(char *)malloc(sizeof(char)*(strlen(LiveStreaming)+strlen(MediaStream)+strlen(BashPath)));
                             strcpy(LiveStreamOptions,BashPath);
                             sprintf(BitRate,"%d",iBitRate);
                             strcat(LiveStreamOptions,LiveStreaming);
                             strcat(LiveStreamOptions,MediaStream);
                             strcat(BitRateOption,BitRate);
                             int length=strlen(LiveStreamOptions);
                             DAVS_Import_Script=(char *)malloc(length+strlen(BitRateOption)*sizeof(char));
                             strcpy(DAVS_Import_Script,LiveStreamOptions);
                             strcat(DAVS_Import_Script,BitRateOption);
                            }
                            else 
                            {

                            DAVS_Import_Script=(char*)malloc(sizeof(char)*(strlen(AudioOption)+strlen(VideoCodec)+strlen(AudioCodec)+strlen(MediaStream)+strlen(VideoOption)+sizeof(BitRateOption)+strlen(BashPath)+strlen(LiveStreaming)));
                            strcpy(DAVS_Import_Script,BashPath);
                            sprintf(BitRate,"%d",iBitRate);
                            strcat(DAVS_Import_Script,LiveStreaming);
                            strcat(DAVS_Import_Script,MediaStream);
                            strcat(DAVS_Import_Script,VideoOption);
                            strcat(DAVS_Import_Script,VideoCodec);
                            strcat(DAVS_Import_Script,AudioOption);
                            strcat(DAVS_Import_Script,AudioCodec);
                            strcat(BitRateOption,BitRate);
                            strcat(DAVS_Import_Script,BitRateOption);
                          }
                      }
                    else {
               		char *LiveStreamOptions=(char*)malloc(strlen(LiveStreaming)+strlen(BashPath)+strlen(MediaStream)*sizeof(char));
		        strcpy(LiveStreamOptions,BashPath);
	                strcat(LiveStreamOptions,LiveStreaming);
         	        strcat(LiveStreamOptions,MediaStream);
	                int length=strlen(LiveStreamOptions);
	                DAVS_Import_Script=(char *)malloc(length*sizeof(char));
	                strcpy(DAVS_Import_Script,LiveStreamOptions);
                  }
          }
         else if(StreamingType==2) // Broadcast Streaming
          {
            if(iBitRate>1)
                  {
                if(!strcmp(VideoCodec,"") && !strcmp(AudioCodec,""))
                            {
                             char *BroadcastOptions=(char *)malloc(sizeof(char)*(strlen(BroadCastStreaming)+strlen(MediaStream)+strlen(BashPath)));                             
                             strcpy(BroadcastOptions,BashPath);
                             sprintf(BitRate,"%d",iBitRate);
                             strcat(BroadcastOptions,BroadCastStreaming);
                             strcat(BroadcastOptions,MediaStream);
                             strcat(BitRateOption,BitRate);
                             int length=strlen(BroadcastOptions);
                             DAVS_Import_Script=(char *)malloc(length+strlen(BitRateOption)*sizeof(char));
                             strcpy(DAVS_Import_Script,BroadcastOptions);
                             strcat(DAVS_Import_Script,BitRateOption);
                              
                            }
                        else
                            {
 			    DAVS_Import_Script=(char*)malloc(sizeof(char)*(strlen(AudioOption)+strlen(VideoCodec)+strlen(AudioCodec)+strlen(MediaStream)+strlen(VideoOption)+sizeof(BitRateOption)+strlen(BashPath)+strlen(BroadCastStreaming)));
                            strcpy(DAVS_Import_Script,BashPath);
                            sprintf(BitRate,"%d",iBitRate);
                            strcat(DAVS_Import_Script,BroadCastStreaming);
                            strcat(DAVS_Import_Script,MediaStream);
                            strcat(DAVS_Import_Script,VideoOption);
                            strcat(DAVS_Import_Script,VideoCodec);
                            strcat(DAVS_Import_Script,AudioOption);
                            strcat(DAVS_Import_Script,AudioCodec);
                            strcat(BitRateOption,BitRate);
                            strcat(DAVS_Import_Script,BitRateOption);
                            }
                           }
                else
                   {
                      DAVS_Import_Script=(char*)malloc(sizeof(char)*(strlen(BroadCastStreaming)+strlen(MediaStream)+strlen(BashPath)));
                      strcpy(DAVS_Import_Script,BashPath);
                      strcat(DAVS_Import_Script,BroadCastStreaming);
                      strcat(DAVS_Import_Script,MediaStream);
                   }
               }
             else { // Video on Demand streaming
                     if(iBitRate>1)  
                     {
                             if(!strcmp(VideoCodec,"") && !strcmp(AudioCodec,""))
                             {
                              char Import[]="bash /home/waqasdaar/DAVS/bin/rtsp/import.sh -d %s -t %s ";
                              sprintf(BitRate,"%d",iBitRate);
                              int length=strlen(MediaStream)+strlen(Import)+strlen(BitRate);
                              DAVS_Import_Script=(char *)malloc(length*sizeof(char));
                              snprintf(DAVS_Import_Script,length,Import,MediaStream,BitRate);
                            }
                            else
                            {
                            char Import[]="bash /home/waqasdaar/DAVS/bin/rtsp/import.sh -d %s -g %s -a %s -t %s ";
                            sprintf(BitRate,"%d",iBitRate);
                            int length=strlen(VideoCodec)+strlen(AudioCodec)+strlen(MediaStream)+strlen(Import)+strlen(BitRate);
                            DAVS_Import_Script=(char *)malloc(length*sizeof(char));
                            snprintf(DAVS_Import_Script,length,Import,MediaStream,VideoCodec,AudioCodec,BitRate);
                            }
                     }
                  else
                   {
                      char Import[]="bash /home/waqasdaar/DAVS/bin/rtsp/import.sh -d %s";
                      int length=strlen(Import)+strlen(MediaStream);
                      DAVS_Import_Script=(char*)malloc(sizeof(char)*length);
                      snprintf(DAVS_Import_Script,length,Import,MediaStream);
                   }
              }

      fp=popen(DAVS_Import_Script,"r");

      while(fgets(ScriptOutPut,sizeof(ScriptOutPut),fp)) {
        strcpy(result.CommandOutPut,ScriptOutPut);
       }

     result.ExitStatus=pclose(fp);
     free(DAVS_Import_Script);
     fclose(fp);
     return &result;
}



ScriptResult *
davs_rtsp_start_10_svc(char *StreamID,  struct svc_req *rqstp)
{
	static ScriptResult  result;

        FILE *fp;
        char ScriptOutPut[1024]="\0";

        char BashPath[]="bash /home/waqasdaar/DAVS/bin/rtsp/start.sh -s ";
        int BashExit;

        char *DAVS_Start_Script;

        DAVS_Start_Script=(char *)malloc((strlen(StreamID)+strlen(BashPath))*sizeof(char));

        // Build the bash start command.

         strcpy(DAVS_Start_Script,BashPath);
         strcat(DAVS_Start_Script,StreamID);

         fp=popen(DAVS_Start_Script,"r");

        while(fgets(ScriptOutPut,sizeof(ScriptOutPut),fp)) {
         strcpy(result.CommandOutPut,ScriptOutPut);
        }

        fclose(fp);
        result.ExitStatus=0;
        free(DAVS_Start_Script);
	return &result;
}

int *
davs_live555_validate_10_svc(char *VideoFile, int iTakeSnapshot,  struct svc_req *rqstp)
{
	static int  result;

        char BashPath[]="bash /home/waqasdaar/DAVS/bin/live555/validate.sh -f ";
        char TakeSnapshot[]=" -s";
        int BashExit;
        int length=strlen(BashPath)+strlen(TakeSnapshot);

        char *DAVS_Validate_Script;


        DAVS_Validate_Script=(char *)malloc(strlen(VideoFile)+length*sizeof(char));

        // Build the bash Validate command.
         strcpy(DAVS_Validate_Script,BashPath);
         strcat(DAVS_Validate_Script,VideoFile);

        //If user is interested to take snapshot of media stream.
        if (iTakeSnapshot==1) {
         strcat(DAVS_Validate_Script,TakeSnapshot);
        }

        BashExit=system(DAVS_Validate_Script);
        wait(&BashExit);

        /* If validate.sh scripts exits normally, we return
           the exit status of a 'validate.sh' script and store
           snapshot of a media stream in the predefined path, else
           we return the status of a system command exit status.*/

        if(WIFEXITED(BashExit)) {
           result=WEXITSTATUS(BashExit);
        }
        else {
            result=BashExit;
        }

	return &result;
}

char **
davs_getstreamuri_10_svc(char *StreamID,  struct svc_req *rqstp)
{
	static char * URI;

        MYSQL *DAVS_Conn;
        MYSQL_RES *DAVS_Res;
        MYSQL_ROW  DAVS_Row;

       char *DAVS_Server="localhost";
       char *UserName="waqasdaar";
       char *Password="davs";
       char *DAVS_Database="DAVS";
       char *Query;


       char strQuery[]="select URI from vod_streams  where stream_id= '%s'";
       int length=strlen(strQuery)+strlen(StreamID);
       Query=(char*)malloc(length*sizeof(char));

       snprintf(Query,length,strQuery,StreamID);

       DAVS_Conn = mysql_init(NULL);
       if(!mysql_real_connect(DAVS_Conn,DAVS_Server,UserName,Password,DAVS_Database,0,NULL,0) ) {
         fprintf(stderr, "%s\n", mysql_error(DAVS_Conn));
         exit(1);
      }

      if(mysql_query(DAVS_Conn,Query)) {
         fprintf(stderr,"%s\n",mysql_error(DAVS_Conn));
      }

          DAVS_Res=mysql_store_result(DAVS_Conn);
          URI=(char*)malloc(sizeof(char)*500);

         while (DAVS_Row=mysql_fetch_row(DAVS_Res)) {
              strcpy(URI,DAVS_Row[0]);
        } // end while loop
        mysql_close(DAVS_Conn);
        free(Query);
	return &URI;
}

ScriptResult *
davs_live555_import_10_svc(int StreamingType,char *MediaStream,int iBitRate,char *VideoCodec,char *AudioCodec,struct 
svc_req *rqstp)
{
	static ScriptResult  result;

        char *DAVS_Import_Script=NULL;
        char BashPath[]="bash /home/waqasdaar/DAVS/bin/live555/import.sh ";
        char BroadCastStreaming[]=" -d ";
        char BitRateOption[25]=" -t ";
        char AudioOption[25]=" -a ";
        char VideoOption[25]=" -g ";
        char BitRate[30]="\0";
        char ScriptOutPut[1024];
        int BashExit;
        int length=0;
        FILE *fp;
           
             if(iBitRate>1)
                  {
                       if(!strcmp(VideoCodec,"") && !strcmp(AudioCodec,""))
                            {

                             char *BroadcastOptions=(char *)malloc(sizeof(char)*(strlen(BroadCastStreaming)+strlen(MediaStream)+strlen(BashPath)));                             
                             strcpy(BroadcastOptions,BashPath);
                             sprintf(BitRate,"%d",iBitRate);
                             strcat(BroadcastOptions,BroadCastStreaming);
                             strcat(BroadcastOptions,MediaStream);
                             strcat(BitRateOption,BitRate);
                             int length=strlen(BroadcastOptions);
                             DAVS_Import_Script=(char *)malloc(length+strlen(BitRateOption)*sizeof(char));
                             strcpy(DAVS_Import_Script,BroadcastOptions);
                             strcat(DAVS_Import_Script,BitRateOption);

                            }
                        else
                            {
 			    DAVS_Import_Script=(char*)malloc(sizeof(char)*(strlen(AudioOption)+strlen(VideoCodec)+strlen(AudioCodec)+strlen(MediaStream)+strlen(VideoOption)+sizeof(BitRateOption)+strlen(BashPath)));
                            strcpy(DAVS_Import_Script,BashPath);
                            sprintf(BitRate,"%d",iBitRate);
                            strcat(DAVS_Import_Script,BroadCastStreaming);
                            strcat(DAVS_Import_Script,MediaStream);
                            strcat(DAVS_Import_Script,VideoOption);
                            strcat(DAVS_Import_Script,VideoCodec);
                            strcat(DAVS_Import_Script,AudioOption);
                            strcat(DAVS_Import_Script,AudioCodec);
                            strcat(BitRateOption,BitRate);
                            strcat(DAVS_Import_Script,BitRateOption);
                            }
                    }
                  else
                   {
                      DAVS_Import_Script=(char*)malloc(sizeof(char)*(strlen(BroadCastStreaming)+strlen(MediaStream)+strlen(BashPath)));
                      strcpy(DAVS_Import_Script,BashPath);
                      strcat(DAVS_Import_Script,BroadCastStreaming);
                      strcat(DAVS_Import_Script,MediaStream);
                   }

      fp=popen(DAVS_Import_Script,"r");

      while(fgets(ScriptOutPut,sizeof(ScriptOutPut),fp)) {
        strcpy(result.CommandOutPut,ScriptOutPut);
       }

     result.ExitStatus=pclose(fp);
     free(DAVS_Import_Script);
     //fclose(fp);
     return &result;
 }

int *
davs_p2p_validate_10_svc(char *P2PFile,  struct svc_req *rqstp)
{
	static int  result;

        char BashPath[]="bash /home/waqasdaar/DAVS/bin/PULSE/p2p-validate.sh -p %s ";
        int BashExit;
        int length=strlen(BashPath)+strlen(P2PFile);

        char *DAVS_PULSE_Script=NULL;


        DAVS_PULSE_Script=(char *)malloc(length*sizeof(char));
        snprintf(DAVS_PULSE_Script,length,BashPath,P2PFile);


        BashExit=system(DAVS_PULSE_Script);
        wait(&BashExit);

        /* If validate.sh scripts exits normally, we return
           the exit status of a 'validate.sh' script and store
           snapshot of a media stream in the predefined path, else
           we return the status of a system command exit status.*/

        if(WIFEXITED(BashExit)) {
           result=WEXITSTATUS(BashExit);
        }
        else {
            result=BashExit;
        }
        free(DAVS_PULSE_Script);
	return &result;
}

int *
davs_p2p_start_10_svc(char *P2PStream,  struct svc_req *rqstp)
{
   static int  result;
   char BashPath[]="bash /home/waqasdaar/DAVS/bin/PULSE/p2p-start.sh -p %s";
   int length=0;

   FILE *fp;
   char *DAVS_PULSE_Start_Script=NULL;

   length=strlen(BashPath)+strlen(P2PStream);
   DAVS_PULSE_Start_Script=(char*)malloc(length*sizeof(char));
   snprintf(DAVS_PULSE_Start_Script,length,BashPath,P2PStream);

   fp=popen(DAVS_PULSE_Start_Script,"r");

   result=pclose(fp);
   //fclose(fp);
   free(DAVS_PULSE_Start_Script);
   return &result;
}

int *
davs_p2p_stop_10_svc(char *P2PVideo,char *Mode, struct svc_req *rqstp)
{
	static int  result;

	char BashPath[]="bash /home/waqasdaar/DAVS/bin/PULSE/p2p-stop.sh -p %s %s";
        char PulseMode[8];
        int BashExit;

        char *DAVS_PULSE_Stop_Script;

        if(!strcmp(Mode,"source")) {
          strcpy(PulseMode,"-s");
        } else {
          strcpy(PulseMode,"-c");
        }

        int length=strlen(BashPath)+strlen(PulseMode)+strlen(P2PVideo);
        DAVS_PULSE_Stop_Script=(char *)malloc(length*sizeof(char));
        snprintf(DAVS_PULSE_Stop_Script,length,BashPath,P2PVideo,PulseMode);


        BashExit=system(DAVS_PULSE_Stop_Script);
        wait(&BashExit);

        if(WIFEXITED(BashExit)) {
         result=WEXITSTATUS(BashExit);
        }
        else {
         result=BashExit;
        }
        free(DAVS_PULSE_Stop_Script);
	return &result;
}

ScriptResult *
davs_p2p_import_10_svc(char *P2PStream, int iBitRate, char *VideoCodec, char *AudioCodec,  struct svc_req *rqstp)
{
	static ScriptResult  result;

        char *DAVS_PULSE_Import_Script=NULL;
        char BitRate[30]="\0";
        char ScriptOutPut[1024];
        int BashExit;
        int length=0;
        FILE *fp;

        if (iBitRate>1) {
             char BashPath[]="bash /home/waqasdaar/DAVS/bin/PULSE/p2p-import.sh -p %s -g %s -a %s -t %s";
             sprintf(BitRate,"%d",iBitRate);
             length=strlen(P2PStream)+strlen(BashPath)+strlen(BitRate)+strlen(VideoCodec)+strlen(AudioCodec);
             DAVS_PULSE_Import_Script=(char*)malloc(length*sizeof(char));
             snprintf(DAVS_PULSE_Import_Script,length,BashPath,P2PStream,VideoCodec,AudioCodec,BitRate);
         }
        else {
             char BashPath[]="bash /home/waqasdaar/DAVS/bin/PULSE/p2p-import.sh -p %s ";
             length=strlen(P2PStream)+strlen(BashPath);
             DAVS_PULSE_Import_Script=(char*)malloc(length*sizeof(char));
             snprintf(DAVS_PULSE_Import_Script,length,BashPath,P2PStream);
        }

     fp=popen(DAVS_PULSE_Import_Script,"r");
      while(fgets(ScriptOutPut,sizeof(ScriptOutPut),fp)) {
        strcpy(result.CommandOutPut,ScriptOutPut);
       }
       result.ExitStatus=pclose(fp);
       free(DAVS_PULSE_Import_Script);

       //fclose(fp);
       return &result;
}

ScriptResult *
davs_p2p_play_10_svc(char *P2PStream,  struct svc_req *rqstp)
{
   static ScriptResult  result;
   char BashPath[]="bash /home/waqasdaar/DAVS/bin/PULSE/p2p-play.sh -p %s";
   int length=0;
   char ScriptOutPut[1024];

   FILE *fp;
   char *DAVS_PULSE_play_Script=NULL;

   length=strlen(BashPath)+strlen(P2PStream);
   DAVS_PULSE_play_Script=(char*)malloc(length*sizeof(char));
   snprintf(DAVS_PULSE_play_Script,length,BashPath,P2PStream);

   fp=popen(DAVS_PULSE_play_Script,"r");

   while(fgets(ScriptOutPut,sizeof(ScriptOutPut),fp)) {
        strcpy(result.CommandOutPut,ScriptOutPut);
   }
     result.ExitStatus=pclose(fp);
     free(DAVS_PULSE_play_Script);
     //fclose(fp);
     return &result;
}


int *
davs_streamavailable_10_svc(char *Stream,  struct svc_req *rqstp)
{
	static int  result;

        char BashScript[]="/home/waqasdaar/DAVS/checkstreams/CheckStream.sh -s %s";
        char *DAVS_CheckStream=NULL;
        int BashExit;

        int length=strlen(BashScript)+strlen(Stream);
        DAVS_CheckStream=(char*)malloc(length*sizeof(char));
        snprintf(DAVS_CheckStream,length,BashScript,Stream);

        BashExit=system(DAVS_CheckStream);
        wait(&BashExit);

        if(WIFEXITED(BashExit)) {
           result=WEXITSTATUS(BashExit);
        }
        else {
            result=BashExit;
        }
        //free(DAVS_CheckStream);
	return &result;
}

