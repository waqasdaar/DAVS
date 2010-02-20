package RPCClient;

import org.acplt.oncrpc.*;
import java.io.IOException;

import java.net.InetAddress;

public class davsClient extends OncRpcClientStub {

    public davsClient(InetAddress host, int protocol)
           throws OncRpcException, IOException {
        super(host, davs.DAVS, 10, 0, protocol);
    }

    public davsClient(InetAddress host, int port, int protocol)
           throws OncRpcException, IOException {
        super(host, davs.DAVS, 10, port, protocol);
    }

    public davsClient(OncRpcClient client)
           throws OncRpcException, IOException {
        super(client);
    }

    public davsClient(InetAddress host, int program, int version, int protocol)
           throws OncRpcException, IOException {
        super(host, program, version, 0, protocol);
    }

    public davsClient(InetAddress host, int program, int version, int port, int protocol)
           throws OncRpcException, IOException {
        super(host, program, version, port, protocol);
    }

    public int davs_rtsp_validate_10(String arg1, int arg2)
           throws OncRpcException, IOException {
        class XdrAble$ implements XdrAble {
            public String arg1;
            public int arg2;
            public void xdrEncode(XdrEncodingStream xdr)
                throws OncRpcException, IOException {
                xdr.xdrEncodeString(arg1);
                xdr.xdrEncodeInt(arg2);
            }
            public void xdrDecode(XdrDecodingStream xdr)
                throws OncRpcException, IOException {
            }
        };
        XdrAble$ args$ = new XdrAble$();
        args$.arg1 = arg1;
        args$.arg2 = arg2;
        XdrInt result$ = new XdrInt();
        client.call(davs.davs_rtsp_validate_10, davs.DAVS_VERSION, args$, result$);
        return result$.intValue();
    }

    public int davs_rtsp_stop_10(String arg1)
           throws OncRpcException, IOException {
        XdrString args$ = new XdrString(arg1);
        XdrInt result$ = new XdrInt();
        client.call(davs.davs_rtsp_stop_10, davs.DAVS_VERSION, args$, result$);
        return result$.intValue();
    }

    public int davs_rtsp_deport_10(String arg1)
           throws OncRpcException, IOException {
        XdrString args$ = new XdrString(arg1);
        XdrInt result$ = new XdrInt();
        client.call(davs.davs_rtsp_deport_10, davs.DAVS_VERSION, args$, result$);
        return result$.intValue();
    }

    public int davs_rtsp_vod_10(String arg1, String arg2, String arg3, String arg4, String arg5)
           throws OncRpcException, IOException {
        class XdrAble$ implements XdrAble {
            public String arg1;
            public String arg2;
            public String arg3;
            public String arg4;
            public String arg5;
            public void xdrEncode(XdrEncodingStream xdr)
                throws OncRpcException, IOException {
                xdr.xdrEncodeString(arg1);
                xdr.xdrEncodeString(arg2);
                xdr.xdrEncodeString(arg3);
                xdr.xdrEncodeString(arg4);
                xdr.xdrEncodeString(arg5);
            }
            public void xdrDecode(XdrDecodingStream xdr)
                throws OncRpcException, IOException {
            }
        };
        XdrAble$ args$ = new XdrAble$();
        args$.arg1 = arg1;
        args$.arg2 = arg2;
        args$.arg3 = arg3;
        args$.arg4 = arg4;
        args$.arg5 = arg5;
        XdrInt result$ = new XdrInt();
        client.call(davs.davs_rtsp_vod_10, davs.DAVS_VERSION, args$, result$);
        return result$.intValue();
    }

    public int davs_rtsp_broadcast_10(String arg1, String arg2)
           throws OncRpcException, IOException {
        class XdrAble$ implements XdrAble {
            public String arg1;
            public String arg2;
            public void xdrEncode(XdrEncodingStream xdr)
                throws OncRpcException, IOException {
                xdr.xdrEncodeString(arg1);
                xdr.xdrEncodeString(arg2);
            }
            public void xdrDecode(XdrDecodingStream xdr)
                throws OncRpcException, IOException {
            }
        };
        XdrAble$ args$ = new XdrAble$();
        args$.arg1 = arg1;
        args$.arg2 = arg2;
        XdrInt result$ = new XdrInt();
        client.call(davs.davs_rtsp_broadcast_10, davs.DAVS_VERSION, args$, result$);
        return result$.intValue();
    }

    public int davs_rtsp_RemoveVODStream_10(String arg1)
           throws OncRpcException, IOException {
        XdrString args$ = new XdrString(arg1);
        XdrInt result$ = new XdrInt();
        client.call(davs.davs_rtsp_RemoveVODStream_10, davs.DAVS_VERSION, args$, result$);
        return result$.intValue();
    }

    public int davs_rtsp_RemoveLiveStream_10(String arg1)
           throws OncRpcException, IOException {
        XdrString args$ = new XdrString(arg1);
        XdrInt result$ = new XdrInt();
        client.call(davs.davs_rtsp_RemoveLiveStream_10, davs.DAVS_VERSION, args$, result$);
        return result$.intValue();
    }

    public int davs_p2p_validate_10(String arg1)
           throws OncRpcException, IOException {
        XdrString args$ = new XdrString(arg1);
        XdrInt result$ = new XdrInt();
        client.call(davs.davs_p2p_validate_10, davs.DAVS_VERSION, args$, result$);
        return result$.intValue();
    }

    public int davs_p2p_start_10(String arg1)
           throws OncRpcException, IOException {
        XdrString args$ = new XdrString(arg1);
        XdrInt result$ = new XdrInt();
        client.call(davs.davs_p2p_start_10, davs.DAVS_VERSION, args$, result$);
        return result$.intValue();
    }

    public int davs_p2p_stop_10(String arg1, String arg2)
           throws OncRpcException, IOException {
        class XdrAble$ implements XdrAble {
            public String arg1;
            public String arg2;
            public void xdrEncode(XdrEncodingStream xdr)
                throws OncRpcException, IOException {
                xdr.xdrEncodeString(arg1);
                xdr.xdrEncodeString(arg2);
            }
            public void xdrDecode(XdrDecodingStream xdr)
                throws OncRpcException, IOException {
            }
        };
        XdrAble$ args$ = new XdrAble$();
        args$.arg1 = arg1;
        args$.arg2 = arg2;
        XdrInt result$ = new XdrInt();
        client.call(davs.davs_p2p_stop_10, davs.DAVS_VERSION, args$, result$);
        return result$.intValue();
    }

    public int davs_live555_validate_10(String arg1, int arg2)
           throws OncRpcException, IOException {
        class XdrAble$ implements XdrAble {
            public String arg1;
            public int arg2;
            public void xdrEncode(XdrEncodingStream xdr)
                throws OncRpcException, IOException {
                xdr.xdrEncodeString(arg1);
                xdr.xdrEncodeInt(arg2);
            }
            public void xdrDecode(XdrDecodingStream xdr)
                throws OncRpcException, IOException {
            }
        };
        XdrAble$ args$ = new XdrAble$();
        args$.arg1 = arg1;
        args$.arg2 = arg2;
        XdrInt result$ = new XdrInt();
        client.call(davs.davs_live555_validate_10, davs.DAVS_VERSION, args$, result$);
        return result$.intValue();
    }

    public int davs_streamavailable_10(String arg1)
           throws OncRpcException, IOException {
        XdrString args$ = new XdrString(arg1);
        XdrInt result$ = new XdrInt();
        client.call(davs.davs_streamavailable_10, davs.DAVS_VERSION, args$, result$);
        return result$.intValue();
    }

    public int davs_login_10(String arg1, String arg2)
           throws OncRpcException, IOException {
        class XdrAble$ implements XdrAble {
            public String arg1;
            public String arg2;
            public void xdrEncode(XdrEncodingStream xdr)
                throws OncRpcException, IOException {
                xdr.xdrEncodeString(arg1);
                xdr.xdrEncodeString(arg2);
            }
            public void xdrDecode(XdrDecodingStream xdr)
                throws OncRpcException, IOException {
            }
        };
        XdrAble$ args$ = new XdrAble$();
        args$.arg1 = arg1;
        args$.arg2 = arg2;
        XdrInt result$ = new XdrInt();
        client.call(davs.davs_login_10, davs.DAVS_VERSION, args$, result$);
        return result$.intValue();
    }

    public String davs_getrole_10(String arg1)
           throws OncRpcException, IOException {
        XdrString args$ = new XdrString(arg1);
        XdrString result$ = new XdrString();
        client.call(davs.davs_getrole_10, davs.DAVS_VERSION, args$, result$);
        return result$.stringValue();
    }

    public int davs_pulseOutAvailable_10(String arg1)
           throws OncRpcException, IOException {
        XdrString args$ = new XdrString(arg1);
        XdrInt result$ = new XdrInt();
        client.call(davs.davs_pulseOutAvailable_10, davs.DAVS_VERSION, args$, result$);
        return result$.intValue();
    }

    public String davs_GetStreamingEngines_10()
           throws OncRpcException, IOException {
        XdrVoid args$ = XdrVoid.XDR_VOID;
        XdrString result$ = new XdrString();
        client.call(davs.davs_GetStreamingEngines_10, davs.DAVS_VERSION, args$, result$);
        return result$.stringValue();
    }

    public String davs_GetVodStreams_10()
           throws OncRpcException, IOException {
        XdrVoid args$ = XdrVoid.XDR_VOID;
        XdrString result$ = new XdrString();
        client.call(davs.davs_GetVodStreams_10, davs.DAVS_VERSION, args$, result$);
        return result$.stringValue();
    }

    public String davs_GetBroadcast_Streams_10()
           throws OncRpcException, IOException {
        XdrVoid args$ = XdrVoid.XDR_VOID;
        XdrString result$ = new XdrString();
        client.call(davs.davs_GetBroadcast_Streams_10, davs.DAVS_VERSION, args$, result$);
        return result$.stringValue();
    }

    public String davs_GetVodStream_Description_10(String arg1)
           throws OncRpcException, IOException {
        XdrString args$ = new XdrString(arg1);
        XdrString result$ = new XdrString();
        client.call(davs.davs_GetVodStream_Description_10, davs.DAVS_VERSION, args$, result$);
        return result$.stringValue();
    }

    public String davs_GetBroadcastStream_Description_10(String arg1)
           throws OncRpcException, IOException {
        XdrString args$ = new XdrString(arg1);
        XdrString result$ = new XdrString();
        client.call(davs.davs_GetBroadcastStream_Description_10, davs.DAVS_VERSION, args$, result$);
        return result$.stringValue();
    }

    public String davs_GetSnapshot_10(String arg1)
           throws OncRpcException, IOException {
        XdrString args$ = new XdrString(arg1);
        XdrString result$ = new XdrString();
        client.call(davs.davs_GetSnapshot_10, davs.DAVS_VERSION, args$, result$);
        return result$.stringValue();
    }

    public String davs_GetStreamURI_10(String arg1)
           throws OncRpcException, IOException {
        XdrString args$ = new XdrString(arg1);
        XdrString result$ = new XdrString();
        client.call(davs.davs_GetStreamURI_10, davs.DAVS_VERSION, args$, result$);
        return result$.stringValue();
    }

    public ScriptResult davs_p2p_import_10(String arg1, int arg2, String arg3, String arg4)
           throws OncRpcException, IOException {
        class XdrAble$ implements XdrAble {
            public String arg1;
            public int arg2;
            public String arg3;
            public String arg4;
            public void xdrEncode(XdrEncodingStream xdr)
                throws OncRpcException, IOException {
                xdr.xdrEncodeString(arg1);
                xdr.xdrEncodeInt(arg2);
                xdr.xdrEncodeString(arg3);
                xdr.xdrEncodeString(arg4);
            }
            public void xdrDecode(XdrDecodingStream xdr)
                throws OncRpcException, IOException {
            }
        };
        XdrAble$ args$ = new XdrAble$();
        args$.arg1 = arg1;
        args$.arg2 = arg2;
        args$.arg3 = arg3;
        args$.arg4 = arg4;
        ScriptResult result$ = new ScriptResult();
        client.call(davs.davs_p2p_import_10, davs.DAVS_VERSION, args$, result$);
        return result$;
    }

    public ScriptResult davs_p2p_play_10(String arg1)
           throws OncRpcException, IOException {
        XdrString args$ = new XdrString(arg1);
        ScriptResult result$ = new ScriptResult();
        client.call(davs.davs_p2p_play_10, davs.DAVS_VERSION, args$, result$);
        return result$;
    }

    public ScriptResult davs_live555_import_10(int arg1, String arg2, int arg3, String arg4, String arg5)
           throws OncRpcException, IOException {
        class XdrAble$ implements XdrAble {
            public int arg1;
            public String arg2;
            public int arg3;
            public String arg4;
            public String arg5;
            public void xdrEncode(XdrEncodingStream xdr)
                throws OncRpcException, IOException {
                xdr.xdrEncodeInt(arg1);
                xdr.xdrEncodeString(arg2);
                xdr.xdrEncodeInt(arg3);
                xdr.xdrEncodeString(arg4);
                xdr.xdrEncodeString(arg5);
            }
            public void xdrDecode(XdrDecodingStream xdr)
                throws OncRpcException, IOException {
            }
        };
        XdrAble$ args$ = new XdrAble$();
        args$.arg1 = arg1;
        args$.arg2 = arg2;
        args$.arg3 = arg3;
        args$.arg4 = arg4;
        args$.arg5 = arg5;
        ScriptResult result$ = new ScriptResult();
        client.call(davs.davs_live555_import_10, davs.DAVS_VERSION, args$, result$);
        return result$;
    }

    public ScriptResult davs_rtsp_import_10(int arg1, String arg2, int arg3, String arg4, String arg5)
           throws OncRpcException, IOException {
        class XdrAble$ implements XdrAble {
            public int arg1;
            public String arg2;
            public int arg3;
            public String arg4;
            public String arg5;
            public void xdrEncode(XdrEncodingStream xdr)
                throws OncRpcException, IOException {
                xdr.xdrEncodeInt(arg1);
                xdr.xdrEncodeString(arg2);
                xdr.xdrEncodeInt(arg3);
                xdr.xdrEncodeString(arg4);
                xdr.xdrEncodeString(arg5);
            }
            public void xdrDecode(XdrDecodingStream xdr)
                throws OncRpcException, IOException {
            }
        };
        XdrAble$ args$ = new XdrAble$();
        args$.arg1 = arg1;
        args$.arg2 = arg2;
        args$.arg3 = arg3;
        args$.arg4 = arg4;
        args$.arg5 = arg5;
        ScriptResult result$ = new ScriptResult();
        client.call(davs.davs_rtsp_import_10, davs.DAVS_VERSION, args$, result$);
        return result$;
    }

    public ScriptResult davs_rtsp_start_10(String arg1)
           throws OncRpcException, IOException {
        XdrString args$ = new XdrString(arg1);
        ScriptResult result$ = new ScriptResult();
        client.call(davs.davs_rtsp_start_10, davs.DAVS_VERSION, args$, result$);
        return result$;
    }

}
