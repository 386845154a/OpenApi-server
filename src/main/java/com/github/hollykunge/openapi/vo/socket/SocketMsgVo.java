package com.github.hollykunge.openapi.vo.socket;

import com.github.hollykunge.openapi.config.business.SocketMsgTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:zhuqz
 * description: 发送给消息给socket端
 * date:2019/12/27 16:02
 **/
@Data
public class SocketMsgVo implements Serializable {
    private static final long serialVersionUID = 3317552605396382844L;
    /**数据id 确认消息接收成功使用*/
    String id;
    /**发送人*/
    String sender = "openapi";
    /**接收人,多个逗号分割，且多个人情况下confirmFlg应是fasle,绑定和解绑不需要设置该属性*/
    String receiver;
    /**编码**/
    String code = SocketMsgTypeEnum.SINGLE_MSG.getCode();
    /**消息内容**/
    SocketMsgDetailVo msg;
    /**是否需要应答*/
    Boolean confirmFlg = false;

    public String getCode() {
        return code;
    }

    public void setCode(SocketMsgTypeEnum code) {
        this.code = code.getCode();
    }
    public void setCode(String code) throws Exception{
        boolean check = false;
        for(SocketMsgTypeEnum item : SocketMsgTypeEnum.values()){
            if(item.getCode().equals(code)){
                check = true;
                break;
            }
        }
        if(!check){
            throw new Exception("编码不再枚举范围内："+code);

        }
        this.code = code;
    }
}
