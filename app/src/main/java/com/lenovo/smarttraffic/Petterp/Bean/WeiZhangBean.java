package com.lenovo.smarttraffic.Petterp.Bean;

import java.util.List;

/**
 * @author Petterp on 2019/5/27
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class WeiZhangBean {

    /**
     * action/GetPeccancyType.do
     * {"UserName":"user1"}
     * ERRMSG : 成功
     * ROWS_DETAIL : [{"pcode":"1001A　　","premarks":"A 驾驶拼装的非汽车类机动车上道路行驶的","pmoney":1000,"pscore":0},{"pcode":"1001B　　","premarks":"B 驾驶拼装的汽车上道路行驶的","pmoney":1500,"pscore":0},{"pcode":"1002A　　","premarks":"A 驾驶已达报废标准的非汽车类机动车上道路行驶的","pmoney":1000,"pscore":0},{"pcode":"1002B　　","premarks":"B 驾驶已达报废标准的汽车上道路行驶的","pmoney":1500,"pscore":0},{"pcode":"1005A　　","premarks":"A 未取得驾驶证驾驶非汽车类机动车的","pmoney":1000,"pscore":0},{"pcode":"1005B　　","premarks":"B 未取得驾驶证驾驶汽车的","pmoney":1500,"pscore":0},{"pcode":"1006A　　","premarks":"A 驾驶证被吊销期间驾驶非汽车类机动车的","pmoney":1000,"pscore":0},{"pcode":"1006B　　","premarks":"B 驾驶证被吊销期间驾驶汽车的","pmoney":1500,"pscore":0},{"pcode":"1007","premarks":"把机动车交给未取得机动车驾驶证的人驾驶的","pmoney":1500,"pscore":0},{"pcode":"1008","premarks":"把机动车交给机动车驾驶证被吊销的人驾驶的","pmoney":1500,"pscore":0},{"pcode":"1009","premarks":"把机动车交给机动车驾驶证被暂扣的人驾驶的","pmoney":1500,"pscore":0},{"pcode":"1010A　　","premarks":"A 驾驶人在驾驶证超过有效期仍驾驶非汽车类机动车的","pmoney":1000,"pscore":0},{"pcode":"1010B 　","premarks":"B 驾驶人在驾驶证超过有效期仍驾驶汽车的","pmoney":1500,"pscore":0},{"pcode":"1011","premarks":"非法安装警报器的","pmoney":1000,"pscore":0},{"pcode":"1012","premarks":"非法安装标志灯具的","pmoney":1000,"pscore":0},{"pcode":"1013","premarks":"驾驶证丢失期间仍驾驶机动车的","pmoney":200,"pscore":0},{"pcode":"1014","premarks":"驾驶证损毁期间仍驾驶机动车的","pmoney":200,"pscore":0},{"pcode":"1015","premarks":"驾驶证被依法扣留期间仍驾驶机动车的","pmoney":200,"pscore":0},{"pcode":"1016","premarks":"违法记分达到12分仍驾驶机动车的","pmoney":200,"pscore":0},{"pcode":"1017","premarks":"不按规定投保机动车第三者责任险的","pmoney":150,"pscore":0},{"pcode":"1018","premarks":"机动车不在机动车道内行驶的","pmoney":150,"pscore":0},{"pcode":"1019","premarks":"机动车违反规定使用专用车道的","pmoney":150,"pscore":0},{"pcode":"1020","premarks":"机动车驾驶人不服从交警指挥的","pmoney":200,"pscore":0},{"pcode":"1021","premarks":"遇前方机动车停车排队等候或者缓慢行驶时,从前方车辆两侧穿插行驶的","pmoney":200,"pscore":0},{"pcode":"1022","premarks":"遇前方机动车停车排队等候或者缓慢行驶时,从前方车辆两侧超越行驶的","pmoney":200,"pscore":0},{"pcode":"1023","premarks":"遇前方机动车停车排队等候或者缓慢行驶时,未依次交替驶入车道减少后的路口、路段的","pmoney":100,"pscore":0},{"pcode":"1024","premarks":"在没有交通信号灯、交通标志、交通标线或交警指挥的交叉路口遇到停车排队等候或者缓慢行驶时,机动车未依次交替通行的","pmoney":100,"pscore":0},{"pcode":"1025","premarks":"遇前方机动车停车排队等候或者缓慢行驶时,在人行横道、网状线区域内停车等候的","pmoney":100,"pscore":0},{"pcode":"1026","premarks":"行经铁路道口,不按规定通行的","pmoney":50,"pscore":0},{"pcode":"1027","premarks":"机动车载货长度、宽度、高度超过规定的","pmoney":150,"pscore":0},{"pcode":"1028","premarks":"机动车载物行驶时遗洒、飘散载运物的","pmoney":150,"pscore":0},{"pcode":"1029","premarks":"运载超限物品时不按规定的时间、路线、速度行驶的","pmoney":150,"pscore":0},{"pcode":"1030","premarks":"运载超限物品时未悬挂明显标志的","pmoney":150,"pscore":0},{"pcode":"1031","premarks":"运载危险物品未经批准的","pmoney":200,"pscore":0},{"pcode":"1032","premarks":"运载危险物品时不按规定的时间、路线、速度行驶的","pmoney":200,"pscore":0},{"pcode":"1033","premarks":"运载危险物品时未悬挂警示标志的","pmoney":200,"pscore":0},{"pcode":"1034","premarks":"运载危险物品时未采取必要的安全措施的","pmoney":200,"pscore":0},{"pcode":"1035","premarks":"载客汽车载货违反规定的","pmoney":100,"pscore":0},{"pcode":"1036","premarks":"货运机动车违反规定载人的","pmoney":100,"pscore":0},{"pcode":"1037","premarks":"未将故障车辆移到不妨碍交通的地方停放的","pmoney":200,"pscore":0},{"pcode":"1038","premarks":"不避让正在作业的道路养护车、工程作业车的","pmoney":50,"pscore":0},{"pcode":"1039","premarks":"机动车违反规定停放、临时停车,妨碍其它车辆、行人通行的","pmoney":200,"pscore":0},{"pcode":"1040","premarks":"机动车喷涂、粘贴标识或者车身广告影响安全驾驶的","pmoney":100,"pscore":0},{"pcode":"1041","premarks":"道路养护施工作业车辆、机械作业时未开启示警灯和危险报警闪光灯的","pmoney":200,"pscore":0},{"pcode":"1042","premarks":"机动车不按规定车道行驶的","pmoney":200,"pscore":0},{"pcode":"1043","premarks":"变更车道时影响正常行驶的机动车的","pmoney":200,"pscore":0},{"pcode":"1044","premarks":"在禁止掉头或者禁止左转弯标志、标线的地点掉头的","pmoney":200,"pscore":0},{"pcode":"1045","premarks":"在容易发生危险的路段掉头的","pmoney":200,"pscore":0},{"pcode":"1046","premarks":"掉头时妨碍正常行驶的车辆和行人通行的","pmoney":200,"pscore":0},{"pcode":"1047","premarks":"机动车未按规定鸣喇叭示意的","pmoney":50,"pscore":0},{"pcode":"1048","premarks":"在禁止鸣喇叭的区域或者路段鸣喇叭的","pmoney":150,"pscore":0},{"pcode":"1049","premarks":"在机动车驾驶室的前后窗范围内悬挂、放置妨碍驾驶人视线的物品的","pmoney":50,"pscore":0},{"pcode":"1050","premarks":"机动车行经漫水路或漫水桥时未低速通过的","pmoney":50,"pscore":0},{"pcode":"1051","premarks":"机动车载运超限物品行经铁路道口时不按指定的道口通过的","pmoney":100,"pscore":0},{"pcode":"1052","premarks":"机动车载运超限物品行经铁路道口时不按指定的时间通过的","pmoney":100,"pscore":0},{"pcode":"1053","premarks":"机动车行经渡口,不服从渡口管理人员指挥,不依次待渡的","pmoney":50,"pscore":0},{"pcode":"1054","premarks":"上下渡船时,不低速慢行的","pmoney":50,"pscore":0},{"pcode":"1055","premarks":"特种车辆违反规定使用警报器的","pmoney":200,"pscore":0},{"pcode":"1056","premarks":"特种车辆违反规定使用标志灯具的","pmoney":200,"pscore":0},{"pcode":"1057","premarks":"机动车在单位院内居民居住区内不低速行驶的","pmoney":100,"pscore":0},{"pcode":"1058","premarks":"机动车在单位院内居民居住区内不避让行人的","pmoney":100,"pscore":0},{"pcode":"1059","premarks":"驾驶摩托车手离车把的","pmoney":150,"pscore":0},{"pcode":"1060","premarks":"驾驶摩托车在车把上悬挂物品的","pmoney":150,"pscore":0},{"pcode":"1061","premarks":"拖拉机驶入大中城市中心城区内道路的","pmoney":150,"pscore":0},{"pcode":"1062","premarks":"拖拉机驶入高速公路、城市快速路或其它禁止通行道路的","pmoney":150,"pscore":0},{"pcode":"1063","premarks":"拖拉机违反规定载人的","pmoney":200,"pscore":0},{"pcode":"1064","premarks":"拖拉机牵引多辆挂车的","pmoney":150,"pscore":0},{"pcode":"1065","premarks":"学习驾驶人不按指定路线上道路学习驾驶的","pmoney":100,"pscore":0},{"pcode":"1066","premarks":"学习驾驶人不按指定时间上道路学习驾驶的","pmoney":100,"pscore":0},{"pcode":"1067","premarks":"学习驾驶人使用非教练车上道路驾驶的","pmoney":100,"pscore":0},{"pcode":"1068","premarks":"学习驾驶人在教练不随车指导下上道路驾驶车辆的","pmoney":100,"pscore":0},{"pcode":"1069","premarks":"使用教练车时有与教学无关的人员乘坐的","pmoney":100,"pscore":0},{"pcode":"1070","premarks":"实习期内未粘贴或悬挂实习标志的","pmoney":200,"pscore":0},{"pcode":"1071","premarks":"上道路行驶的机动车未放置检验合格标志的","pmoney":100,"pscore":0},{"pcode":"1072","premarks":"驾驶安全设施不全的机动车的","pmoney":100,"pscore":0},{"pcode":"1073","premarks":"驾驶机件不符合技术标准的机动车的","pmoney":100,"pscore":0},{"pcode":"1101","premarks":"驾驶人或者乘坐人员未按规定使用安全带的","pmoney":200,"pscore":1},{"pcode":"1102","premarks":"不按规定使用灯光的","pmoney":200,"pscore":1},{"pcode":"1103","premarks":"不按规定会车的","pmoney":100,"pscore":1},{"pcode":"1104","premarks":"不按规定倒车的","pmoney":100,"pscore":1},{"pcode":"1105","premarks":"摩托车后座乘坐不满十二周岁未成年人的","pmoney":100,"pscore":1},{"pcode":"1106","premarks":"驾驶轻便摩托车载人的","pmoney":100,"pscore":1},{"pcode":"1107","premarks":"在车门、车厢没有关好时行车的","pmoney":50,"pscore":1},{"pcode":"1108","premarks":"上道路行驶的机动车未放置保险标志的","pmoney":100,"pscore":1},{"pcode":"1109","premarks":"未随车携带行驶证的","pmoney":100,"pscore":1},{"pcode":"1110","premarks":"未随车携带驾驶证的","pmoney":100,"pscore":1},{"pcode":"1201","premarks":"机动车载物超过核定载质量未达30%的","pmoney":200,"pscore":2},{"pcode":"1202","premarks":"公路客运车辆载客超过核定载客人数未达20%的","pmoney":500,"pscore":2},{"pcode":"1203","premarks":"机动车在没有划分机动车道、非机动车道和人行道的道路上,不在道路中间通行的","pmoney":50,"pscore":2},{"pcode":"1204","premarks":"行经人行横道,未减速行驶的","pmoney":200,"pscore":2},{"pcode":"1205","premarks":"遇行人正在通过人行横道时未停车让行的","pmoney":200,"pscore":2},{"pcode":"1206","premarks":"行经没有交通信号的道路时,遇行人横过道路未避让的","pmoney":200,"pscore":2},{"pcode":"1207","premarks":"驾驶摩托车时驾驶人未按规定戴安全头盔的或者乘坐人员未按规定戴安全头盔的","pmoney":200,"pscore":2},{"pcode":"1208","premarks":"机动车通过有灯控路口时,不按所需行进方向驶入导向车道的","pmoney":100,"pscore":2},{"pcode":"1209","premarks":"左转弯时,未靠路口中心点左侧转弯的","pmoney":100,"pscore":2},{"pcode":"1210","premarks":"通过路口遇放行信号不依次通过的","pmoney":100,"pscore":0},{"pcode":"1212","premarks":"通过路口向右转弯遇同车道内有车等候放行信号时,不依次停车等候的","pmoney":100,"pscore":2},{"pcode":"1213","premarks":"牵引故障机动车时,被牵引的机动车除驾驶人外载人的","pmoney":200,"pscore":2},{"pcode":"1214","premarks":"牵引故障机动车时,被牵引的机动车拖带挂车的","pmoney":100,"pscore":2},{"pcode":"1215","premarks":"牵引故障机动车时,被牵引的机动车宽度大于牵引的机动车的","pmoney":100,"pscore":2},{"pcode":"1216","premarks":"使用软连接装置牵引故障机动车时,牵引车与被牵引车之间未保持安全距离的","pmoney":100,"pscore":2},{"pcode":"1217","premarks":"牵引制动失效的被牵引车,未使用硬连接牵引装置的","pmoney":100,"pscore":2},{"pcode":"1218","premarks":"使用汽车吊车牵引车辆的","pmoney":100,"pscore":2},{"pcode":"1219","premarks":"使用轮式专用机械牵引车辆的","pmoney":100,"pscore":2},{"pcode":"1220","premarks":"使用摩托车牵引车辆的","pmoney":100,"pscore":2},{"pcode":"1221","premarks":"牵引摩托车的","pmoney":100,"pscore":2},{"pcode":"1222","premarks":"未使用专用清障车拖曳转向或照明、信号装置失效的机动车的","pmoney":100,"pscore":2},{"pcode":"1223","premarks":"驾驶时拨打接听手持电话的","pmoney":100,"pscore":2},{"pcode":"1224","premarks":"驾驶时观看电视的","pmoney":100,"pscore":2},{"pcode":"1225","premarks":"驾车时有其他妨碍安全行车的行为的","pmoney":100,"pscore":2},{"pcode":"1226","premarks":"连续驾驶机动车超过4小时未停车休息或停车休息时间少于20分钟的","pmoney":200,"pscore":2},{"pcode":"1227","premarks":"在同车道行驶中,不按规定与前车保持必要的安全距离的","pmoney":200,"pscore":2},{"pcode":"1228","premarks":"路口遇有交通阻塞时未依次等候的","pmoney":100,"pscore":2},{"pcode":"1229","premarks":"机动车违反禁令标志指示的","pmoney":200,"pscore":2},{"pcode":"1230","premarks":"机动车违反禁止标线指示的","pmoney":200,"pscore":2},{"pcode":"1231","premarks":"机动车违反警告标志指示的","pmoney":50,"pscore":2},{"pcode":"1232","premarks":"机动车违反警告标线指示的","pmoney":50,"pscore":2},{"pcode":"1233","premarks":"实习期内驾驶公共汽车的","pmoney":200,"pscore":2},{"pcode":"1234","premarks":"实习期内驾驶营运客车的","pmoney":200,"pscore":2},{"pcode":"1235","premarks":"实习期内驾驶执行任务的特种车辆的","pmoney":200,"pscore":2},{"pcode":"1236","premarks":"实习期内驾驶载有危险物品的机动车的","pmoney":200,"pscore":2},{"pcode":"1237","premarks":"实习期内驾驶的机动车牵引挂车的","pmoney":200,"pscore":2},{"pcode":"1238","premarks":"机动车载人超过核定人数的","pmoney":200,"pscore":2},{"pcode":"1301","premarks":"机动车逆向行驶的","pmoney":200,"pscore":3},{"pcode":"1303","premarks":"机动车行驶超过规定时速50%以下的","pmoney":150,"pscore":3},{"pcode":"1304","premarks":"从前车右侧超车的","pmoney":200,"pscore":3},{"pcode":"1305","premarks":"前车左转弯时超车的","pmoney":200,"pscore":3},{"pcode":"1306","premarks":"前车掉头时超车的","pmoney":200,"pscore":3},{"pcode":"1307","premarks":"前车超车时超车的","pmoney":200,"pscore":3},{"pcode":"1308","premarks":"与对面来车有会车可能时超车的","pmoney":200,"pscore":3},{"pcode":"1309","premarks":"超越执行紧急任务的警车、消防车、救护车、工程救险车的","pmoney":200,"pscore":3},{"pcode":"1310","premarks":"在铁路道口、路口、窄桥、弯道、陡坡、隧道、人行横道、交通流量大的路段等地点超车的","pmoney":200,"pscore":3},{"pcode":"1311","premarks":"车辆在道路上发生故障或事故后,妨碍交通又难以移动的,不按规定设置警告标志或未按规定使用警示灯光的","pmoney":200,"pscore":3},{"pcode":"1312","premarks":"准备进入环形路口不让已在路口内的机动车先行的","pmoney":100,"pscore":3},{"pcode":"1313","premarks":"转弯的机动车未让直行的车辆、行人先行的","pmoney":100,"pscore":3},{"pcode":"1314","premarks":"相对方向行驶的右转弯机动车不让左转弯车辆先行的","pmoney":100,"pscore":3},{"pcode":"1315","premarks":"机动车通过无灯控或交警指挥的路口,不按交通标志、标线指示让优先通行的一方先行的","pmoney":100,"pscore":3},{"pcode":"1316","premarks":"机动车通过无灯控、交警指挥、交通标志标线控制的路口,不让右方道路的来车先行的","pmoney":100,"pscore":3},{"pcode":"1317","premarks":"载货汽车牵引多辆挂车的","pmoney":150,"pscore":3},{"pcode":"1318","premarks":"半挂牵引车牵引多辆挂车的","pmoney":150,"pscore":3},{"pcode":"1319","premarks":"挂车的灯光信号、制动、连接、安全防护等装置不符合国家标准的","pmoney":150,"pscore":3},{"pcode":"1320","premarks":"小型载客汽车牵引旅居挂车以外的且总质量700千克以上挂车的","pmoney":150,"pscore":3},{"pcode":"1321","premarks":"挂车载人的","pmoney":150,"pscore":3},{"pcode":"1322","premarks":"载货汽车牵引挂车的载质量超过汽车本身的载质量的","pmoney":150,"pscore":3},{"pcode":"1323","premarks":"型载客汽车牵引挂车的","pmoney":150,"pscore":3},{"pcode":"1324","premarks":"中型载客汽车牵引挂车的","pmoney":150,"pscore":3},{"pcode":"1325","premarks":"低速载货汽车牵引挂车的","pmoney":150,"pscore":3},{"pcode":"1326","premarks":"三轮机动车汽车牵引挂车的","pmoney":150,"pscore":3},{"pcode":"1327","premarks":"机动车在发生故障或事故后,不按规定使用灯光的","pmoney":200,"pscore":3},{"pcode":"1328","premarks":"驾驶机动车下陡坡时熄火、空档滑行的","pmoney":200,"pscore":3},{"pcode":"1329","premarks":"故意遮挡机动车号牌的","pmoney":200,"pscore":3},{"pcode":"1330","premarks":"故意污损机动车号牌的","pmoney":200,"pscore":3},{"pcode":"1331","premarks":"不按规定安装机动车号牌的","pmoney":200,"pscore":3},{"pcode":"1332","premarks":"上道路行驶的机动车未悬挂机动车号牌的","pmoney":100,"pscore":3},{"pcode":"1333","premarks":"不避让执行任务的特种车辆的","pmoney":200,"pscore":3},{"pcode":"1334","premarks":"机动车不避让盲人的","pmoney":150,"pscore":3},{"pcode":"1339","premarks":"运输剧毒化学品机动车超过规定时速50%以下的","pmoney":200,"pscore":3},{"pcode":"1601A 　","premarks":"公路客运车辆载客,超过额定乘员20%以上不足50%的","pmoney":1000,"pscore":6},{"pcode":"1601B　　","premarks":"公路客运车辆载客超过额定乘员50%以上不足100%","pmoney":1500,"pscore":6},{"pcode":"1601C 　","premarks":"公路客运车辆载客超过额定乘员100%以上的","pmoney":2000,"pscore":6},{"pcode":"1602A　　","premarks":"机动车载物超过核定载质量30%以上不足100%的","pmoney":1000,"pscore":6},{"pcode":"1602B 　","premarks":"机动车载物超过核定载质量100%以上的","pmoney":2000,"pscore":6},{"pcode":"1603A 　","premarks":"机动车行驶超过规定时速50%以上不足100%的","pmoney":1000,"pscore":6},{"pcode":"1603B 　","premarks":"机动车行驶超过规定时速100%以上","pmoney":2000,"pscore":6},{"pcode":"1604","premarks":"饮酒后驾驶机动车的","pmoney":300,"pscore":6},{"pcode":"1605","premarks":"饮酒后驾驶营运机动车的","pmoney":500,"pscore":6},{"pcode":"1606","premarks":"公路客运车辆违反规定载货的","pmoney":1000,"pscore":6},{"pcode":"1607A 　","premarks":"货运机动车违反规定载客3人以下的","pmoney":500,"pscore":6},{"pcode":"1607B　　","premarks":"货运机动车违反规定载客3人以上的","pmoney":1500,"pscore":6},{"pcode":"1609","premarks":"运输剧毒化学品机动车超过规定时速50%以上的","pmoney":2000,"pscore":6},{"pcode":"1701","premarks":"使用他人机动车驾驶证驾驶机动车的","pmoney":200,"pscore":12},{"pcode":"1702","premarks":"醉酒后驾驶机动车的","pmoney":1500,"pscore":12},{"pcode":"1703","premarks":"醉酒后驾驶营运机动车的","pmoney":2000,"pscore":12},{"pcode":"1704A　　","premarks":"A在驾驶证暂扣期间仍驾驶非汽车类机动车的","pmoney":1000,"pscore":12},{"pcode":"1704B　　","premarks":"B在驾驶证暂扣期间仍驾驶汽车的","pmoney":1500,"pscore":12},{"pcode":"1705","premarks":"造成交通事故后逃逸,尚不构成犯罪","pmoney":2000,"pscore":12},{"pcode":"1706","premarks":"违反交通管制规定强行通行,不听劝阻的","pmoney":2000,"pscore":12},{"pcode":"1709A　　","premarks":"驾驶与驾驶证载明的准驾车型不相符合的车辆的 A驾驶非汽车类","pmoney":1000,"pscore":12},{"pcode":"1709B　　","premarks":"驾驶与驾驶证载明的准驾车型不相符合的车辆的 B驾驶汽车类","pmoney":1500,"pscore":12}]
     * RESULT : S
     */

    private String ERRMSG;
    private String RESULT;
    private List<ROWSDETAILBean> ROWS_DETAIL;

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public List<ROWSDETAILBean> getROWS_DETAIL() {
        return ROWS_DETAIL;
    }

    public void setROWS_DETAIL(List<ROWSDETAILBean> ROWS_DETAIL) {
        this.ROWS_DETAIL = ROWS_DETAIL;
    }

    public static class ROWSDETAILBean {
        /**
         * pcode : 1001A　　
         * premarks : A 驾驶拼装的非汽车类机动车上道路行驶的
         * pmoney : 1000
         * pscore : 0
         */

        private String pcode;
        private String premarks;
        private int pmoney;
        private int pscore;

        public String getPcode() {
            return pcode;
        }

        public void setPcode(String pcode) {
            this.pcode = pcode;
        }

        public String getPremarks() {
            return premarks;
        }

        public void setPremarks(String premarks) {
            this.premarks = premarks;
        }

        public int getPmoney() {
            return pmoney;
        }

        public void setPmoney(int pmoney) {
            this.pmoney = pmoney;
        }

        public int getPscore() {
            return pscore;
        }

        public void setPscore(int pscore) {
            this.pscore = pscore;
        }
    }
}
