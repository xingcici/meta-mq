package com.meta.mq.remote.protocol;

/**
 * @author : haifeng.pang.
 * @version 0.1 : MessageResponse v0.1 2021/2/3 下午2:39 By haifeng.pang.
 * @description :
 */
public class MessageResponse extends RemoteResponse {

    private static final long serialVersionUID = 23366612668278552L;

    private String redirect;

    public MessageResponse(int code, int version, String body, String redirect) {
        super(code, version, body);
        this.redirect = redirect;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
