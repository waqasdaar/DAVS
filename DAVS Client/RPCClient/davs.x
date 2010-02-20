     const Command_MAX = 1024;                
     struct ScriptResult
      {
            int  ExitStatus;
            char CommandOutPut[Command_MAX];
      };


program DAVS {
          version DAVS_VERSION {

                int          davs_rtsp_validate(string,int)=1;
                int          davs_rtsp_stop(string)=4;
                int          davs_rtsp_deport(string)=5;
                int          davs_rtsp_vod(string,string,string,string,string)=10;
                int          davs_rtsp_broadcast(string,string)=11;
                int          davs_rtsp_RemoveVODStream(string)=14;
                int          davs_rtsp_RemoveLiveStream(string)=15;
                
                int          davs_p2p_validate(string)=20;
                int          davs_p2p_start(string)=23;
                int          davs_p2p_stop(string,string)=24;

                int          davs_live555_validate(string,int)=16;
                int          davs_streamavailable(string)=25;

                int          davs_login(string,string)=26;               
                string       davs_getrole(string)=27;
                
                int          davs_pulseOutAvailable(string)=28;

                string         davs_GetStreamingEngines(void)=13;
                string         davs_GetVodStreams(void)=6;
                string         davs_GetBroadcast_Streams(void)=7;
                string         davs_GetVodStream_Description(string)=8;
                string         davs_GetBroadcastStream_Description(string)=9;
                string         davs_GetSnapshot(string)=12;
                string         davs_GetStreamURI(string)=18;                             
               
                ScriptResult davs_p2p_import(string,int,string,string)=21;
                ScriptResult davs_p2p_play(string)=22;
                ScriptResult davs_live555_import(int,string,int,string,string)=17; 
                ScriptResult davs_rtsp_import(int,string,int,string,string)=2;
                ScriptResult davs_rtsp_start(string)=3;
          }=10;
}=0x20001234;













