package netease.rcstest.constant;

/**
 * Create with IntelliJ IDEA
 *
 * @author ChenHongliang
 * @date 2021/12/1 17:28
 */
public class HttpTelecomConstant {
    public static int dueTime=600;


    public static String apiVersion="v1";

    public static final String unLegalAccessTokenCode ="40014";

    /**
     * 获取accessToken地址
     */
    public static final String getAccessTokenPath="/cspApi/"+apiVersion+"/accessToken";

    /**
     * 上传客户资料地址
     */
    public static final String uploadCustomerFilePath="/cspApi/"+apiVersion+"/uploadFile";

    /**
     * 查看客户资料地址
     */
    public static final String checkCustomerFilePath="/cspApi/"+apiVersion+"/getFile";

    /**
     * 新增客户地址
     */
    public static final String addCustomerPath="/cspApi/"+apiVersion+"/cspCustomer/addcspCustomer";

    /**
     * 查看客户资料地址
     */
    public static final String checkCustomerPath="/cspApi/"+apiVersion+"/cspCustomer/selectCspCustomer";

    /**
     * 修改客户资料地址
     */
    public static final String editCustomerPath="/cspApi/"+apiVersion+"/cspCustomer/editcspCustomer";

    /**
     * 删除客户地址
     */
    public static final String deleteCustomerPath="/cspApi/"+apiVersion+"/deleteCustomer";

    /**
     * 上传chatbot资料地址
     */
    public static final String uploadChatbotFilePath="/cspApi/"+apiVersion+"/Chatbot/uploadChatbotFile";

    /**
     * 新增chatbot地址
     */
    public static final String addChatbotPath="/cspApi/"+apiVersion+"/Chatbot/addChatbot";

    /**
     * 更改chatbot状态（上下线）地址
     */
    public static final String changeChatbotStatusPath="/cspApi/"+apiVersion+"/Chatbot/isOnlineUpdate";

    /**
     * 修改chatbot地址
     */
    public static final String editChatbotPath="/cspApi/"+apiVersion+"/Chatbot/updateChatbot";

    /**
     * 删除chatbot地址
     */
    public static final String deleteChatbotPath="/cspApi/"+apiVersion+"/Chatbot/deleteChatbot";

    /**
     * 开发者配置地址
     */
    public static final String updateDeveloperPath="/cspApi/"+apiVersion+"/developerConfiguration/updateDeveloper";

    /**
     * 手机号白名单配置地址
     */
    public static final String phoneWhitePath="/cspApi/"+apiVersion+"/configuration/whiteList/updatePhoneNumber";


    /**
     * 退出测试状态
     */
    public static  final String quiteTestPath="/cspApi/"+apiVersion+"/Chatbot/QuitTestStatus";

    /**
     * 查询客户
     */
    public static final String selectCspCustomerPath="/cspApi/"+apiVersion+"/selectCspCustomer";

}
