package com.kouleen.message.api.infrastructure.constants;

/**
 * @author zhangqing
 * @since 2023/8/4 11:38
 */
public enum FictionTypeEnum {

    MYSTERIOUS_FANTASY(1,0,"玄幻奇幻",10),
    WU_XIA_XIAN_XIA(2,0,"武侠仙侠",11),
    URBAN_ROMANCE(3,0,"都市言情",12),
    HISTORICAL_MILITARY(4,0,"历史军事",13),
    SCIENCE_FICTION_SUPERNATURAL(5,0,"科幻灵异",14),
    ONLINE_GAME_COMPETITION(6,0,"网游竞技",15),
    GIRL_CHANNEL(7,1,"女生频道",16)

    ;

    final int catId;

    final int direction;

    final String name;

    final int sort;

    FictionTypeEnum(int catId, int direction, String name, int sort){
        this.catId = catId;
        this.direction = direction;
        this.name = name;
        this.sort = sort;
    }

    public static String getFictionTypeName(int catId){
        FictionTypeEnum[] fictionTypeEnums = FictionTypeEnum.values();
        for (FictionTypeEnum fictionTypeEnum : fictionTypeEnums) {
            if(fictionTypeEnum.catId == catId){
                return fictionTypeEnum.name;
            }
        }
        return "";
    }
}
