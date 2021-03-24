package com.github.hollykunge.openapi.vo.socket;

import com.github.hollykunge.openapi.config.business.SocketMsgDetailTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:zhuqz
 * description:应答前端消息体
 * date:2019/10/16 14:32
 **/
@Data
public class SocketMsgDetailVo implements Serializable {
    private static final long serialVersionUID = -820977987664126610L;
    /**编码 与 MessageType编码一一对应 因为前段是数字，这里暂时不能使用string*/
    private int code = Integer.valueOf(SocketMsgDetailTypeEnum.OPEN_API_NOTICE.getCode()) ;
    /**消息内容*/
    private Object data;

    public Integer getCode() {
        return code;
    }
   //使用枚举限制code，防止后期编码增加不好维护
    public void setCode(SocketMsgDetailTypeEnum code) {
        this.code = Integer.parseInt(code.getCode());
    }
    public void setCode(int code) throws Exception{
        boolean check = false;
        for(SocketMsgDetailTypeEnum item : SocketMsgDetailTypeEnum.values()){
            if(item.getCode().equals(code+"")){
                check = true;
                break;
            }
        }
        if(!check){
            throw new Exception("消息体编码不再枚举范围内："+code);

        }
        this.code = code;
    }
}
