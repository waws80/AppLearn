package thanatos.netrequest.utils;

/**
 * Created by lxf52 on 2016/10/9.
 */

public class NetRequestConstant {

    public class Methed{
       public static final int GET=0;
        public static final int POST=1;
   }
    public class Error{
        public static final int NetWork_Error=0x0;
        public static final int No_Url=0x1;
        public static final int Url_Error=0x2;
        public static final int TimeOut_Error=0x3;
        public static final int GetDate_Error=0x4;
        public static final int NoPermission=0x4;
    }
}
