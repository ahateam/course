import { Message } from 'element-ui';

export default{
    message(type,message,center){
        if(center===undefined) center=false
        Message({
            type:type,
            message:message,
            center:center,
        })
    }
}

