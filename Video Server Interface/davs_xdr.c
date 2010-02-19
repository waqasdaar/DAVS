/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#include "davs.h"

bool_t
xdr_ScriptResult (XDR *xdrs, ScriptResult *objp)
{
	register int32_t *buf;

	int i;
	 if (!xdr_int (xdrs, &objp->ExitStatus))
		 return FALSE;
	 if (!xdr_vector (xdrs, (char *)objp->CommandOutPut, Command_MAX,
		sizeof (char), (xdrproc_t) xdr_char))
		 return FALSE;
	return TRUE;
}

bool_t
xdr_davs_rtsp_validate_10_argument (XDR *xdrs, davs_rtsp_validate_10_argument *objp)
{
	 if (!xdr_string (xdrs, &objp->arg1, ~0))
		 return FALSE;
	 if (!xdr_int (xdrs, &objp->arg2))
		 return FALSE;
	return TRUE;
}

bool_t
xdr_davs_rtsp_vod_10_argument (XDR *xdrs, davs_rtsp_vod_10_argument *objp)
{
	 if (!xdr_string (xdrs, &objp->arg1, ~0))
		 return FALSE;
	 if (!xdr_string (xdrs, &objp->arg2, ~0))
		 return FALSE;
	 if (!xdr_string (xdrs, &objp->arg3, ~0))
		 return FALSE;
	 if (!xdr_string (xdrs, &objp->arg4, ~0))
		 return FALSE;
	 if (!xdr_string (xdrs, &objp->arg5, ~0))
		 return FALSE;
	return TRUE;
}

bool_t
xdr_davs_rtsp_broadcast_10_argument (XDR *xdrs, davs_rtsp_broadcast_10_argument *objp)
{
	 if (!xdr_string (xdrs, &objp->arg1, ~0))
		 return FALSE;
	 if (!xdr_string (xdrs, &objp->arg2, ~0))
		 return FALSE;
	return TRUE;
}

bool_t
xdr_davs_p2p_stop_10_argument (XDR *xdrs, davs_p2p_stop_10_argument *objp)
{
	 if (!xdr_string (xdrs, &objp->arg1, ~0))
		 return FALSE;
	 if (!xdr_string (xdrs, &objp->arg2, ~0))
		 return FALSE;
	return TRUE;
}

bool_t
xdr_davs_live555_validate_10_argument (XDR *xdrs, davs_live555_validate_10_argument *objp)
{
	 if (!xdr_string (xdrs, &objp->arg1, ~0))
		 return FALSE;
	 if (!xdr_int (xdrs, &objp->arg2))
		 return FALSE;
	return TRUE;
}

bool_t
xdr_davs_p2p_import_10_argument (XDR *xdrs, davs_p2p_import_10_argument *objp)
{
	 if (!xdr_string (xdrs, &objp->arg1, ~0))
		 return FALSE;
	 if (!xdr_int (xdrs, &objp->arg2))
		 return FALSE;
	 if (!xdr_string (xdrs, &objp->arg3, ~0))
		 return FALSE;
	 if (!xdr_string (xdrs, &objp->arg4, ~0))
		 return FALSE;
	return TRUE;
}

bool_t
xdr_davs_live555_import_10_argument (XDR *xdrs, davs_live555_import_10_argument *objp)
{
	 if (!xdr_int (xdrs, &objp->arg1))
		 return FALSE;
	 if (!xdr_string (xdrs, &objp->arg2, ~0))
		 return FALSE;
	 if (!xdr_int (xdrs, &objp->arg3))
		 return FALSE;
	 if (!xdr_string (xdrs, &objp->arg4, ~0))
		 return FALSE;
	 if (!xdr_string (xdrs, &objp->arg5, ~0))
		 return FALSE;
	return TRUE;
}

bool_t
xdr_davs_rtsp_import_10_argument (XDR *xdrs, davs_rtsp_import_10_argument *objp)
{
	 if (!xdr_int (xdrs, &objp->arg1))
		 return FALSE;
	 if (!xdr_string (xdrs, &objp->arg2, ~0))
		 return FALSE;
	 if (!xdr_int (xdrs, &objp->arg3))
		 return FALSE;
	 if (!xdr_string (xdrs, &objp->arg4, ~0))
		 return FALSE;
	 if (!xdr_string (xdrs, &objp->arg5, ~0))
		 return FALSE;
	return TRUE;
}
