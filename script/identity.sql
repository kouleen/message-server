create table if not exists task_identity
(
    id                    bigint                                 not null comment '主键',
    user_code             varchar(32)                            not null comment '居民编码',
    user_name             varchar(64)                            not null comment '居民名称',
    user_gender           int(1)       default 1                 not null comment '居民性别 1:男 0:女',
    user_phone            varchar(32)  default ''                null comment '电话号码',
    remark                varchar(256) default ''                null comment '备注',
    is_delete             tinyint      default 0                 not null comment '删除标识',
    object_version_number int          default 0                 not null comment '版本号',
    creation_date         datetime     default CURRENT_TIMESTAMP null comment '创建时间',
    last_update_date      datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    created_by            bigint       default -1                null comment '创建人',
    last_updated_by       bigint       default -1                null comment '创建人',
    created_account       varchar(50)  default 'SYSTEM'          null comment '创建帐号',
    last_updated_account  varchar(50)  default 'SYSTEM'          null comment '创建帐号'
) comment '身份表';