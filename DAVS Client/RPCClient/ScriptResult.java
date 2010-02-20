package RPCClient;

import org.acplt.oncrpc.*;
import java.io.IOException;

public class ScriptResult implements XdrAble {
    public int ExitStatus;
    public byte [] CommandOutPut;

    public ScriptResult() {
    }

    public ScriptResult(XdrDecodingStream xdr)
           throws OncRpcException, IOException {
        xdrDecode(xdr);
    }

    public void xdrEncode(XdrEncodingStream xdr)
           throws OncRpcException, IOException {
        xdr.xdrEncodeInt(ExitStatus);
        xdr.xdrEncodeByteFixedVector(CommandOutPut, davs.Command_MAX);
    }

    public void xdrDecode(XdrDecodingStream xdr)
           throws OncRpcException, IOException {
        ExitStatus = xdr.xdrDecodeInt();
        CommandOutPut = xdr.xdrDecodeByteFixedVector(davs.Command_MAX);
    }

}

